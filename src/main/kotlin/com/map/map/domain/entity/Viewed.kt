package com.map.map.domain.entity

import javax.persistence.*

@Entity
class Viewed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "album_idx", nullable = false)
    var album: Album? = null
}