package com.todo.api.model.board

import java.time.LocalDateTime

// Board 상세 조회 응답 DTO
data class GetBoardDetailResponse(
    val id: Long?,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
)


fun GetBoardDetailResponse.toResponse() = GetBoardDetailResponse(
    id = id,
    title = title,
    content = content,
    createdBy = createdBy,
    createdAt = createdAt,
)
