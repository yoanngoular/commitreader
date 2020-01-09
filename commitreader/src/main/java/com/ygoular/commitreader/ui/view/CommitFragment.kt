package com.ygoular.commitreader.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ygoular.commitreader.BaseApplication
import com.ygoular.commitreader.R
import com.ygoular.commitreader.model.Commit
import com.ygoular.commitreader.ui.adapter.CommitAdapter
import com.ygoular.commitreader.viewmodel.CommitViewModel
import kotlinx.android.synthetic.main.commit_fragment.*

class CommitFragment : Fragment() {

    private val mViewModel: CommitViewModel by viewModels()
    private val mCommitAdapter = CommitAdapter()

    private val mSharedPreferences: SharedPreferences by lazy {
        BaseApplication.mApplicationComponent.application().getSharedPreferences(
            context?.getString(R.string.shared_pref_name),
            Context.MODE_PRIVATE
        )
    }
    private val mPrefRepository = "repository"
    private val mPrefDefaultRepository by lazy { context?.getString(R.string.default_repository) }

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

        layout_refresh_commits.setOnRefreshListener { refreshData() }

        refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.commits_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> refreshData()
            R.id.menu_change_repository -> updateRepository()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateRepository() {
        val editText = EditText(context)
        editText.setText(mSharedPreferences.getString(mPrefRepository, mPrefDefaultRepository))
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.change_repository))
                .setMessage(getString(R.string.change_repository_message))
                .setView(editText)
                .setPositiveButton(getString(R.string.done)) { _, _ ->
                    val repository = editText.text.toString()
                    mSharedPreferences.edit().putString(mPrefRepository, repository).apply()
                    refreshData()
                }
                .setNegativeButton(getString(R.string.cancel), null)
                .create()
        }?.show()
    }

    private fun fetchCommits(
        repository: String = mSharedPreferences.getString(
            mPrefRepository,
            mPrefDefaultRepository
        ).toString()
    ) {
        mViewModel.getCommitList(repository)
            .observe(viewLifecycleOwner, Observer<List<Commit>> { commitList ->
                mCommitAdapter.submitList(commitList)
                adaptUi(
                    if (commitList.isNullOrEmpty())
                        "${getString(R.string.error_no_commits_available)} $repository"
                    else ""
                )
            })
    }

    private fun adaptUi(error: String = "") {
        layout_refresh_commits.isRefreshing = false
        if (error.isNotEmpty()) {
            text_error_retrieving_commits.text = error
            text_error_retrieving_commits.visibility = View.VISIBLE
            recycler_view_commits.visibility = View.GONE
        } else {
            text_error_retrieving_commits.visibility = View.GONE
            recycler_view_commits.visibility = View.VISIBLE
        }
    }

    private fun refreshData() {
        layout_refresh_commits.isRefreshing = true
        fetchCommits()
    }
}
