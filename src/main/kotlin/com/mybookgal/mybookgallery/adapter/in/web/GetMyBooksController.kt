package com.mybookgal.mybookgallery.adapter.`in`.web

import com.mybookgal.mybookgallery.adapter.`in`.web.GetMyBooksController.GetMyBooksResponse
import com.mybookgal.mybookgallery.application.port.`in`.GetMyBooksQuery
import com.mybookgal.mybookgallery.domain.MyBook
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/mybooks")
class GetMyBooksController(private val getMyBooksQuery: GetMyBooksQuery) {

    data class GetMyBooksResponse(
        val id: Long?,
        val title: String,
        val authorName: String,
        val content: String,
        val addedAt: LocalDateTime
    )

    @GetMapping("/")
    fun getLatestMyBooks(): ResponseEntity<List<GetMyBooksResponse>> {
        val myBooks = getMyBooksQuery.getMyBooks().map { it.toGetMyBooksResponse() }
        return ResponseEntity.ok(myBooks)
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