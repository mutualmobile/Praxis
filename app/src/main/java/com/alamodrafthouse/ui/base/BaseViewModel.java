package com.alamodrafthouse.ui.base;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public abstract class BaseViewModel<T extends MvvmView> implements MvvmViewModel<T> {

  private T mvvmView;
  protected CompositeSubscription compositeSubscription;

  @Override
  public void attachView(T mvvmView) {
    this.mvvmView = mvvmView;
  }

  @Override
  public void detachView() {
    mvvmView = null;
  }

  public boolean isViewAttached() {
    return mvvmView != null;
  }

  public T getMvvmView() {
    return mvvmView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }

  public void unsubscribeFromDataStore() {
    Timber.d("unsubscribeFromDataStore(): ");
    if(compositeSubscription!=null) {
      compositeSubscription.unsubscribe();
      compositeSubscription.clear();
      compositeSubscription = null;
    }
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(CategoryMvpView) before" +
          " requesting data to the Presenter");
    }
  }
}