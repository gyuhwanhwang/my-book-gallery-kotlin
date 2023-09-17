package com.mybookgal.mybookgallery.adapter.`in`.web

import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQuery
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryBookItem
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryRequest
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SearchBookInfoController(private val externalQuery: GetExternalBookInfoQuery) {

    @GetMapping("/external/search/book")
    fun searchBook(request: SearchBookInfoRequest): ResponseEntity<SearchBookInfoResponse> {
        val adapterResponse = externalQuery.getBookInfo(request.toPortRequest()).toAdapterResponse()
        return ResponseEntity(adapterResponse, HttpStatus.OK)
    }
}

data class SearchBookInfoRequest(
    val query: String,
    val display: Int?,
    val start: Int?,
    val sort: String?,
)

data class SearchBookInfoResponse(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<SearchBookInfoBookItem>
)

data class SearchBookInfoBookItem(
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

private fun SearchBookInfoRequest.toPortRequest(): GetExternalBookInfoQueryRequest {
    return GetExternalBookInfoQueryRequest(
        query = this.query,
        display = this.display,
        start = this.start,
        sort = this.sort
    )
}

private fun GetExternalBookInfoQueryResponse.toAdapterResponse(): SearchBookInfoResponse {
    return SearchBookInfoResponse(
        lastBuildDate = this.lastBuildDate,
        total = this.total,
        start = this.start,
        display = this.display,
        items = this.items.map { it.toAdapterItem() }
    )
}

private fun GetExternalBookInfoQueryBookItem.toAdapterItem(): SearchBookInfoBookItem {
    return SearchBookInfoBookItem(
        title = this.title,
        link = this.link,
        image = this.image,
        author = this.author,
        discount = this.discount,
        publisher = this.publisher,
        isbn = this.isbn,
        description = this.description,
        publishDate = this.publishDate
    )
}