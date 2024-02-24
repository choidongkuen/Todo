package com.todo.domain.entity

import com.todo.core.entity.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "likes")
class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    board: Board,
) : BaseTimeEntity() {
    @ManyToOne
    @JoinColumn(name = "booard_id")
    var board: Board = board
        protected set
}
