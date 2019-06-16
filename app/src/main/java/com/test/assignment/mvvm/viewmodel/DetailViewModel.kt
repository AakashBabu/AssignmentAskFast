package com.test.assignment.mvvm.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.model.ListItem
import com.test.assignment.mvvm.repository.ListerRepository

class DetailViewModel : ViewModel() {

    lateinit var detail_model: MutableLiveData<DetailModel>
    var repo : ListerRepository? = null

    fun init(activity: Activity){
        detail_model = MutableLiveData()
        repo = ListerRepository(activity)

    }

    fun fetch( i: Int) {
        repo?.loadDetail(i,detail_model)

    }

    fun getDetailModel(): LiveData<DetailModel> = detail_model
}