package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class AlbumLikeBackUp {
    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn
    var album: AlbumBackUp? = null

    @ManyToOne
    @JoinColumn
    var user: UserBackUp? = null

    @Column(nullable = false)
    var isState: Boolean? = false

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}