package com.todo.domain.entity

import com.todo.api.dto.board.GetBoardResponse
import com.todo.core.entity.BaseTimeEntity
import com.todo.service.board.dto.UpdateBoardRequestDto
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

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

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()
        protected set

    fun updateBoard(request: UpdateBoardRequestDto) {
        title = request.title
        content = request.content
        updatedBy = request.updatedBy
    }

    fun toGetBoardResponse() =
        GetBoardResponse(
            id = id,
            title = title,
            createdBy = createdBy,
            createdAt = createdAt,
        )
}
