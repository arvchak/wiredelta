package www.wiredelta.com.company_info.repository;

import com.android.volley.Response;

import java.util.ArrayList;

import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.model.CompanyResult;

/**
 * Created by arvind on 5/19/16.
 */
public interface CompanyListRepository {

    interface LoadResultsCallback {
        void onResultsLoaded(CompanyResult result);
        void onResultsFailed();

    }

    void getAllCompanyResults(LoadResultsCallback callback);
    ArrayList<CompanyInfoModel> getAllCompanyData();
    void setAllCompanyData(ArrayList<CompanyInfoModel> models);
}
