package com.alamodrafthouse.ui.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alamodrafthouse.R;
import com.alamodrafthouse.data.model.CategoryModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sekhar on 4/6/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
  private List<CategoryModel> data = new ArrayList<>();

  public void setData(List<CategoryModel> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.example_list_item, parent, false);
    ViewHolder vh = new ViewHolder(v);
    return vh;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(data.get(position).getName());
  }

  @Override public int getItemCount() {

    return data.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ViewHolder(View v) {
      super(v);
      textView = (TextView) v;
    }
  }
}