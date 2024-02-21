package com.todo.api.model.board

import com.todo.domain.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

// Board 목록 조회 응답 DTO
data class GetBoardResponse(
    val id: Long?,
    val title: String,
    val createdBy: String,
    val createdAt: LocalDateTime
)

fun Page<Board>.toGetBoardResponse() = PageImpl(
    content.map {it.toGetBoardResponse()},
    pageable,
    totalElements
)
