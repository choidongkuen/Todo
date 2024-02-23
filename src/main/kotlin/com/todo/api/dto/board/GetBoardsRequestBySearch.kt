package com.todo.api.dto.board

import com.todo.service.board.dto.GetBoardsRequestDto

// Board 목록 조회 요청 DTO
// 동적 쿼리 : title,createdBy,firstTag
data class GetBoardsRequestBySearch(
    val title: String? = null,
    val createdBy: String? = null,
    val firstTag: String? = null,
)

fun GetBoardsRequestBySearch.toDto() =
    GetBoardsRequestDto(
        title = title,
        createdBy = createdBy,
        firstTag = firstTag,
    )
