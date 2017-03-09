package com.alamodrafthouse.ui.category;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alamodrafthouse.R;
import com.alamodrafthouse.data.model.CategoryModel;
import com.alamodrafthouse.databinding.ExampleListItemBinding;
import com.alamodrafthouse.ui.kotlinsample.KotlinActivity;
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
    holder.binder.setViewModel(data.get(position));
    holder.binder.executePendingBindings();
    holder.binder.getRoot().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        v.getContext().startActivity(new Intent(v.getContext(), KotlinActivity.class));
      }
    });
  }

  @Override public int getItemCount() {
    return data.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final ExampleListItemBinding binder;

    public ViewHolder(View v) {
      super(v);
      binder = DataBindingUtil.bind(v);
    }
  }
}