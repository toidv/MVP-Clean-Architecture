package com.example.toidv.rxexample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.toidv.rxexample.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.AsyncOnSubscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFunction();
        function2();
        function3();

    }

    private void firstFunction() {
        // Observalbe emits Hello word and complete
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello world!");
                subscriber.onCompleted();
            }
        });


        // Create subscriber consume the data
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("DVT", s);
            }
        };

        // Hook them together
        myObservable.subscribe(mySubscriber);


        // When subscription are made, myObservable calls the subscriber's onNext()
        // and complete()
    }

    private void function2() {
        Observable<String> myObsrvable = Observable.just("Hell world2!");

        Action1<String> onNextAction = s -> Log.e("DVT", s);

        myObsrvable.subscribe(onNextAction);
    }

    private void function3() {
        Observable.just("Hello world 3")
                .map(s -> s + " Steve").subscribe(s -> {
            Log.e("DVT", s);
        });

        /*query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .take(5)
                .doOnNext(title -> saveTitle(title))
                .subscribe(title -> System.out.println(title));*/
    }

}
