package com.map.map.domain.entity.backup

import java.util.*
import javax.persistence.*

@Entity
class BuildingBackUp {
    @Id
    var id: Long? = null

    @Column(nullable = false)
    var Do: String? = null

    @Column(nullable = false)
    var si: String? = null

    @Column(nullable = false)
    var dong: String? = null

    @Column(nullable = false)
    var address: String? = null

    //위도
    @Column(nullable = false)
    var latitude: Double? = null

    //경도
    @Column(nullable = false)
    var longitude: Double? = null

    @Column
    var updatedDate: Date? = null

    @Column
    var deletedDate: Date? = null

    @OneToMany(mappedBy = "building")
    var visitor: MutableList<VisitedBackUp> = mutableListOf()

    @OneToMany(mappedBy = "building")
    var albums: MutableList<AlbumBackUp> = mutableListOf()


}