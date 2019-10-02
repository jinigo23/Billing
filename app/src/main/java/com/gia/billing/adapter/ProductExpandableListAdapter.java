/*
package com.gia.billing.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gia.billing.R;
import com.gia.billing.model.Products;

import java.util.HashMap;
import java.util.List;

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    //    private ArrayList<Products> productsArrayList;
    private List<String> listHead;
    private HashMap<String, Products> listHashMap;

    public ProductExpandableListAdapter(Context context, List<String> listHead, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listHead = listHead;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listHead.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listHead.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHead.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listHead.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.expandable_list_items, null);
        }
        TextView expand_item = view.findViewById(R.id.expand_item);
        expand_item.setTypeface(null, Typeface.BOLD);
        expand_item.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
//            view=LayoutInflater.from(context).get
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.expandable_list_items, null);
        }
        TextView child_item = view.findViewById(R.id.expand_item);
        child_item.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
*/
