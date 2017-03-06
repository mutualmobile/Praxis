package com.alamodrafthouse.ui.base;

public interface MvvmViewModel<V extends MvvmView> {
  void attachView(V view);

  void detachView();
}
