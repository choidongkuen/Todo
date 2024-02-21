package com.todo.domain.entity

import com.todo.core.entity.BaseTimeEntity
import com.todo.service.board.dto.UpdateBoardRequestDto
import jakarta.persistence.*

@Entity(name = "boards")
open class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    title: String,

    content: String,

    createdBy: String,
) : BaseTimeEntity() {
    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", nullable = false)
    var content: String = content
        protected set

    @Column(name = "created_by", nullable = false)
    var createdBy: String = createdBy
        protected set

    @Column(name = "updated_by")
    var updatedBy: String? = null
        protected set

    fun updateBoard(request: UpdateBoardRequestDto) {
        title = request.title
        content = request.content
        updatedBy = request.updatedBy
    }
}
