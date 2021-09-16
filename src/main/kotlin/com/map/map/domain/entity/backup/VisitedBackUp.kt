package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class VisitedBackUp {
    @Id
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: UserBackUp? = null

    @ManyToOne
    @JoinColumn(name = "building_idx", nullable = false)
    var building: BuildingBackUp? = null


    @Column(nullable = false)
    var date: Date? = Date()

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null
}