package com.todo.domain.entity

import com.todo.api.dto.todo.TodoRequest
import com.todo.core.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity(name = "todos")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    @Column(name = "title", nullable = false)
    var title: String,
    @Column(name = "description", nullable = false)
    var description: String,
    @Lob
    @Column(name = "done", nullable = false)
    var done: Boolean,
) : BaseTimeEntity() {
    // Todo 엔티티 수정
    // JPA + Kotlin 시 어쩔 수 없이 var (가변 인자)
    fun update(request: TodoRequest): Todo {
        title = request.title
        description = request.description
        done = request.done
        return this
    }

    fun approve() {
        println("$id Todo is approved")
    }
}
