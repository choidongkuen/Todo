package com.todo.domain.entity

import com.todo.core.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "tags")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    board: Board,
    createdBy: String,
) : BaseTimeEntity() {
    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @ManyToOne
    @JoinColumn(name = "board_id")
    var board: Board = board
        protected set

    @Column(name = "created_by")
    var createdBy: String = createdBy
        protected set
}
