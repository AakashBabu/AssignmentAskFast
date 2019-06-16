package com.test.assignment.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.assignment.R
import com.test.gambit.views.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_home_activtiy.*

class HomeActivtiy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activtiy)
        setupUI()
    }

    private fun setupUI() {
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
