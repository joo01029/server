package com.map.map.service.like

import com.map.map.domain.entity.Album
import com.map.map.domain.entity.AlbumLike
import com.map.map.domain.entity.User
import com.map.map.domain.repository.AlbumRepo
import com.map.map.domain.repository.LikeRepo
import com.map.map.domain.repository.UserRepo
import com.map.map.domain.response.like.LikedUsersRo
import com.map.map.exception.CustomHttpException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl @Autowired constructor(
    private val albumRepo: AlbumRepo,
    private val userRepo: UserRepo,
    private val likeRepo: LikeRepo,
): LikeService {


    /**
     * 앨범 좋아요 상태 변경
     */
    @Transactional
    override fun changeLikeAlbumState(userId: String, albumId: Long) {
        val user = findUserById(userId)
        val album = findAlbum(albumId)
        val albumLike = likeRepo.findAlbumLikeByAlbumAndUser(album, user).orElseGet {
            val albumLike = AlbumLike(user, album, false)
            album.likes.add(albumLike)
            albumLike
        }

        albumLike.isState = !albumLike.isState!!


        likeRepo.save(albumLike)
    }
    /**
     * 좋아요 상태 확인
     */
    @Transactional(readOnly = true)
    override fun isLike(userId: String, albumId: Long): Boolean {
        val user = findUserById(userId)
        val album = findAlbum(albumId)
        val albumLike = likeRepo.findAlbumLikeByAlbumAndUser(album, user)
            .orElse(AlbumLike(user, findAlbum(albumId), false))

        return albumLike.isState!!
    }

    /**
     * 좋아요 한 유저들 보기
     */
    @Transactional(readOnly = true)
    override fun getLikedUsers(albumId: Long): MutableList<LikedUsersRo> {
        val album = findAlbum(albumId)

        val likedUserRoList = mutableListOf<LikedUsersRo>()
        for (like in album.likes) {
            if(like.isState == true) {
                val likedUsersRo = LikedUsersRo()
                userToSimpleUserInfoRo(like.user!!, likedUsersRo)
                likedUserRoList.add(likedUsersRo)
            }
        }

        return likedUserRoList
    }

    override fun findUserById(userId: String): User {
        return userRepo.findById(userId) ?: throw CustomHttpException(HttpStatus.NOT_FOUND, "없는 사용자")
    }

    override fun findAlbum(id:Long) : Album {
        return albumRepo.findByIdx(id) ?: throw CustomHttpException(HttpStatus.NOT_FOUND, "앨범을 찾을 수 없음")

    }
}