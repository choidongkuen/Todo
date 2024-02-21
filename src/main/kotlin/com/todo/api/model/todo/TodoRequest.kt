package com.todo.api.model.todo

data class TodoRequest(
    val title: String,
    val description: String,
    val done: Boolean = false
)
