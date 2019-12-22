package com.ygoular.commitreader

import com.ygoular.commitreader.data.GitHubServiceUnitTest
import com.ygoular.commitreader.model.CommitUnitTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(GitHubServiceUnitTest::class, CommitUnitTest::class)
class CommitReaderUnitTestSuite