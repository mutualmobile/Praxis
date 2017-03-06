package com.alamodrafthouse.data.services;

import com.alamodrafthouse.data.model.CategoryModel;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiService {
    @GET("/categories/") Observable<List<CategoryModel>> getCategorys();
}