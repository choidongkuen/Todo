package com.todo.api.dto.todo

data class TodoRequest(
    val title: String,
    val description: String,
    val done: Boolean = false
)
