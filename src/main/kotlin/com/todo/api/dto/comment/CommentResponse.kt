package com.todo.api.dto.comment

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)
