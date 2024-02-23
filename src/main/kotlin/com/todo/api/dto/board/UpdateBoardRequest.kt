package com.todo.api.dto.board

import com.todo.service.board.dto.UpdateBoardRequestDto

// Board 수정 요청 DTO
data class UpdateBoardRequest(
    val title: String,
    val content: String,
    val updatedBy: String,
    val tags: List<String>,
) {
    fun toUpdateBoardRequestDto() =
        UpdateBoardRequestDto(
            title = this.title,
            content = this.content,
            updatedBy = this.updatedBy,
            tags = this.tags,
        )
}
