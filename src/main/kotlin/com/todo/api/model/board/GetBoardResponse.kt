package com.todo.api.model.board

import java.time.LocalDateTime

// Board 상세 조회 응답 DTO
data class GetBoardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val updatedBy: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
