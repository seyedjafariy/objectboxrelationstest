package com.worldsnas.objectboxrelationstest.entity

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import io.objectbox.relation.ToMany

@Entity
data class TeacherEntity(
        @Id(assignable = true)
        var id: Long = 0,
        @NameInDb("CACHE_TIME")
        var cache: Long = System.currentTimeMillis()) {
    @Backlink(to = "teacher")
    lateinit var students: ToMany<StudentEntity>
}