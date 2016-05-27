package com.example.toidv.rxexample.ui.rxexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.toidv.rxexample.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class RxSampleDemo extends AppCompatActivity {
    private static final String TAG = RxSampleDemo.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFunction();
        function2();
        mapFunction();
        testSubscribe();

    }

    private void firstFunction() {
        // Observable emits Hello word and complete
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 1000000; i++) {
                    subscriber.onNext(i + "");
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());


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
                Log.d(TAG, s);
            }
        };

        // Hook them together
        myObservable.subscribe(mySubscriber);

    }

    public void testSubscribe() {
        Observable.just("Action1")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });

        Observable.just("Subscriber")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);

                    }
                });
        Observable.just("Observer")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                    }
                });

        ConnectableObservable<Integer> connectableObservable = Observable.range(0, 5).publish();
        connectableObservable.filter(integer -> integer % 2 == 0)
                .map(integer -> integer.toString())
                .subscribe(string -> {
                    Log.d(TAG, "even " + string);
                });

        connectableObservable.filter((integer -> (integer % 2 != 0)))
                .map(integer -> integer.toString())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "odd " + s);
                    }
                });

        connectableObservable.connect();


    }


    private void heavyComputationFunction() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 1000000; i++) {
                    subscriber.onNext(i + "");
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation()) // Scheduler intended for computational work
                .subscribe(s -> {
                    Log.d(TAG, s);
                });
    }


    private void mapFunction() {

        // Log the length of input string
        Observable.just("Hello world")
                .map(s -> s.length())
                .map(integer -> integer.toString())
                .subscribe(string -> {
                    Log.d(TAG, string);
                });

        // Log the even number
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8};
        Observable.from(integers)
                .filter(integer -> (integer % 2 == 0))
                .map(integer -> integer.toString())
                .subscribe(s -> {
                    Log.d(TAG, s);
                });
    }


    private void function2() {
        Observable<String> myObsrvable = Observable.just("Hell world2!");

        Action1<String> onNextAction = s -> Log.d(TAG, s);

        myObsrvable.subscribe(onNextAction);

        // Convert any object into an Observable that emits that object
        Observable.just("Hello world")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, s);
                    }
                });

        // Converts an Array into an Observable that emits the items in the Array.
        Integer[] numbers = {0, 1, 2, 3, 4, 5};
        Observable.from(numbers)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "complete emit item");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, integer.toString());

                    }


                });


    }


}
