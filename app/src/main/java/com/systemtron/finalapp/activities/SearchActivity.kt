package com.systemtron.finalapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.systemtron.finalapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(),CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        launch {

        }
    }
}
