package com.todo.api.model.board

// Board 수정 요청 DTO
data class UpdateBoardRequest(
    val title: String,
    val content: String,
    val updatedBy: String,
)
