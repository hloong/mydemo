package com.hloong.mydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.MovieEntity;
import com.hloong.mydemo.net4okhttp.HttpMethods;
import com.hloong.mydemo.net4okhttp.ProgressSubscriber;
import com.hloong.mydemo.net4okhttp.SubscriberOnNextListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxJavaRetrofitActivity extends Activity {
    @Bind(R.id.click_me_BN)
    Button btClickMe;
    @Bind(R.id.result_TV)
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_me_BN)
    public void onClick(){
        get3Movie();
    }

//    private void getMovie() {
//        String baseUrl = "https://api.douban.com/v2/movie/";
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Api api = retrofit.create(Api.class);
//        Call<MovieEntity> call = api.getTopMoive(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                tvResult.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                tvResult.setText(t.getMessage());
//            }
//        });
//    }
//    private void get2Movie() {
//        String baseUrl = "https://api.douban.com/v2/movie/";
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        Api api = retrofit.create(Api.class);
//        api.getTopMoive(0,10)
//           .subscribeOn(Schedulers.io())
//           .observeOn(AndroidSchedulers.mainThread())
//           .subscribe(new Observer<MovieEntity>() {
//               @Override
//               public void onCompleted() {
//                   Toast.makeText(RxJavaRetrofitActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//               }
//
//               @Override
//               public void onError(Throwable e) {
//                   tvResult.setText(e.getMessage());
//               }
//
//               @Override
//               public void onNext(MovieEntity movieEntity) {
//                   tvResult.setText(movieEntity.toString());
//               }
//           });
//    }

    private void get3Movie(){
        SubscriberOnNextListener getTopMovieOnNext = new SubscriberOnNextListener<MovieEntity>(){
            @Override
            public void onNext(MovieEntity movieEntity) {

            }
        };
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext,RxJavaRetrofitActivity.this), 0, 10);
    }
}
