package com.map.map.domain.entity

import javax.persistence.*

@Entity
class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(nullable = false)
    var follower:User? = null

    @ManyToOne
    @JoinColumn(nullable = false)
    var following:User? = null
}