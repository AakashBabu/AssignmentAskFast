package com.test.gambit.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import com.test.assignment.application.MyApplication
import com.test.assignment.networking.WebClientService
import com.test.assignment.utils.Constants.Companion.API_KEY
import com.test.assignment.utils.Constants.Companion.LANGAUGE
import com.test.assignment.utils.ResponceHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.HashMap

class WebClientSupport {


    val handler: ResponceHandler
    lateinit var progress: ProgressDialog
    val context: Context
    val webCall: WebClientService

    constructor(hand: ResponceHandler, context: Activity) {
        handler = hand
        this.context = context
        this.webCall = (context.application as MyApplication).getWebserviceCall()
        showLoadingDialog()
    }

    fun handleResporce(obj: Any) {
        dismissLoadingDialog()
        handler.handleSuccess(obj)
    }

    fun handleError(throwable: Throwable) {
        dismissLoadingDialog(throwable)
    }

    fun getList(i :Int,qry:String) :Disposable = (webCall.getList(qry,i))!!.subscribe(this::handleResporce, this::handleError)

    fun getDetail(id:Int):Disposable = (webCall.getDetail(id))!!.subscribe(this::handleResporce, this::handleError)



    fun showLoadingDialog() {
        progress = ProgressDialog(context)
        progress.setTitle("Loading...")
//        progress.setMessage("Please wait a moment")
        progress.show()
    }

    fun dismissLoadingDialog() {
        if (progress.isShowing()) {
            progress.dismiss()
        }
    }

    private fun dismissLoadingDialog(message: Throwable) {
        if (progress.isShowing()) {
            progress.dismiss()
        }
        AlertDialog.Builder(context).setTitle("Alert")
                .setMessage(message.message)
                .setPositiveButton("Retry", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        handler.handleError(message, true)
                    }

                }).setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        handler.handleError(message, false)
                    }

                }).create().show()
    }

}