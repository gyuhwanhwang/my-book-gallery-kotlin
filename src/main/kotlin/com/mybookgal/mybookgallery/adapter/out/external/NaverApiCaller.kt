package com.mybookgal.mybookgallery.adapter.out.external

import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfo
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoBookItem
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoRequest
import com.mybookgal.mybookgallery.application.port.out.LoadExternalBookInfoResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

@Component
class NaverApiCaller(externalApiProvider: ExternalApiProvider,
                    val restTemplate: RestTemplate): LoadExternalBookInfo {

    private val log = LoggerFactory.getLogger(NaverApiCaller::class.java)
    private val naver = externalApiProvider.naver

    override fun loadBookInfo(request: LoadExternalBookInfoRequest): LoadExternalBookInfoResponse {

        val uriComponents = getUriComponents(request)
        log.info("request to Naver API = {}", uriComponents.toUriString())

        val httpEntity = getHttpEntity()

        val response: ResponseEntity<NaverBookResponse> = restTemplate.exchange(
            uriComponents.toUriString(),
            HttpMethod.GET,
            httpEntity,
            NaverBookResponse::class.java
        )

        return response.body?.toPortResponse() ?: throw RuntimeException("API Response is empty")
    }

    private fun getHttpEntity(): HttpEntity<String> {
        val headers = HttpHeaders()
        headers.set(naver.header.keyClientId, naver.credential.clientId)
        headers.set(naver.header.keyClientSecret, naver.credential.restApiKey)
        return HttpEntity(headers)
    }

    private fun getUriComponents(request: LoadExternalBookInfoRequest): UriComponents {
        return UriComponentsBuilder.fromHttpUrl(naver.baseUrl)
            .apply {
                queryParam("query", request.query)
                request.display?.let { queryParam("display", it) }
                request.start?.let { queryParam("start", it) }
                request.sort?.let { queryParam("sort", it) }
            }
            .build()
    }
}

data class NaverBookResponse(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<NaverBookItem>
)

data class NaverBookItem(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: Long,
    val publisher: String,
    val isbn: Long,
    val description: String,
    val pubdate: String
)

private fun NaverBookResponse.toPortResponse(): LoadExternalBookInfoResponse {
    return LoadExternalBookInfoResponse(
        lastBuildDate = this.lastBuildDate,
        total = this.total,
        start = this.start,
        display = this.display,
        items = this.items.map { it.toPortItem() }
    )
}

private fun NaverBookItem.toPortItem(): LoadExternalBookInfoBookItem {
    return LoadExternalBookInfoBookItem(
        title = this.title,
        link = this.link,
        image = this.image,
        author = this.author,
        discount = this.discount,
        publisher = this.publisher,
        isbn = this.isbn,
        description = this.description,
        publishDate = this.pubdate
    )
}