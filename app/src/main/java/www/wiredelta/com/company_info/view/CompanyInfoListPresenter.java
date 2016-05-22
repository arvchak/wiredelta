package www.wiredelta.com.company_info.view;

import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.model.CompanyResult;
import www.wiredelta.com.company_info.repository.CompanyListRepository;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyInfoListPresenter implements CompanyListRepository.LoadResultsCallback {


    private final CompanyInfoContract.View view;
    private final CompanyListRepository repository;


    public CompanyInfoListPresenter(CompanyInfoContract.View view, CompanyListRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void onCreate() {
        view.setupList();
    }

    public void setAdapter() {
        view.setCompanyListAdapter();
    }


    public void loadData() {
        if (!view.isNetworkAvailable()) {
            view.showNetworkNotAvailable();
            return;
        }
        repository.getAllCompanyResults(this);
    }


    @Override
    public void onResultsLoaded(CompanyResult result) {
        Log.d("results" , result.results.toString());
        view.showCompanylistContent(result.results);
    }

    @Override
    public void onResultsFailed() {

    }

    public void buildDepartments(ArrayList<CompanyInfoModel> infomodels){

        ArrayList<String> filterDept = new ArrayList<>();
        for(CompanyInfoModel model : infomodels){
            String[] deptArray = model.companyDepartments.split(",");
            for(String dept : deptArray) {
                if(!dept.isEmpty() && !filterDept.contains(dept.trim()))
                    filterDept.add(dept.trim());
            }
        }
        if(filterDept.isEmpty()){
         view.showEmptyCompanyListContent();
            return ;
        }

        Collections.sort(filterDept);
        filterDept.add(0 , "All Companies");
        view.showFilterDialog(filterDept);
    }




    public void searchModels(ArrayList<CompanyInfoModel> infomodels, String query) {

        ArrayList<CompanyInfoModel> searchResults = new ArrayList<>();
        if(query.isEmpty()){
            view.showCompanylistContent(infomodels);
            return;
        }
        for(CompanyInfoModel model : infomodels){
            if(model.companyName.contains(query)) {
                searchResults.add(model);
            }
        }
        if(searchResults.isEmpty()){
            view.showEmptyCompanyListContent();
        }else {
            view.showCompanylistContent(searchResults);
        }

    }

    public void prepareSearchView() {

        view.prepareSearchView();
    }
}
