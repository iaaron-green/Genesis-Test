package com.sverbusoft.genesis_test.ui.fragments.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sverbusoft.genesis_test.R
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.databinding.FragmentReposBinding
import com.sverbusoft.genesis_test.ui.adapter.ReposAdapter
import kotlinx.android.synthetic.main.fragment_repos.*

class ReposFragment : Fragment() {
    private lateinit var reposViewModel: ReposViewModel
    private lateinit var adapter: ReposAdapter
    private lateinit var binding: FragmentReposBinding;
    private var pagedList: PagedList<ReposResponseItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reposViewModel =
            ViewModelProviders.of(this).get(ReposViewModel::class.java)
        adapter = ReposAdapter()
        binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        initUI()
        subscribeUI()
        super.onStart()
    }

    private fun initUI() {
        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            pagedList?.dataSource?.invalidate()
        }
    }

    private fun subscribeUI() {
        reposViewModel.reposPages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            pagedList = it
            binding.swipeRefreshLayout.isRefreshing = false;
        });
    }

//    override fun onItemClick(userModel: UserModel) {
//        findNavController().navigate(R.id.action_homeFragment_to_profileFragment, bundleOf(
//            Constants.BUNDLE_KEY to Gson().toJson(userModel))
//        )
//    }
}