package com.example.toidv.rxexample.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.toidv.rxexample.R;
import com.example.toidv.rxexample.data.pojo.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by TOIDV on 5/27/2016.
 */
public class RxExampleUtils {

    public static MaterialDialog createProgress(Context context, String title) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
    }


    public static MaterialDialog createAlertDialog(Context context, String title) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .positiveText(R.string.ok)
                .build();
    }

    public static String getError(Throwable e, Retrofit retrofit) {
        String errorMessage = "General Error!";
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            Converter<ResponseBody, ApiError> responseBodyObjectConverter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            try {
                ApiError error = responseBodyObjectConverter.convert(body);
                if (error != null) {
                    errorMessage = error.getMessage();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return errorMessage;
    }
}
