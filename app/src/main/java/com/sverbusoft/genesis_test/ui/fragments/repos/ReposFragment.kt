package com.sverbusoft.genesis_test.ui.fragments.repos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

class ReposFragment : Fragment(), ReposAdapter.ItemClickListener {
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
        adapter = ReposAdapter(this)
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
        swipe_refresh_layout.setOnRefreshListener {
            pagedList?.dataSource?.invalidate()
        }
        et_search.apply {
            addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    reposViewModel.searchRepos(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

            })
        }
    }

    private fun subscribeUI() {
        reposViewModel.reposPages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            //adapter.notifyDataSetChanged()
            pagedList = it
            swipe_refresh_layout.isRefreshing = false;
        });
    }

    override fun onItemClick(repos: ReposResponseItem) {
        reposViewModel.addToFavorite(repos)
    }

//    override fun onItemClick(userModel: UserModel) {
//        findNavController().navigate(R.id.action_homeFragment_to_profileFragment, bundleOf(
//            Constants.BUNDLE_KEY to Gson().toJson(userModel))
//        )
//    }
}