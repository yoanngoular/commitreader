package com.ygoular.commitreader.ui.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ygoular.commitreader.R
import com.ygoular.commitreader.model.Commit
import kotlinx.android.synthetic.main.commit_item.view.*


class CommitAdapter : ListAdapter<Commit, CommitAdapter.CommitHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Commit>() {
            override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem.mSha1 == newItem.mSha1
            }

            override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
                return oldItem.mAuthor == newItem.mAuthor
                        && oldItem.mAuthorAvatarUrl == newItem.mAuthorAvatarUrl
                        && oldItem.mTitle == newItem.mTitle
                        && oldItem.mMessage == newItem.mMessage
            }
        }

        // If commit message takes more than 60 lines to be displayed, it won't be fully displayed
        // by choice
        private const val EXPANDED_VALUE = 60
        private const val SHRINKED_VALUE = 3
        private const val IMAGE_SIZE = 180
        private const val ANIMATION_DURATION = 500L
        private const val MAX_LINES_PROPERTY = "maxLines"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitHolder {
        return CommitHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.commit_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommitHolder, position: Int) {
        with(getItem(position)) {
            holder.mCommitSha1.text = mSha1
            holder.mCommitAuthor.text = mAuthor
            holder.mCommitTitle.text = mTitle
            holder.mCommitMessage.text = mMessage
            val picasso = Picasso.get()
            if (mAuthorAvatarUrl.isNotEmpty()) { // Placeholder image otherwise
                picasso.load(mAuthorAvatarUrl).resize(IMAGE_SIZE, IMAGE_SIZE)
                    .into(holder.mCommitAvatar)
            }
        }
    }

    inner class CommitHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var mCommitSha1: TextView = mView.text_sha_1
        var mCommitAuthor: TextView = mView.text_author
        var mCommitTitle: TextView = mView.text_title
        var mCommitMessage: TextView = mView.text_message
        var mCommitAvatar: ImageView = mView.image_avatar

        init {
            // Expand/Shrink animation when clicking on an item
            mView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (mView.text_message.lineCount == SHRINKED_VALUE) {
                        animateNewMaxLines(mView, EXPANDED_VALUE)
                    } else {
                        animateNewMaxLines(mView, SHRINKED_VALUE)
                    }
                }
            }
        }

        private fun animateNewMaxLines(mView: View, maxLines: Int) {
            val animation = ObjectAnimator.ofInt(
                mView.text_message,
                MAX_LINES_PROPERTY,
                maxLines
            )
            animation.duration = ANIMATION_DURATION
            animation.start()
        }
    }
}