package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class CommentBackUp {
    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: UserBackUp? = null

    @Column(nullable = false)
    var content: String? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}