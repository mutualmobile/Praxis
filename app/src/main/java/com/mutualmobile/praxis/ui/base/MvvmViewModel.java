package com.mutualmobile.praxis.ui.base;

public interface MvvmViewModel<V extends MvvmView> {
  void attachView(V view);

  void detachView();
}
