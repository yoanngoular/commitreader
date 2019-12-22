package com.ygoular.commitreader.data

import com.ygoular.commitreader.CommitReaderUnitTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class GitHubServiceUnitTest: CommitReaderUnitTest() {

    @Test
    fun testGetCommitListSuccessful() {
        assertTrue(getCommitListSuccess().isSuccessful)
    }

    @Test
    fun testGetCommitListUnsuccessful() {
        assertFalse(getCommitListError().isSuccessful)
    }
}
