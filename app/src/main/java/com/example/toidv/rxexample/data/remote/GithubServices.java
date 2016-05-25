package com.example.toidv.rxexample.data.remote;

import com.example.toidv.rxexample.data.pojo.Owner;
import com.example.toidv.rxexample.data.pojo.Repo;
import com.example.toidv.rxexample.data.pojo.SearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by TOIDV on 5/24/2016.
 */
public interface GithubServices {

    String ENDPOINT = "https://api.github.com/";

    @GET("search/users")
    Observable<SearchResult> searchRepo(@Query("q") String name, @Query("sort") String sort);

    @GET
    Observable<Repo[]> getRepos(@Url String url);


    class Creator {

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();


            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit;

        }

    }
}
