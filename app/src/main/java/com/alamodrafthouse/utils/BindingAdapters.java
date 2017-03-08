package com.alamodrafthouse.utils;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.alamodrafthouse.data.model.CategoryModel;
import com.alamodrafthouse.ui.category.CategoryAdapter;

public class BindingAdapters {

  @BindingAdapter("bind:visibility") public static void setVisibility(View view, boolean visible) {
    view.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

  @BindingAdapter("bind:catergoryItems")
  public static void bindList(RecyclerView view, ObservableArrayList<CategoryModel> list) {
    ((CategoryAdapter) view.getAdapter()).setData(list);
  }
}
