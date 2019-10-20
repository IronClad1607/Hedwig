package com.systemtron.finalapp.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.systemtron.finalapp.R
import com.systemtron.finalapp.modals.Item
import kotlinx.android.synthetic.main.cvresult.view.*
import java.lang.NullPointerException

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
                try {
                    Picasso.get().load(item.pagemap.cse_image[0].src).fit().into(imgThumb)
                } catch (e: NullPointerException) {
                    imgThumb.setColorFilter(Color.WHITE)
                }

                try {
                    Picasso.get().load(item.pagemap.cse_thumbnail[0].src).fit().into(imgSnippet)
                } catch (e: NullPointerException) {
                    imgSnippet.setColorFilter(Color.WHITE)
                }


                setOnClickListener {
                    val i = Intent()
                    i.action = Intent.ACTION_VIEW
                    i.data = Uri.parse(item.link)

                    try {
                        startActivity(context, i, null)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "Cant Move Forward!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}