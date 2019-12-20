package com.ygoular.commits.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ygoular.commits.R
import com.ygoular.commits.viewmodel.CommitViewModel
import kotlinx.android.synthetic.main.commit_fragment.*

class CommitFragment : Fragment() {

    private val mViewModel: CommitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.commit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCommits()
    }

    private fun fetchCommits() {
        mViewModel.getCommitList(
            "torvalds/linux",
            onSuccess = { list -> text_default_message.text = list[0].toString()},
            onError = { error -> text_default_message.text = error.mMessage })
    }

}
