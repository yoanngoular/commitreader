package com.ygoular.commitreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ygoular.commitreader.BaseApplication
import com.ygoular.commitreader.model.Commit
import com.ygoular.commitreader.repository.CommitRepository

class CommitViewModel : ViewModel() {

    private val mCommitRepository: CommitRepository =
        BaseApplication.mApplicationComponent.commitRepository()

    fun getCommitList(repository: String): LiveData<List<Commit>> =
        mCommitRepository.getCommitList(repository)
}
