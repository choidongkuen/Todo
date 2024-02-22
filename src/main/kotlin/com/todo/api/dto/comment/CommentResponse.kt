package com.todo.api.dto.comment

import com.todo.domain.entity.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)

fun Comment.toResponse() =
    CommentResponse(
        id = id ?: 0L,
        content = content,
        createdBy = createdBy,
        createdAt = createdAt,
    )
