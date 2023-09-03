package com.mybookgal.mybookgallery.adapter.`in`.web

import com.mybookgal.mybookgallery.application.port.`in`.GetMyBooksQuery
import com.mybookgal.mybookgallery.domain.MyBook
import com.mybookgal.mybookgallery.domain.Role
import com.mybookgal.mybookgallery.domain.User
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@WebMvcTest
class GetMyBooksControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var getMyBooksQuery: GetMyBooksQuery

    @Test
    fun `List my books`() {
        val user = User(name = "testUser", email = "test@email.com", role = Role.USER, introduction = null, profileImage = null)
        val firstMyBook = MyBook(title = "test1", content = "test1", addedAt = LocalDateTime.now(), author = user)
        val secondMyBook = MyBook(title = "test2", content = "test2", addedAt = LocalDateTime.now(), author = user)

        every { getMyBooksQuery.getMyBooks() } returns listOf(secondMyBook, firstMyBook)

        mockMvc.perform(get("/api/mybooks/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].title").value(secondMyBook.title))
            .andExpect(jsonPath("\$.[0].content").value(secondMyBook.content))
            .andExpect(jsonPath("\$.[0].authorName").value(user.name))
    }
}