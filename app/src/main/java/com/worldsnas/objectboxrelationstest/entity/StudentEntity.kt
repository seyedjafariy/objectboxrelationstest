package com.worldsnas.objectboxrelationstest.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import io.objectbox.relation.ToOne

@Entity
class StudentEntity {

    @Id(assignable = true)
    var id : Long = 0
    @NameInDb("name")
    var name : String = ""
    @NameInDb("address")
    var address : String = ""
    @NameInDb("phone")
    var phone : Int = 0

    lateinit var teacher : ToOne<TeacherEntity>
}