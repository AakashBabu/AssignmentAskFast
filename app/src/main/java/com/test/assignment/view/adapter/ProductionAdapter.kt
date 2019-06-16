package com.test.gambit.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.R
import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.model.ListModel
import com.test.assignment.mvvm.model.ProductionCompany
import com.test.assignment.utils.Constants.Companion.BASE_IMG_URL
import com.test.assignment.utils.Constants.Companion.getWidth
import com.test.assignment.utils.MapperFlow
import com.test.assignment.utils.Pager
import kotlinx.android.synthetic.main.row_list.view.*


class ProductionAdapter(
    var context: Context,
    var data: List<ProductionCompany>
) : RecyclerView.Adapter<ProductionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_list, null))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_title.setText(data.get(position).name)
        holder.ll_parent.layoutParams.width = getWidth(250,context)
        holder.img_poster.layoutParams.height = getWidth(250,context)
        Glide.with(context).load(BASE_IMG_URL+data.get(position).logo_path).error(R.drawable.ic_image_black)
            .into(holder.img_poster)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img_poster = v.img_poster
        val txt_title = v.txt_title
        val ll_parent = v.ll_parent
    }

}