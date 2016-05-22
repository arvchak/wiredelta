package www.wiredelta.com.company_info.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import www.wiredelta.com.R;

/**
 * Created by arvind on 5/19/16.
 */
public class CompanyViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView description;
    public TextView startDate;
    public TextView founder;
    public TextView departments;

    public CompanyViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.title);
        description = (TextView)itemView.findViewById(R.id.description);
        startDate = (TextView)itemView.findViewById(R.id.started);
        founder = (TextView)itemView.findViewById(R.id.founder);
        departments = (TextView)itemView.findViewById(R.id.department);

    }
}
