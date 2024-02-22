package com.todo.api.dto.board

import com.todo.service.board.dto.CreateBoardRequestDto

// Board 생성 요청 DTO
data class CreateBoardRequest(
    val title: String,
    val content: String,
    val createdBy: String,
    val tags: List<String> = emptyList(),
) {
    fun toCreateBoardRequestDto() =
        CreateBoardRequestDto(
            title = this.title,
            content = this.content,
            createdBy = this.createdBy,
            tags = this.tags,
        )
}
