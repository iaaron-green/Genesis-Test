package com.sverbusoft.genesis_test.ui.fragments.favorite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sverbusoft.genesis_test.R
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import com.sverbusoft.genesis_test.ui.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), FavoriteAdapter.ItemClickListener {
    private lateinit var adapter: FavoriteAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    private var pagedList: PagedList<ReposModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
            ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        adapter = FavoriteAdapter(this)

        return root
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
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    favoriteViewModel.searchRepos(s.toString())
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
        favoriteViewModel.reposPages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            //adapter.notifyDataSetChanged()
            pagedList = it
            swipe_refresh_layout.isRefreshing = false
        })
    }

    override fun onItemDelete(repos: ReposModel) {
        favoriteViewModel.deleteFromFavorite(repos)
    }
}