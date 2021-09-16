package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class ViewedBackUp {
    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: UserBackUp? = null

    @ManyToOne
    @JoinColumn(name = "album_idx", nullable = false)
    var album: AlbumBackUp? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}