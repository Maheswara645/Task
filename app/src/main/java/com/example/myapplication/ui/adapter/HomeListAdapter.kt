package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.`interface`.CommentListener
import com.example.myapplication.helper.GlideImageLoader
import com.example.myapplication.response.ResponseModelItem
import com.example.myapplication.response.model.DetailsModel
import kotlinx.android.synthetic.main.item_home_list.view.*


class HomeListAdapter :
    PagingDataAdapter<ResponseModelItem, HomeListAdapter.ViewHolder>(DataDifferntiator) {

    lateinit var commentListener: CommentListener

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object DataDifferntiator : DiffUtil.ItemCallback<ResponseModelItem>() {

        override fun areItemsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val responseModelItem = getItem(position)
        holder.itemView.name.text = getItem(position)?.name

        GlideImageLoader(holder.itemView.context).load(
            getItem(position)?.owner?.avatarUrl!!,
            holder.itemView.userImg
        )
        holder.itemView.setOnClickListener {
            commentListener.onsend(DetailsModel().apply {
                name = responseModelItem?.name
                comment = holder.itemView.comments.text.toString()
                image = responseModelItem?.owner?.avatarUrl
            }, position)
        }
    }
// to get the exact position of the item
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_list, parent, false)
        return ViewHolder(inflatedView)
    }


}