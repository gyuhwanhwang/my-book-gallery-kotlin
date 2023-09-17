package com.mybookgal.mybookgallery.application.service

import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQuery
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryBookItem
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryRequest
import com.mybookgal.mybookgallery.application.port.`in`.GetExternalBookInfoQueryResponse
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfo
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoBookItem
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoRequest
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoResponse
import org.springframework.stereotype.Service

@Service
class SearchExternalBookInfoService(private val loadExternalBookInfo: LoadExternalBookInfo): GetExternalBookInfoQuery {

    override fun getBookInfo(request: GetExternalBookInfoQueryRequest): GetExternalBookInfoQueryResponse {
        val outgoingResponse = loadExternalBookInfo.loadBookInfo(request.toOutgoingRequest())
        return outgoingResponse.toIngoingResponse()
    }
}

private fun GetExternalBookInfoQueryRequest.toOutgoingRequest(): LoadExternalBookInfoRequest {
    return LoadExternalBookInfoRequest(
        query = this.query,
        display = this.display,
        start = this.start,
        sort = this.sort
    )
}

private fun LoadExternalBookInfoResponse.toIngoingResponse(): GetExternalBookInfoQueryResponse {
    return GetExternalBookInfoQueryResponse(
        lastBuildDate = this.lastBuildDate,
        total = this.total,
        start = this.start,
        display = this.display,
        items = this.items.map { it.toIngoingItem() }
    )
}

private fun LoadExternalBookInfoBookItem.toIngoingItem(): GetExternalBookInfoQueryBookItem {
    return GetExternalBookInfoQueryBookItem(
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