package com.todo.api.dto.comment

// 댓글 생성 요청 DTO
data class CreateCommentRequest(
    val content: String,
    val createdBy: String
)
