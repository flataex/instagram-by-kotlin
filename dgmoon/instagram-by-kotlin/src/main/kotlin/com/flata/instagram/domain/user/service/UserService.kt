package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.dto.UserRequest
import com.flata.instagram.domain.user.dto.UserResponse
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.NoDataException
import com.flata.instagram.global.exception.NotUniqueColumnException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getUsers(): ResponseEntity<List<UserResponse>> =
        userRepository.findAll()
            .map { users ->
                UserResponse(
                    users.id,
                    users.email,
                    users.password,
                    users.nickname
                )
            }.let {
                ResponseEntity.ok(it)
            }

    @Transactional(readOnly = true)
    fun getUser(id: Long): ResponseEntity<UserResponse> =
        userRepository.findByIdOrNull(id)
            ?.let {
                ResponseEntity.ok(
                    UserResponse(
                        it.id,
                        it.email,
                        it.password,
                        it.nickname
                    )
                )
            } ?: throw NoDataException()

    @Transactional
    fun saveUser(userRequest: UserRequest): ResponseEntity<Unit> =
        run {
            validateEmail(userRequest.email)
            validateNickname(userRequest.nickname)

            ResponseEntity.created(
                URI.create(
                    "/user/".plus(
                        userRepository.save(userRequest.toEntity()).id
                    )
                )
            ).build()
        }

    private fun validateEmail(email: String) =
        check(!userRepository.existsByEmail(email)) {
            throw NotUniqueColumnException()
        }

    private fun validateNickname(nickname: String) =
        check(!userRepository.existsByNickname(nickname)) {
            throw NotUniqueColumnException()
        }

    @Transactional
    fun updateUser(userRequest: UserRequest): ResponseEntity<Unit> =
        userRepository.findByIdOrNull(userRequest.id)
            ?.let {
                it.update(
                    userRequest.email,
                    userRequest.password,
                    userRequest.nickname
                )
                ResponseEntity.noContent().build()
            } ?: throw NoDataException()

    @Transactional
    fun deleteUser(userRequest: UserRequest): ResponseEntity<Unit> =
        userRepository.findByIdOrNull(userRequest.id)
            ?.let {
                userRepository.deleteById(it.id)
                ResponseEntity.noContent().build()
            } ?: throw NoDataException()
}
