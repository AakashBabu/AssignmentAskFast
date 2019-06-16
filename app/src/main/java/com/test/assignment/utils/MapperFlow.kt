package com.test.assignment.utils

import android.content.Context
import android.content.Intent
import com.test.assignment.view.activity.DetailActivity

class MapperFlow {
    companion object{
        val ID = "ID"
        fun moveToDetailPage(context: Context, id: Int){
            context.startActivity(Intent(context, DetailActivity::class.java).putExtra(ID,id))
        }
    }
}