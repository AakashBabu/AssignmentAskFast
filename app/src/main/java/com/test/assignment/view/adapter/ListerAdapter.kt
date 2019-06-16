package com.test.gambit.views.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.assignment.R
import com.test.assignment.mvvm.model.ListItem
import com.test.assignment.mvvm.model.ListModel
import com.test.assignment.utils.Constants.Companion.BASE_IMG_URL
import com.test.assignment.utils.MapperFlow
import com.test.assignment.utils.Pager
import kotlinx.android.synthetic.main.row_list.view.*


class ListerAdapter(
    var context: Context, var pager: Pager,
    var data: List<ListItem> = ArrayList<ListItem>(), var current_page : Int = 0
) : RecyclerView.Adapter<ListerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_list, null))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_title.setText(data.get(position).title)
        holder.txt_rating.setText(""+data.get(position).vote_average)
        holder.txt_rating.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite, 0, 0, 0);
        Glide.with(context).load(BASE_IMG_URL+data.get(position).poster_path)
            .into(holder.img_poster)
        holder.ll_parent.setOnClickListener {
            MapperFlow.moveToDetailPage(context,data.get(position).id)
        }

        if(position > data.size - 3){
            pager.pageEnded()
        }
    }

    fun addList(data_set: ListModel,reset : Boolean) {
//        if(reset)
//            data.clear()
//        data.addAll(data_set.results)
//        next_page = data_set.meta.next_page
//        page_ended = (data_set.meta.total_pages == data_set.meta.current_page) || (data_set.meta.total_pages == 0)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img_poster = v.img_poster
        val txt_title = v.txt_title
        val ll_parent = v.ll_parent
        val txt_rating = v.txt_rating
    }

}