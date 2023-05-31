package com.flata.instagram.domain.user.service

import com.flata.instagram.domain.user.dto.UserRequest
import com.flata.instagram.domain.user.dto.UserResponse
import com.flata.instagram.domain.user.model.Users
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
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
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
    }

    @Transactional(readOnly = true)
    fun getUser(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { throw NoDataException() }
        return UserResponse(
            user.id,
            user.email,
            user.password,
            user.nickname
        )
    }

    @Transactional
    fun saveUser(userRequest: UserRequest): Long {
        validateEmail(userRequest.email)
        validateNickname(userRequest.nickname)
        return userRepository.save(userRequest.toEntity()).id
    }

    private fun validateEmail(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw NotUniqueColumnException()
        }
    }

    private fun validateNickname(nickname: String) {
        if (userRepository.existsByNickname(nickname)) {
            throw NotUniqueColumnException()
        }
    }

    @Transactional
    fun updateUser(userRequest: UserRequest) {
        val userToUpdate: Users = userRepository.findById(userRequest.id)
            .orElseThrow { throw NoDataException() }
        userToUpdate.update(
            userRequest.email,
            userRequest.password,
            userRequest.nickname
        )
    }

    @Transactional
    fun deleteUser(userRequest: UserRequest) {
        userRepository.deleteById(userRequest.id)
    }
}
