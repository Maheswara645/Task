package com.example.myapplication.ui.fragment

import android.app.FragmentTransaction
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.datafactory.MainViewModelFactory
import com.example.myapplication.helper.GlideImageLoader
import com.example.myapplication.networkcall.ApiServices
import com.example.myapplication.ui.activity.MainActivity
import com.example.myapplication.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    //sharedview model between two fragments
    private val viewModel by activityViewModels<HomeViewModel> { MainViewModelFactory(ApiServices.getApiService()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).bottomNavigation.visibility = View.GONE
        viewModel.commentLiveData.observe(viewLifecycleOwner, Observer {
            comments.setText(it.comment)
            name.setText(it.name)
            GlideImageLoader(requireContext()).load(
                it.image.toString(),
                userImg
            )

        })
    }
// the first lifecyle call method in fragment
    override fun onAttach(context: Context) {
        super.onAttach(context)
    //oncliking backbutton
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                (requireActivity() as MainActivity).bottomNavigation.visibility = View.VISIBLE
                (requireActivity() as MainActivity).fm.popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }

}