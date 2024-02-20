package com.todo.api

import com.todo.api.model.TodoListResponse
import com.todo.api.model.TodoRequest
import com.todo.api.model.TodoResponse
import com.todo.service.TodoService
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoService: TodoService,
) {

    // 모든 todo list 조회
    @GetMapping
    fun getAll() =
        ok(TodoListResponse.of(todoService.findAll()))

    // 특정 todo 조회
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
        ok(TodoResponse.of(todoService.findById(id)))

    // todo 생성
    @PostMapping
    fun create(@RequestBody request: TodoRequest) =
        ok(todoService.create(request))

    // todo 업데이트
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: TodoRequest,
    ) = ok(TodoResponse.of(todoService.update(id, request)))

    // todo 삭제
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ) {
        todoService.delete(id)
    }
}
