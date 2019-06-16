package com.test.assignment.view.activity

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.assignment.R
import com.test.assignment.mvvm.model.DetailModel
import com.test.assignment.mvvm.viewmodel.DetailViewModel
import com.test.assignment.utils.Constants
import com.test.assignment.utils.Constants.Companion.formatter
import com.test.assignment.utils.MapperFlow
import com.test.assignment.utils.ResponceHandler
import com.test.gambit.views.adapter.ProductionAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_view.*
import kotlinx.android.synthetic.main.content_detail_view.*


class DetailActivity : AppCompatActivity(), Observer<DetailModel>, LifecycleOwner {

    private var id: Int = 0
    private val lifecycleRegistry = LifecycleRegistry(this)


    private lateinit var listerViewModel: DetailViewModel
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var model: DetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        id = intent.getIntExtra(MapperFlow.ID,0)
        callDetailModel()
    }

    private fun callDetailModel() {
        listerViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        listerViewModel.init(this)
        listerViewModel.getDetailModel().observe(this, this)
        listerViewModel.fetch(id)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    private fun setType() {
        if (model.adult) {
            txt_status.setTextColor(ContextCompat.getColor(this, R.color.red));
            txt_status.setText("A")
        } else {
            txt_status.setTextColor(ContextCompat.getColor(this, R.color.green));
            txt_status.setText("UA")
        }
    }

    private fun setUpCompany() {
        Log.v("responce", "model.production_companies --" + model.production_companies.size)
        rv_product.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_product.adapter = ProductionAdapter(this, model.production_companies)
    }


    private fun setUpTitle() {
        toolbar.title = model.title
        toolbar_layout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.transparent));
        toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.txt_white));
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onChanged(t: DetailModel?) {
        model = t as DetailModel
        setUpTitle()
        txt_heading_title.setText(model.title)
        txt_genere.setText(model.getGenere())
        txt_running.setText(Html.fromHtml(resources.getString(R.string.running_time) + "<br>" + model.runtime + " Mins"))
        txt_release.setText(Html.fromHtml(resources.getString(R.string.release_date) + "<br>" + model.release_date))
        txt_rating_score.setText(
            Html.fromHtml(
                resources.getString(R.string.rating_score) + "<br>" + model.vote_average + " (" + formatter.format(
                    model.vote_count
                ) + " votes)"
            )
        )
        txt_synopsis.setText(model.overview)
        setType()
        setUpCompany()
        Glide.with(this).load(Constants.BASE_IMG_URL + model.poster_path).into(img_banner)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
