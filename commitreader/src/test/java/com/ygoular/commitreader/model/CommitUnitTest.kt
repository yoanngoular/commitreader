package com.ygoular.commitreader.model

import com.ygoular.commitreader.CommitReaderUnitTest
import org.junit.Assert.*
import org.junit.Test


class CommitUnitTest : CommitReaderUnitTest() {

    companion object {
        private const val AUTHOR_AVATAR_URL =
            "https://avatars3.githubusercontent.com/u/19864447?v=4"
        private const val AUTHOR = "Yoann Goular"
        private const val TITLE = "Initial commit"
        private const val MESSAGE = "Initial commit"
        private const val SHA_1 = "9857637f310c83eb383173c4ae065c89f4239b5e"
    }

    @Test
    fun testFromCommitResponsesNotNull() {
        val commits = Commit.fromCommitResponses(getCommitListSuccess().body())

        assertNotNull(commits)

        assertTrue(commits!!.isNotEmpty())

        assertTrue(commits[1] == Commit(AUTHOR, AUTHOR_AVATAR_URL, TITLE, MESSAGE, SHA_1))
    }

    @Test
    fun testFromCommitResponsesNull() {
        assertNull(Commit.fromCommitResponses(getCommitListError().body()))
    }
}
