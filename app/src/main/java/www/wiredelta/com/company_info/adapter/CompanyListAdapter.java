package www.wiredelta.com.company_info.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import www.wiredelta.com.R;
import www.wiredelta.com.company_info.model.CompanyInfoModel;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyListAdapter extends RecyclerView.Adapter<CompanyViewHolder> implements Filterable {

    ArrayList<CompanyInfoModel> items;
    private Context context;

    public CompanyListAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public void setItems(ArrayList<CompanyInfoModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void add(CompanyInfoModel infoModel) {
        this.items.add(infoModel);
        notifyItemInserted(items.size() - 1);
    }


    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.row_company_item, parent, false);
        return new CompanyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        CompanyInfoModel model = this.items.get(position);
        holder.title.setText(model.companyName);
        holder.description.setText(model.companyDescription);
        holder.founder.setText(context.getString(R.string.founded_by)+" "+model.companyOwner);
        holder.departments.setText(model.companyDepartments);
        String year = model.getYear();
        if(year!=null)
        {
            holder.startDate.setText(context.getString(R.string.active_since)+year);
        }
        else
            holder.startDate.setText("");
    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public Filter getFilter() {
        return new FilterDepartments(this , items);
    }

    static class FilterDepartments extends Filter {
        ArrayList<CompanyInfoModel> filteredList;
        CompanyListAdapter adapter;
        private ArrayList<CompanyInfoModel> originalData;

        public FilterDepartments(CompanyListAdapter adapter, ArrayList<CompanyInfoModel> originalData) {
            this.originalData = originalData;
            filteredList = new ArrayList<>();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(adapter.items);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final CompanyInfoModel data : originalData) {
                    if (data.companyDepartments.toLowerCase().trim().contains(filterPattern)) {
                        filteredList.add(data);
                    }

                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            adapter.setItems((ArrayList<CompanyInfoModel>) filterResults.values);
        }
    }

}
