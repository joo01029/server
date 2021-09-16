package com.map.map.domain.entity

import javax.persistence.*

@Entity
class AlbumLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn
    var user: User? = null

    @Column(nullable = false)
    var isState: Boolean? = false
}