package com.mybookgal.mybookgallery.application.port.`in`

interface GetExternalBookInfoQuery {

    fun getBookInfo(request: GetExternalBookInfoQueryRequest): GetExternalBookInfoQueryResponse
}

data class GetExternalBookInfoQueryRequest(
    val query: String,
    val display: Int?,
    val start: Int?,
    val sort: String?,
)

data class GetExternalBookInfoQueryResponse(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<GetExternalBookInfoQueryBookItem>
)

data class GetExternalBookInfoQueryBookItem(
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