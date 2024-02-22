package com.todo.domain.entity

import com.todo.core.entity.BaseTimeEntity
import com.todo.service.comment.dto.UpdateCommentRequestDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

// kotlin 에서 JPA 사용시 다양한 문제 발생
// 변하지 않는 불변 프로퍼티인 경우 val 로 주생성자에 선언
// 변할 여지가 있는 프로퍼티인 경우 var 로 클래스 내부에 선언 + protected set (open 플러그인)
@Entity(name = "comments")
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    content: String,
    val createdBy: String,
    board: Board,
) : BaseTimeEntity() {
    @Column(name = "content", nullable = false)
    var content: String = content
        protected set

    @Column(name = "updated_by")
    var updatedBy: String? = null
        protected set

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var board: Board = board
        protected set

    fun updateComment(request: UpdateCommentRequestDto) {
        content = request.content
        updatedBy = request.updatedBy
    }
}
