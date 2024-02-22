package com.todo.service.board.dto

import com.todo.domain.entity.Board

data class CreateBoardRequestDto(
    val title: String,
    val content: String,
    val createdBy: String,
    val tags: List<String>,
)

fun CreateBoardRequestDto.toEntity(): Board =
    Board(
        title = title,
        content = content,
        createdBy = createdBy,
        tags = tags,
    )
