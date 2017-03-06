package com.alamodrafthouse;

public class MvvmViewNotAttachedException extends RuntimeException {
    public MvvmViewNotAttachedException() {
        super("Please call ViewModel.attachView(MvvmView) before requesting data to the ViewModel");
    }
}
