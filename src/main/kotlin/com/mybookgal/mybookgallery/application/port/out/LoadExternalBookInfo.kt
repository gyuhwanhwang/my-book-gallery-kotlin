package com.mybookgal.mybookgallery.application.port.out

interface LoadExternalBookInfo {

    fun loadBookInfo(request: LoadExternalBookInfoRequest): LoadExternalBookInfoResponse
}

data class LoadExternalBookInfoRequest(
    val query: String,
    val display: Int?,
    val start: Int?,
    val sort: String?,
)

data class LoadExternalBookInfoResponse(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<LoadExternalBookInfoBookItem>
)

data class LoadExternalBookInfoBookItem(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: Long,
    val publisher: String,
    val isbn: Long,
    val description: String,
    val publishDate: String
)