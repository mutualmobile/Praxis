package com.mutualmobile.praxis.ui.base;

import android.databinding.BaseObservable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public abstract class BaseViewModel<T extends MvvmView> extends BaseObservable implements MvvmViewModel<T> {

  private CompositeDisposable compositeDisposable;
  private T mvvmView;

  @Override public void attachView(T mvvmView) {
    this.mvvmView = mvvmView;
  }

  @Override public void detachView() {
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
    if (compositeDisposable != null) {
      compositeDisposable.dispose();
      compositeDisposable.clear();
      compositeDisposable = null;
    }
  }

  protected void addDisposable(Disposable disposable) {
    if (compositeDisposable == null) {
      compositeDisposable = new CompositeDisposable();
    }

    compositeDisposable.add(disposable);
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(CategoryMvpView) before" + " requesting data to the Presenter");
    }
  }
}