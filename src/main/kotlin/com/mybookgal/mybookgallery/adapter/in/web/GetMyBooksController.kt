package com.mybookgal.mybookgallery.adapter.`in`.web

import com.mybookgal.mybookgallery.adapter.`in`.web.GetMyBooksController.GetMyBooksResponse
import com.mybookgal.mybookgallery.application.port.`in`.GetMyBooksQuery
import com.mybookgal.mybookgallery.domain.MyBook
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class GetMyBooksController(private val getMyBooksQuery: GetMyBooksQuery) {

    data class GetMyBooksResponse(
        val id: Long?,
        val title: String,
        val authorName: String,
        val content: String,
        val addedAt: LocalDateTime
    )

    @GetMapping("/v1/mybooks")
    fun getLatestMyBooksV1(): ResponseEntity<List<GetMyBooksResponse>> {
        val myBooks = getMyBooksQuery.getMyBooks().map { it.toGetMyBooksResponse() }
        return ResponseEntity.ok(myBooks)
    }

    @GetMapping("/v2/mybooks")
    fun getLatestMyBooksV2(pageable: Pageable): ResponseEntity<Page<GetMyBooksResponse>> {
        val myBooksByPage = getMyBooksQuery.getMyBooksByPage(pageable).map { it.toGetMyBooksResponse() }
        return ResponseEntity.ok(myBooksByPage)
    }
}

fun MyBook.toGetMyBooksResponse(): GetMyBooksResponse {
    return GetMyBooksResponse(
        id = this.id,
        title = this.title,
        authorName = this.author.name,
        content = this.content,
        addedAt = this.addedAt
    )
}