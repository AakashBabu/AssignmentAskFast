package com.test.assignment.mvvm.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.assignment.mvvm.model.ListItem
import com.test.assignment.mvvm.repository.ListerRepository

class ListViewModel : ViewModel() {

    lateinit var list_data: MutableLiveData<ArrayList<ListItem>>
    var repo : ListerRepository? = null

    fun init(activity: Activity){
        list_data = MutableLiveData()
        repo = ListerRepository(activity)

    }

    fun fetch( i: Int, qry: String) {
        repo?.loadList(i, qry,list_data)

    }

    fun getLister(): LiveData<ArrayList<ListItem>> = list_data
}