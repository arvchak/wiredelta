package www.wiredelta.com.company_info.repository;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.model.CompanyResult;
import www.wiredelta.com.network.CompanyHttpClient;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyListResultImpl implements CompanyListRepository {

    private Context context;
    private ArrayList<CompanyInfoModel> models = new ArrayList<>();
    public CompanyListResultImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getAllCompanyResults(final LoadResultsCallback callback) {

        CompanyHttpClient client = new CompanyHttpClient(this.context);
        client.getAllCompanies(new Response.Listener<CompanyInfoModel[]>() {

            @Override
            public void onResponse(CompanyInfoModel[] response) {
                CompanyResult result = new CompanyResult();
                List<CompanyInfoModel> list = Arrays.asList(response);
                result.results = new ArrayList<>();
                result.results.addAll(list);
                setAllCompanyData(result.results);
                callback.onResultsLoaded(result);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResultsFailed();
            }
        });


    }

    @Override
    public ArrayList<CompanyInfoModel> getAllCompanyData() {
        return this.models;
    }

    @Override
    public void setAllCompanyData(ArrayList<CompanyInfoModel> models) {
            this.models = models;
    }
}
