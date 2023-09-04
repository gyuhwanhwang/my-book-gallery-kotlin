package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.domain.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class SpringDataMyBookRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val myBookRepository: SpringDataMyBookRepository,
) {

    @Test
    fun `When findByIdOrNull then return MyBook`() {
        val userJpaEntity = UserJpaEntity(name = "test", email = "test@emain.com", role = Role.USER)
        entityManager.persist(userJpaEntity)

        val myBookJpaEntity = MyBookJpaEntity(title = "test", content = "test content", author = userJpaEntity)
        entityManager.persist(myBookJpaEntity)
        entityManager.flush()

        val foundMyBook = myBookRepository.findByIdOrNull(myBookJpaEntity.id!!)
        assertThat(foundMyBook).isEqualTo(myBookJpaEntity)
    }
}