package com.todo.domain.entity

import com.todo.core.entity.BaseTimeEntity
import jakarta.persistence.*

@Entity(name = "boards")
class Board(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "title", length = 50, nullable = false)
    val title: String,

    @Lob
    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "created_by", length = 50)
    val createdBy: String,

    @Column(name = "updated_by", length = 50)
    val updatedBy: String,

) : BaseTimeEntity()
