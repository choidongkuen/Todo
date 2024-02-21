package com.todo.api.dto.board

import com.todo.service.board.dto.GetBoardsRequestDto

// Board 목록 조회 요청 DTO
data class GetBoardsRequest(
    val title: String? = null,
    val createdBy: String? = null
)

fun GetBoardsRequest.toDto() = GetBoardsRequestDto(
    title = title,
    createdBy = createdBy
)
