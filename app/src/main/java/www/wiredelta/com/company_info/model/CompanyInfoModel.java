package www.wiredelta.com.company_info.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyInfoModel implements Parcelable{

    public long companyId;
    @SerializedName("comapnyName")
    public String companyName;
    public String companyOwner;
    public String companyStartDate;
    public String companyDescription;
    public String companyDepartments;


    protected CompanyInfoModel(Parcel in) {
        companyId = in.readLong();
        companyName = in.readString();
        companyOwner = in.readString();
        companyStartDate = in.readString();
        companyDescription = in.readString();
        companyDepartments = in.readString();
    }

    public static final Creator<CompanyInfoModel> CREATOR = new Creator<CompanyInfoModel>() {
        @Override
        public CompanyInfoModel createFromParcel(Parcel in) {
            return new CompanyInfoModel(in);
        }

        @Override
        public CompanyInfoModel[] newArray(int size) {
            return new CompanyInfoModel[size];
        }
    };

    public String getYear()
    {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy");
        try {
            Date inputDate = inputFormat.parse(this.companyStartDate);
            return outputFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null ;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(companyId);
        parcel.writeString(companyName);
        parcel.writeString(companyOwner);
        parcel.writeString(companyStartDate);
        parcel.writeString(companyDescription);
        parcel.writeString(companyDepartments);
    }
}
