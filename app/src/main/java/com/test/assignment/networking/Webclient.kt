package com.test.gambit.utils

import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.model.ListModel
import io.reactivex.Observable;
import okhttp3.*
import retrofit2.http.*
import java.io.IOException


public interface WebAPiService {

    @GET("movie/{qry}")
    fun getList(@Path("qry") qry: String, @Query("page") page: Int, @Query("api_key") key: String, @Query("language") language: String): Observable<ListModel>

    @GET("movie/{qry}")
    fun getDetail(@Path("qry") qry: Int, @Query("api_key") key: String, @Query("language") language: String): Observable<DetailModel>
}

class UserAgentInterceptor : Interceptor {

    private val userAgent: String

    init {
        this.userAgent = System.getProperty("http.agent")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestWithUserAgent = originRequest.newBuilder()
            .header("User-Agent", userAgent)
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}