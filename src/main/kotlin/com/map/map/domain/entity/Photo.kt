package com.map.map.domain.entity

import javax.persistence.*

@Entity
class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @Column(nullable = false)
    var filed: String? = null
}