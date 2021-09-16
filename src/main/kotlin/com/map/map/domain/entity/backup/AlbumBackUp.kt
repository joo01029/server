package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class AlbumBackUp {

    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: UserBackUp? = null

    @ManyToOne
    @JoinColumn(name = "building_idx", nullable = false)
    var building: BuildingBackUp? = null

    // 앨범 메모
    @Column(nullable = false)
    var memo: String? = null

    @Column(nullable = false)
    var date: Date? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null

    @OneToMany(mappedBy = "album")
    var likes: MutableList<AlbumLikeBackUp> = mutableListOf()

    @OneToMany(mappedBy = "album")
    var viewed: MutableList<ViewedBackUp> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "album_idx")
    var comments: MutableList<CommentBackUp> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "album_idx")
    var photo: MutableList<PhotoBackUp> = mutableListOf()
}