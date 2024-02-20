package com.todo.service


import com.todo.api.model.TodoRequest
import com.todo.domain.Todo
import com.todo.domain.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class TodoService(
    // 동일 파일, 클래스 내에서만 접근 가능
    private val todoRepository: TodoRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Todo> =
        todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))


    @Transactional(readOnly = true)
    fun findById(id: Long): Todo =
        // Kotlin 확장 함수 ( optional 대신 )
        todoRepository.findByIdOrNull(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun create(request: TodoRequest?): Todo {
        // checkNotNull 호출하고 나면 Not Null 로 변경
        checkNotNull(request) { "TodoRequest is null" }

        val todo = Todo(
            title = request.title,
            description = request.description,
            done = request.done
        )
        return todoRepository.save(todo)
    }

    @Transactional
    fun update(id: Long, request: TodoRequest?): Todo {
        checkNotNull(request) { "TodoRequest is null" }

        return findById(id).let {
            it.update(request)
        }
    }

    @Transactional
    fun delete(id: Long) = todoRepository.deleteById(id)
}
