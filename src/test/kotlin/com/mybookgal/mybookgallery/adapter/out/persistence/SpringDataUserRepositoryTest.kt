package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.domain.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class SpringDataUserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: SpringDataUserRepository,
) {

    @Test
    fun `When findByIdOrNull then return User`() {
        val userJpaEntity = UserJpaEntity(name = "test", email = "test@emain.com", role = Role.USER)
        entityManager.persist(userJpaEntity)
        entityManager.flush()

        val foundUser = userRepository.findByIdOrNull(userJpaEntity.id!!)
        assertThat(foundUser).isEqualTo(userJpaEntity)
    }
}