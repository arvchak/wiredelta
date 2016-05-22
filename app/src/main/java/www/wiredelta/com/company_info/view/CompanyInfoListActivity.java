package www.wiredelta.com.company_info.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import www.wiredelta.com.R;
import www.wiredelta.com.company_info.adapter.CompanyListAdapter;
import www.wiredelta.com.company_info.adapter.GridViewAdapter;
import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.repository.CompanyListRepository;
import www.wiredelta.com.company_info.repository.CompanyListResultImpl;
import www.wiredelta.com.network.NetworkInfoUtility;

public class CompanyInfoListActivity extends AppCompatActivity implements CompanyInfoContract.View {


    private CompanyInfoListPresenter presenter;
    private RecyclerView listView;
    private TextView emptyView;
    private CompanyListAdapter adapter;
    private CompanyListRepository repository;
    private SearchView searchView;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.listView);
        emptyView = (TextView)findViewById(R.id.emptyView);
        repository = new CompanyListResultImpl(this);
        adapter = new CompanyListAdapter(this);
        presenter = new CompanyInfoListPresenter(this, repository);
        presenter.onCreate();
        presenter.loadData();
        handleIntent(getIntent());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetworkInfoUtility.isNetworkAvailable(this);
    }

    @Override
    public void showNetworkNotAvailable() {

        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(R.string.internet_state);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void showCompanylistContent(ArrayList<CompanyInfoModel> result) {
        listView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        adapter.setItems(result);

    }


    @Override
    public void setCompanyListAdapter() {
        listView.setAdapter(adapter);
    }

    @Override
    public void setupList() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);
        presenter.setAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        presenter.prepareSearchView();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter)
        {
            presenter.buildDepartments(repository.getAllCompanyData());
            adapter.setItems(repository.getAllCompanyData());
        }
        return true ;
    }

    @Override
    public void showEmptyCompanyListContent() {

        emptyView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void prepareSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchModels(repository.getAllCompanyData() , "");
                return true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("bundleData" , repository.getAllCompanyData());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<CompanyInfoModel> arrayList = savedInstanceState.getParcelableArrayList("bundleData");
        adapter.setItems(arrayList);
    }

    @Override
    public void showFilterDialog(ArrayList<String> uniqueDepts) {

        GridView view = new GridView(this);
        GridViewAdapter adapter = new GridViewAdapter(this , uniqueDepts);
        view.setNumColumns(3);
        view.setOnItemClickListener(itemClick);
        view.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Filter");
        builder.setCancelable(true);
        dialog = builder.show();
    }

    AdapterView.OnItemClickListener itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String tag = (String)adapterView.getAdapter().getItem(i);
            if(i == 0){
                adapter.getFilter().filter("");
            }else {
                adapter.getFilter().filter(tag);
            }
            dialog.dismiss();
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ArrayList<CompanyInfoModel> infomodels = repository.getAllCompanyData();
            presenter.searchModels(infomodels, query);
        }
    }
}
