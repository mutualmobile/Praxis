package com.alamodrafthouse.ui.category;

import android.os.Bundle;
import com.alamodrafthouse.R;
import com.alamodrafthouse.databinding.ActivityCategoryBinding;
import com.alamodrafthouse.injection.component.ActivityComponent;
import com.alamodrafthouse.ui.base.BaseActivity;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

/**
 * Created by Sekhar on 4/6/15.
 */
public class CategoryActivity extends BaseActivity<ActivityCategoryBinding, CategoryViewModel>
    implements CategoryView {

  @Inject CategoryAdapter mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Crashlytics());
    setUI();
    loadData();
  }

  @Override protected void onComponentCreated(ActivityComponent component) {
    component.inject(this);
  }

  @Override protected void onResume() {
    super.onResume();
  }

  private void loadData() {
    viewModel.loadData();
  }

  private void setUI() {
    throw new RuntimeException("This is a crash");
    //binding.recyclerView.setHasFixedSize(true);
    //binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //binding.recyclerView.setAdapter(mAdapter);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    viewModel.unsubscribeFromDataStore();
  }

  @Override protected int layoutId() {
    return R.layout.activity_category;
  }
}
