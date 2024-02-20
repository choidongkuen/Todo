package com.todo.api.model.board

// Board 목록 조회 요청 DTO
data class GetBoardsRequest(
    val title: String?,
    val createdBy: String?,
)
