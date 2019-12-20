package com.ygoular.commits.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ygoular.commits.R
import com.ygoular.commits.ui.adapter.CommitAdapter
import com.ygoular.commits.viewmodel.CommitViewModel
import kotlinx.android.synthetic.main.commit_fragment.*

class CommitFragment : Fragment() {

    private val mViewModel: CommitViewModel by viewModels()
    private val mCommitAdapter = CommitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.commit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_commits.layoutManager = LinearLayoutManager(context)
        recycler_view_commits.setHasFixedSize(true)
        recycler_view_commits.adapter = mCommitAdapter

        layout_refresh_commits.setOnRefreshListener { fetchCommits() }

        refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.commits_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> refreshData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchCommits() {
        mViewModel.getCommitList(
            "torvalds/linux",
            onSuccess = { list ->
                mCommitAdapter.submitList(list)
                adaptUi()
            },
            onError = {
                with(
                    Snackbar.make(
                        layout_commits,
                        "${getString(R.string.error_retrieving_commits)} from ${it.mParam} repository",
                        Snackbar.LENGTH_INDEFINITE
                    )
                ) {
                    setAction("OK") { this.dismiss() }
                    show()
                }
                adaptUi(it.mMessage)
            })
    }

    private fun adaptUi(error: String = "") {
        text_error_retrieving_commits.text = error
        text_error_retrieving_commits.visibility =
            if (error.isNotEmpty()) View.VISIBLE else View.GONE
        recycler_view_commits.visibility = if (error.isNotEmpty()) View.GONE else View.VISIBLE
        layout_refresh_commits.isRefreshing = false
    }

    private fun refreshData() {
        layout_refresh_commits.isRefreshing = true
        fetchCommits()
    }

}
