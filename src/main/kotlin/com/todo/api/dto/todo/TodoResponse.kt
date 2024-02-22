package com.todo.api.dto.todo

import com.todo.domain.entity.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String,
    val done: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {

    // Todo -> TodoResponse
    companion object {
        fun of(todo: Todo?): TodoResponse {
            checkNotNull(todo) { "Todo is null" }
            checkNotNull(todo.id) { "Todo'is is null" }

            // 빌더
            return TodoResponse(
                id = todo.id,
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt,
            )
        }
    }
}
