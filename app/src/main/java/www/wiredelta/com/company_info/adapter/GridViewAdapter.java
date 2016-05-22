package www.wiredelta.com.company_info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import www.wiredelta.com.R;

/**
 * Created by arvind on 5/22/16.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<String> items;
    CompoundButton.OnCheckedChangeListener changeListener;

    public GridViewAdapter(Context mContext, ArrayList<String> items) {
        this.mContext = mContext;
        this.items = items;
        this.changeListener = changeListener;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.row_dept, viewGroup, false);
            holder.checkBox = (TextView) view;
            holder.checkBox.setPadding(16, 16, 16 ,16);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.checkBox.setText(items.get(i));
        return view;
    }

    private static class ViewHolder {

        public TextView checkBox;

    }

}
