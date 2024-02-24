package com.todo.service.board.dto

data class GetBoardsRequestDto(
    val title: String? = null,
    val createdBy: String? = null,
    val firstTag: String? = null,
)
