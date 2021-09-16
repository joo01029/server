package com.map.map.domain.entity

import java.util.*
import javax.persistence.*

@Entity
class Visited {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "building_idx", nullable = false)
    var building: Building? = null

    @Column(nullable = false)
    var date: Date? = Date()
}