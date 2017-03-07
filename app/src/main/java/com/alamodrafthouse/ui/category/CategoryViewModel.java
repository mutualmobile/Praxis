package com.alamodrafthouse.ui.category;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import com.alamodrafthouse.data.DataManager;
import com.alamodrafthouse.data.model.CategoryModel;
import com.alamodrafthouse.ui.base.BaseViewModel;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by Shekar on 3/3/17.
 */

public class CategoryViewModel extends BaseViewModel<CategoryView> {
  public final ObservableBoolean showProgress = new ObservableBoolean();
  public final ObservableBoolean showError = new ObservableBoolean(false);
  public ObservableArrayList<CategoryModel> categoryModelList = new ObservableArrayList<>();
  private CompositeSubscription compositeSubscription;
  private DataManager mDataManager;

  @Inject public CategoryViewModel(DataManager dataManager) {
    mDataManager = dataManager;
  }

  public void loadData() {
    if (compositeSubscription == null) {
      compositeSubscription = new CompositeSubscription();
    }
    showProgress.set(true);
    compositeSubscription.add(mDataManager.getCategorys()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<CategoryModel>>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            showProgress.set(false);
            showError.set(true);
          }

          @Override public void onNext(List<CategoryModel> categoryModels) {
            showProgress.set(false);
            showError.set(false);
            categoryModelList.addAll(categoryModels);
          }
        }));
  }

  public ObservableBoolean isLoaded() {
    return showProgress;
  }

  @Override public void unsubscribeFromDataStore() {
    Timber.d("unsubscribeFromDataStore(): ");
    compositeSubscription.unsubscribe();
    compositeSubscription.clear();
    compositeSubscription = null;
  }
}
