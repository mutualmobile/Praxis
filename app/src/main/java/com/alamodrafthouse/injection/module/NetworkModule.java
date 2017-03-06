package com.alamodrafthouse.injection.module;

import com.alamodrafthouse.BuildConfig;
import com.alamodrafthouse.data.DataManager;
import com.alamodrafthouse.data.services.ApiService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shekar on 3/3/17.
 */

@Module public class NetworkModule {

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      httpBuilder.interceptors().add(httpLoggingInterceptor);
    }
    return httpBuilder.build();
  }

  @Provides @Singleton Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .build();
  }

  @Provides @Singleton ApiService provideApiService(Retrofit restAdapter) {
    return restAdapter.create(ApiService.class);
  }

  @Provides @Singleton DataManager getDataManager(ApiService apiService) {
    return new DataManager(apiService);
  }
}
