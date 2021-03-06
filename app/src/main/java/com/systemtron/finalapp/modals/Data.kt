package com.systemtron.finalapp.modals

data class Data(
    val context: Context,
    val items: ArrayList<Item>,
    val kind: String,
    val queries: Queries,
    val searchInformation: SearchInformation,
    val url: Url
)