package com.test.assignment.mvvm.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.model.ListItem
import com.test.assignment.mvvm.model.ListModel
import com.test.assignment.utils.Connectivity
import com.test.assignment.utils.ResponceHandler
import com.test.gambit.utils.WebClientSupport

class ListerRepository(val context: Activity) {


    fun loadList(
        i: Int,
        query: String,
        list_data: MutableLiveData<ArrayList<ListItem>>) {

        if (Connectivity(context).isNetworkConnected()) {

            WebClientSupport(object : ResponceHandler {
                override fun handleSuccess(obj: Any) {
                    val data_set: ArrayList<ListItem> = ArrayList()
                    if(list_data.value != null)
                        data_set.addAll(list_data.value!!)
                    Log.v("responce", "getPlayerList --" + data_set.size)
                    val model = obj as ListModel
                    data_set.addAll(model.results)
                    list_data.value = (data_set)
                    Log.v("responce", "getPlayerList -0-" + data_set.size)
                    Log.v("responce", "getPlayerList -po-11")
                }

                override fun handleError(t: Throwable, retry: Boolean) {
                    if (retry)
                        loadList(i, query, list_data)
                }
            }, context).getList(if (i == -1) 0 else i, query)
        }
    }


    fun loadDetail(
        i: Int,
        list_data: MutableLiveData<DetailModel>) {

        if (Connectivity(context).isNetworkConnected()) {

            WebClientSupport(object : ResponceHandler {
                override fun handleSuccess(obj: Any) {
                    val model = obj as DetailModel
                    list_data.value = (model)
                }

                override fun handleError(t: Throwable, retry: Boolean) {
                    if (retry)
                        loadDetail(i,  list_data)
                }
            }, context).getDetail(i)
        }
    }
}