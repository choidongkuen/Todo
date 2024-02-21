package com.todo.api.dto.board

import com.todo.api.dto.comment.CommentResponse
import java.time.LocalDateTime

// Board 상세 조회 응답 DTO
data class GetBoardDetailResponse(
    val id: Long?,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val comments: List<CommentResponse> = emptyList()
)

fun GetBoardDetailResponse.toResponse() = GetBoardDetailResponse(
    id = id,
    title = title,
    content = content,
    createdBy = createdBy,
    createdAt = createdAt
)
