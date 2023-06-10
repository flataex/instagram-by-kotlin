package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.dto.UserRequest
import com.flata.instagram.domain.user.dto.UserResponse
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.NoDataException
import com.flata.instagram.global.exception.NotUniqueColumnException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> =
        userRepository.findAll()
            .stream()
            .map { users ->
                UserResponse(
                    users.id,
                    users.email,
                    users.password,
                    users.nickname
                )
            }
            .toList()

    @Transactional(readOnly = true)
    fun getUser(id: Long): UserResponse =
        userRepository.findById(id)
            .orElseThrow { throw NoDataException() }
            .let {
                UserResponse(
                    it.id,
                    it.email,
                    it.password,
                    it.nickname
                )
            }


    @Transactional
    fun saveUser(userRequest: UserRequest): Long =
        run {
            validateEmail(userRequest.email)
            validateNickname(userRequest.nickname)

            userRepository.save(userRequest.toEntity()).id
        }

    private fun validateEmail(email: String) =
        check(userRepository.existsByEmail(email)) {
            throw NotUniqueColumnException()
        }

    private fun validateNickname(nickname: String) =
        check(userRepository.existsByNickname(nickname)) {
            throw NotUniqueColumnException()
        }

    @Transactional
    fun updateUser(userRequest: UserRequest) =
        userRepository.findById(userRequest.id)
            .orElseThrow { throw NoDataException() }
            .update(
                userRequest.email,
                userRequest.password,
                userRequest.nickname
            )


    @Transactional
    fun deleteUser(userRequest: UserRequest) =
        userRepository.deleteById(userRequest.id)
}
