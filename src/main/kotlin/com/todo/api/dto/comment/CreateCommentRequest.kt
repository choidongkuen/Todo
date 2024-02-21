package com.todo.api.dto.comment

import com.todo.service.comment.dto.CreateCommentRequestDto

// 댓글 생성 요청 DTO
data class CreateCommentRequest(
    val content: String,
    val createdBy: String
)

fun CreateCommentRequest.toDto() = CreateCommentRequestDto(
    content = content,
    createdBy = createdBy
)
