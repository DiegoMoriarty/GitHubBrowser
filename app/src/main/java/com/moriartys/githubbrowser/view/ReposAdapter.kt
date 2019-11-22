package com.moriartys.githubbrowser.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moriartys.githubbrowser.R
import com.moriartys.githubbrowser.viewModel.RepoPresentationModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repo_item.view.*

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    private var list: MutableList<RepoPresentationModel> = mutableListOf()

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(repoData: RepoPresentationModel) {
            with(containerView) {
                Glide.with(context).load(repoData.avatarUrl).into(containerView.avatar!!)
                name.text = repoData.name
                description.text = repoData.description
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updateData(repos: List<RepoPresentationModel>) {
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

}