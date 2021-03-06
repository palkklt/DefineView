package me.stefan.easybehavior.demo1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import me.stefan.easybehavior.R;
import me.stefan.easybehavior.demo1.model.SecondClassItem;

/**
 * 二级分类（即右侧菜单）的adapter
 * Created by hanj on 14-9-25.
 */
public class SecondClassAdapter extends BaseAdapter{
    private Context context;
    private List<SecondClassItem> list;

    public SecondClassAdapter(Context context, List<SecondClassItem> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
//    public Object getItem(int position) {
//        return null;
//    }

    public Object getItem(int position){
        return list.get(position);
    }

    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
    public long getItemId(int position){
        return  position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.right_listview_item, null);
            holder.nameTV = (TextView) convertView.findViewById(R.id.right_item_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTV.setText(list.get(position).getName());

        return convertView;
    }

    private class ViewHolder{
        TextView nameTV;
    }

}
