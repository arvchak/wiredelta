package www.wiredelta.com.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import www.wiredelta.com.company_info.model.CompanyInfoModel;
import www.wiredelta.com.company_info.model.CompanyResult;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyHttpClient  extends HttpClient{

    public CompanyHttpClient(Context context) {
        super(context);
    }

    public void getAllCompanies(Response.Listener<CompanyInfoModel[]> companyList , Response.ErrorListener errorListener){
        Class listType = CompanyInfoModel[].class;
        GsonRequest<CompanyInfoModel[]> request = new GsonRequest<>(Request.Method.GET , Api.GET_COMPANIES , listType,companyList,  errorListener);
        queue.add(request);
    }
}
