package com.mutualmobile.praxis.utils;

import android.databinding.BindingAdapter;
import android.view.View;

public class BindingAdapters {

  @BindingAdapter("visibility") public static void setVisibility(View view, boolean visible) {
    view.setVisibility(visible ? View.VISIBLE : View.GONE);
  }
}
