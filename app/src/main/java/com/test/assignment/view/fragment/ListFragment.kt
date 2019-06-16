package com.test.assignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.test.assignment.R
import com.test.assignment.mvvm.model.ListItem
import com.test.assignment.utils.Pager
import com.test.assignment.mvvm.viewmodel.ListViewModel
import com.test.assignment.utils.Constants
import com.test.gambit.views.adapter.ListerAdapter
import kotlinx.android.synthetic.main.fragment_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment(val i: Int) : Fragment(), Pager, Observer<List<ListItem>> {
    lateinit var adapter: ListerAdapter
    private lateinit var listerViewModel: ListViewModel
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rcv_lister.layoutManager = GridLayoutManager(activity,2)

        listerViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        listerViewModel.init(activity!!)
        listerViewModel.getLister().observe(this,this)

        adapter = ListerAdapter(activity!!,this)
        rcv_lister.adapter = adapter
        listerViewModel.fetch(1, Constants.getKeyWord(i))
    }

    override fun pageEnded() {
        listerViewModel.fetch(adapter.current_page+1, Constants.getKeyWord(0))
    }

    override fun onChanged(t: List<ListItem>?) {
        adapter.data = t!!
        adapter.notifyDataSetChanged()
    }
}
