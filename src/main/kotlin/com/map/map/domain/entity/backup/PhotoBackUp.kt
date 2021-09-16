package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class PhotoBackUp {
    @Id
    var idx: Long? = null

    @Column(nullable = false)
    var filed: String? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}