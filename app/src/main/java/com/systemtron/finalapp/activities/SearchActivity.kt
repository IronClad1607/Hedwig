package com.systemtron.finalapp.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.systemtron.finalapp.R
import com.systemtron.finalapp.modals.Item
import com.systemtron.finalapp.networks.RetrofitClient
import com.systemtron.finalapp.ui.ResultAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var iResults = 1
    var loadingMore = false
    var lastVisibleItemId = 0
    var searchStringNoSpace = ""
    var mResults = ArrayList<Item>()


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnSearch.setOnClickListener {
            val searchString = etSearch.text.toString()

            searchStringNoSpace = searchString.replace(" ", "+")
            launch {
                val lMResults =
                    LinearLayoutManager(this@SearchActivity, LinearLayout.VERTICAL, false)

                rvResults.layoutManager = lMResults

                createResult(iResults, 0, searchStringNoSpace)

                rvResults.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        lastVisibleItemId = lMResults.findLastVisibleItemPosition()
                        if (lastVisibleItemId == mResults.size - 1 && !loadingMore) {
                            loadMore(iResults + 10)
                        }
                    }
                })

            }
        }

    }

    private fun loadMore(i: Int) {
        loadingMore = true
        launch {
            createResult(i, lastVisibleItemId, searchStringNoSpace)
        }
    }

    private suspend fun createResult(startCount: Int, last: Int, url: String) {
        val resultAPI = RetrofitClient.resultAPI

        val response = resultAPI.getResults(url, startCount)
        Log.d("PUI","$response")
        if (response.isSuccessful) {
            val nResult: ArrayList<Item>? = response.body()?.items
            if (loadingMore) {
                mResults.addAll(nResult!!)
                rvResults.scrollToPosition(last)
            } else {
                mResults = nResult!!
                rvResults.adapter = ResultAdapter(mResults).apply {
                    notifyDataSetChanged()
                }
            }

            loadingMore = false
        }

    }

}
