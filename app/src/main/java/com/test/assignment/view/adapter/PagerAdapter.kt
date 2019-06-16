package com.test.gambit.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.test.assignment.utils.Constants.Companion.getTitile
import com.test.assignment.view.fragment.ListFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        if (position == 0)
            return ListFragment(0)
        else
            return ListFragment(1)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getTitile(position)
    }

    override fun getCount(): Int = 2


}