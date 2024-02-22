package com.todo.service.comment.dto

data class UpdateCommentRequestDto(
    val content: String,
    val updatedBy: String,
)
