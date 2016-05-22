package www.wiredelta.com.company_info.view;

import android.content.Context;

import java.util.ArrayList;
import java.util.Set;

import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.model.CompanyResult;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyInfoContract {

    public interface View
    {
        Context getContext();
        boolean isNetworkAvailable();
        void showNetworkNotAvailable();
        void showCompanylistContent(ArrayList<CompanyInfoModel> result);
        void setCompanyListAdapter();
        void setupList();
        void showEmptyCompanyListContent();
        void prepareSearchView();
        void showFilterDialog(ArrayList<String> uniqueDepts);
    }
}
