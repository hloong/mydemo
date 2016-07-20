package com.hloong.mydemo.net4okhttp;

import com.hloong.mydemo.bean.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/11.
 */
public interface Api {
    @GET("top250")
//    Call<MovieEntity> getTopMoive(@Query("start") int start,@Query("count") int count);
    Observable<httpResult<MovieEntity>> getTopMoive(@Query("start") int start, @Query("count") int count);

}
