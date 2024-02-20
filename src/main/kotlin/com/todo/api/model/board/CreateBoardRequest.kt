package com.todo.api.model.board

// Board 생성 요청 DTO
data class CreateBoardRequest(
    val title: String,
    val content: String,
    val createdBy: String,
)
