package com.todo.api.dto.comment

import com.todo.service.comment.dto.UpdateCommentRequestDto

// 댓글 수정 요청 DTO
data class UpdateCommentRequest(
    val content: String,
    val updatedBy: String,
)

fun UpdateCommentRequest.toDto() =
    UpdateCommentRequestDto(
        content = content,
        updatedBy = updatedBy,
    )
