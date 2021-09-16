package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class FollowBackUp {
    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(nullable = false)
    var follower:UserBackUp? = null

    @ManyToOne
    @JoinColumn(nullable = false)
    var following:UserBackUp? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}