package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.domain.MyBook
import com.mybookgal.mybookgallery.domain.Role
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MyBookPersistenceAdapterTest() {

    private val myBookRepository = mockk<SpringDataMyBookRepository>()
    private val myBookPersistenceAdapter = MyBookPersistenceAdapter(myBookRepository)

    @Test
    fun `When load MyBooks then return List of MyBook`() {
        val userJpaEntity = UserJpaEntity(name = "test", email = "test@emain.com", role = Role.USER)

        val myBookJpaEntity1 = MyBookJpaEntity(title = "test1", content = "test content1", author = userJpaEntity)
        val myBookJpaEntity2 = MyBookJpaEntity(title = "test2", content = "test content2", author = userJpaEntity)
        val myBookEntities = listOf(myBookJpaEntity1, myBookJpaEntity2)

        every { myBookRepository.findAllByOrderByAddedAtDesc() } returns myBookEntities

        val result = myBookPersistenceAdapter.loadMyBooks()
        assertThat(result.size).isEqualTo(2)
        assertThat(result[0]).isInstanceOf(MyBook::class.java)
        assertThat(result[0].title).isEqualTo("test1")
        assertThat(result[0].content).isEqualTo("test content1")
        assertThat(result[0].author.name).isEqualTo("test")
    }
}