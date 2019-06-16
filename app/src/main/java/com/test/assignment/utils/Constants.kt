package com.test.assignment.utils

import android.content.Context
import java.text.DecimalFormat

class Constants{
    companion object{
        val API_KEY = "c8915cfe6b29981c6bab191fc2ab6491"
        val LANGAUGE = "en-US"
        val BASE_IMG_URL = "https://image.tmdb.org/t/p/w200"

        fun getKeyWord(i:Int):String{
            when(i){
                0 -> return "popular"
                1 -> return "now_playing"
            }
            return ""
        }

        fun getTitile(i:Int):String{
            when(i){
                0 -> return "Popoular"
                1 -> return "Now Playing"
            }
            return ""
        }

        var formatter = DecimalFormat("#,###,###")

        fun getWidth(i:Int,context: Context):Int{
            val scale = context.getResources().getDisplayMetrics().density
            val px = (100 * scale + 0.5f).toInt()
            return px;
        }


    }
}