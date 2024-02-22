package com.todo.api.dto.todo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.todo.domain.entity.Todo

class TodoListResponse(
    val items: List<TodoResponse>,
) {
    val size: Int
        // 응답 필드에서 무시
        @JsonIgnore
        get() = items.size

    fun get(index: Int) = items[index]

    companion object {
        // List<Todo> -> List<TodoResponse>
        fun of(todoList: List<Todo>): TodoListResponse =
            TodoListResponse(todoList.map(TodoResponse.Companion::of))
    }
}
