package com.map.map.domain.entity

import javax.persistence.*

@Entity
class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    var user: User? = null

    @Column(nullable = false)
    var content: String? = null
}