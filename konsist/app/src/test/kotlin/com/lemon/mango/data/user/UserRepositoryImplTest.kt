package com.lemon.mango.data.user

import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.user.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

class UserRepositoryImplTest {
    private val userJpaEntityToUserMapper: UserJpaEntityToUserMapper = mockk()
    private val userJpaRepository: UserJpaRepository = mockk()
    private val userToUserJpaEntityMapper: UserToUserJpaEntityMapper = mockk()

    private val sut = UserRepositoryImpl(
        userJpaEntityToUserMapper,
        userJpaRepository,
        userToUserJpaEntityMapper,
    )

    @Test
    fun `saveUser() adds user`() {
        // given
        val user: User = mockk()
        val userJpaEntity: UserJpaEntity = mockk()
        every { userToUserJpaEntityMapper(user) } returns userJpaEntity
        every { userJpaRepository.save(userJpaEntity) } returns userJpaEntity
        every { userJpaEntityToUserMapper(userJpaEntity) } returns user

        // when
        sut.saveUser(user)

        // then
        verify { userJpaRepository.save(userJpaEntity) }
    }

    @Test
    fun `saveUser() returns user`() {
        // given
        val user: User = mockk()
        val userJpaEntity: UserJpaEntity = mockk()
        every { userToUserJpaEntityMapper(user) } returns userJpaEntity
        every { userJpaRepository.save(userJpaEntity) } returns userJpaEntity
        every { userJpaEntityToUserMapper(userJpaEntity) } returns user

        // when
        val actual = sut.saveUser(user)

        // then
        actual shouldBeEqualTo user
    }

    @Test
    fun `getUser() returns user when it exist`() {
        // given
        val userId = getUserId1()
        val user: User = mockk()
        val userJpaEntity: UserJpaEntity = mockk()
        every { userJpaRepository.findById(userId.value).getOrNull() } returns userJpaEntity
        every { userJpaEntityToUserMapper(userJpaEntity) } returns user

        // when
        val actual = sut.getUser(userId)

        // then
        actual shouldBeEqualTo user
    }

    @Test
    fun `getUser() returns null when user doesn't exist`() {
        // given
        val userId = getUserId1()
        every { userJpaRepository.findById(userId.value).getOrNull() } returns null

        // when
        val actual = sut.getUser(userId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `containsUser() returns true when user exist`() {
        // given
        val userId = getUserId1()
        every { userJpaRepository.existsById(userId.value) } returns true

        // when
        val actual = sut.containsUser(userId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsUser() returns false when user doesn't exist`() {
        // given
        val userId = getUserId1()
        every { userJpaRepository.existsById(userId.value) } returns false

        // when
        val actual = sut.containsUser(userId)

        // then
        actual shouldBeEqualTo false
    }
}
