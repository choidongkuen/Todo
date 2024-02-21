package com.todo.api.dto.comment

// 댓글 수정 요청 DTO
data class UpdateCommentRequest(
    val content: String,
    val updatedBy: String
)
