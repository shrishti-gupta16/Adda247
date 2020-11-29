// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingsample.data.repositroy.UserRepository
import com.example.codingsample.databinding.FragmentUserListBinding
import com.example.codingsample.utils.ConnectionLiveData
import com.example.codingsample.utils.isConnected


/**
 * A MovieList fragment containing a recyclerview view.
 */
class UserListFragment : Fragment() {

    private val userRepository by lazy { UserRepository.getRepository(requireActivity().application) }

    private val userViewModel : UserViewModel by viewModels {
        UserViewModelFactory(requireActivity().application, userRepository)
    }

    private val listAdapter: UserListAdapter by lazy { UserListAdapter() }
    private val manager by lazy { LinearLayoutManager(context) }
    private val connectionLiveData by lazy { ConnectionLiveData(requireContext()) }

    private lateinit var viewDataBinding: FragmentUserListBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentUserListBinding.inflate(inflater, container, false).apply {
            viewModel = userViewModel
        }

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        return viewDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData.observe(this) {
            userViewModel.isNetworkAvailable.value = it
        }
        userViewModel.isNetworkAvailable.value = context?.isConnected
        userViewModel.loadUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    private fun initialize() {
        viewDataBinding.cardStackView.layoutManager = manager
        viewDataBinding.cardStackView.adapter = listAdapter
        viewDataBinding.cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    companion object {
        /**
         * The fragment representing the movie list.
         */

        @JvmStatic
        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }
}