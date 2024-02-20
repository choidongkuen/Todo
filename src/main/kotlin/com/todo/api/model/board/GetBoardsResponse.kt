package com.todo.api.model.board

// Board 목록 조회 응답 DTO
data class GetBoardsResponse(
    val id: Long,
    val title: String,
    val createdBy: String,
)
