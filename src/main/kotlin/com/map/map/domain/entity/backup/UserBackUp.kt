package com.map.map.domain.entity.backup

import com.map.map.enum.Gender
import java.util.*
import javax.persistence.*

@Entity
class UserBackUp {
    @Id
    var idx: Long? = null

    @Column(unique = true)
    var id: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var gender: Gender = Gender.Male

    @Column(nullable = false)
    var birthDate: Date? = null;

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null

    @OneToMany(mappedBy = "user")
    var visited: MutableList<VisitedBackUp> = mutableListOf()

    @OneToMany(mappedBy = "user")
    var albums: MutableList<AlbumBackUp> = mutableListOf()

    @OneToMany(mappedBy = "user")
    var viewed: MutableList<ViewedBackUp> = mutableListOf()
}