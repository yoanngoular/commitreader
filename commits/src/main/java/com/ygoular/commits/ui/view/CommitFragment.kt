package com.ygoular.commits.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ygoular.commits.BaseApplication
import com.ygoular.commits.R
import com.ygoular.commits.data.model.CommitResponse
import com.ygoular.commits.model.Commit
import com.ygoular.commits.viewmodel.CommitViewModel
import kotlinx.android.synthetic.main.commit_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        postRequest()
    }

    private fun postRequest() {
        BaseApplication.mApplicationComponent
            .githubService()
            .getCommitList("torvalds/linux")
            .enqueue(object : Callback<List<CommitResponse>> {
                override fun onFailure(call: Call<List<CommitResponse>>, t: Throwable) {
                    text_default_message.text = t.message
                }
                override fun onResponse(
                    call: Call<List<CommitResponse>>,
                    response: Response<List<CommitResponse>>
                ) {
                    text_default_message.text = Commit.fromCommitResponse(response.body()?.get(0)).toString()
                }
            })
    }

}
