package com.alamodrafthouse.ui.category;

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
  public final ObservableBoolean loaded = new ObservableBoolean();
  private CompositeSubscription compositeSubscription;
  private DataManager dataManager;

  @Inject public CategoryViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void loadData() {
    if (compositeSubscription == null) {
      compositeSubscription = new CompositeSubscription();
    }
    loaded.set(false);
    compositeSubscription.add(dataManager.getCategorys()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<CategoryModel>>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            loaded.set(true);
            //getMvvmView().showError();
          }

          @Override public void onNext(List<CategoryModel> categoryModels) {
            loaded.set(true);
            //getMvvmView().showContent(categoryModels);
          }
        }));
  }

  public ObservableBoolean isLoaded() {
    return loaded;
  }

  @Override public void unsubscribeFromDataStore() {
    Timber.d("unsubscribeFromDataStore(): ");
    compositeSubscription.unsubscribe();
    compositeSubscription.clear();
    compositeSubscription = null;
  }
}
