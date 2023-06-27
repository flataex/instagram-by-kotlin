package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.model.dto.FollowRequest
import com.flata.instagram.domain.follow.model.dto.FollowerResponse
import com.flata.instagram.domain.follow.model.entity.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class FollowService(
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    fun follow(followRequest: FollowRequest,
               userId: Long): Long {

        val fromUser = userRepository.findById(userId).orElseThrow { RuntimeException("유저 아이디가 존재하지 않습니다 : $userId") }
        val toUser = userRepository.findById(followRequest.toUserId).orElseThrow { RuntimeException("팔로우할 아이디가 존재하지 않습니다 : ${followRequest.toUserId}") }

        val follow = Follow(
            fromUser = fromUser,
            toUser = toUser
        )

        return followRepository.save(follow).id ?: throw RuntimeException("저장에 실패했습니다.")
    }

    @Transactional
    fun unFollow(followRequest: FollowRequest,
                 userId: Long): Long {
        val follow = followRepository.findByFromUserIdAndToUserId(userId, followRequest.toUserId)
            ?: throw RuntimeException("팔로우 관계가 존재하지 않습니다: $userId : ${followRequest.toUserId}")

        followRepository.delete(follow)

        return follow.id ?: throw RuntimeException("삭제에 실패했습니다.")
    }

    @Transactional
    fun getFollowers(userId: Long): List<FollowerResponse> {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("해당 아이디의 유저가 존재하지 않습니다 : $userId") }
        val follows = followRepository.findByToUser(user)

        return follows.mapNotNull { follow ->
            follow.fromUser.email.let {
                FollowerResponse(it, follow.fromUser.nickname)
            }
        }
    }
}
