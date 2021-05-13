package com.example.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.`interface`.CommentListener
import com.example.myapplication.datafactory.MainViewModelFactory
import com.example.myapplication.networkcall.ApiServices
import com.example.myapplication.response.model.DetailsModel
import com.example.myapplication.ui.activity.MainActivity
import com.example.myapplication.ui.adapter.HomeListAdapter
import com.example.myapplication.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), CommentListener {

    //viewmodel init
    private val viewModel by activityViewModels<HomeViewModel> { MainViewModelFactory(ApiServices.getApiService()) }
    lateinit var mainListAdapter: HomeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to avoid repeated setof data
        lifecycleScope.launchWhenResumed {
            setupList() // set list
            setupView()// set view
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupList() {
        mainListAdapter = HomeListAdapter()
        recyclerViewList.apply {
            adapter = mainListAdapter
        }
        mainListAdapter.commentListener = this
    }

    private fun setupView() {
        lifecycleScope.launch {
            // get the response and set it in adapter
            viewModel.getPageingSingleVn().collectLatest {
                mainListAdapter.submitData(it)
            }
        }
    }

    // interface to have the data using viewmodel and fragment api
    override fun onsend(detailsModel: DetailsModel, position: Int) {
        viewModel.setComments(detailsModel)
        val fragment: Fragment = DetailFragment()

        (requireActivity() as MainActivity).fm.beginTransaction()
            .add(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }
}