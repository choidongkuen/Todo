package com.todo.service.comment.dto

import com.todo.domain.entity.Board
import com.todo.domain.entity.Comment

data class CreateCommentRequestDto(
    val content: String,
    val createdBy: String,
)

fun CreateCommentRequestDto.toEntity(board: Board) = Comment(
    content = content,
    createdBy = createdBy,
    board = board,
)
