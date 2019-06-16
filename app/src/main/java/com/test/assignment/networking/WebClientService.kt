package com.test.assignment.networking

import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.model.ListModel
import com.test.assignment.utils.Constants
import com.test.assignment.utils.Constants.Companion.API_KEY
import com.test.gambit.utils.WebAPiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class WebClientService {

    val webCall: WebAPiService

    constructor(retrofit: Retrofit) {
        webCall = retrofit.create(WebAPiService::class.java)
    }

    fun getList(qry:String,page:Int): Observable<ListModel>? {
        return webCall.getList(qry,page,API_KEY,Constants.LANGAUGE)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getDetail(id:Int): Observable<DetailModel>? {
        return webCall.getDetail(id,API_KEY,Constants.LANGAUGE)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}