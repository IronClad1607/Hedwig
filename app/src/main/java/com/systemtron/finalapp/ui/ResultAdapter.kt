package com.systemtron.finalapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.systemtron.finalapp.R
import com.systemtron.finalapp.modals.Item
import kotlinx.android.synthetic.main.cvresult.view.*

class ResultAdapter(private val results: ArrayList<Item>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvresult, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            with(itemView) {
                tvURL.text = item.link
                tvTitle.text = item.title
                tvsnippet.text = item.snippet
            }
        }
    }
}