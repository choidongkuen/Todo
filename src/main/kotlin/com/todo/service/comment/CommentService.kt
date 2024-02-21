package com.todo.service.comment

import com.todo.api.dto.comment.CreateCommentRequest
import com.todo.api.dto.comment.UpdateCommentRequest
import com.todo.domain.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun createComment(id: Long, request: CreateCommentRequest) {
    }

    fun updateComment(id: Long, request: UpdateCommentRequest) {
    }

    fun deleteComment(id: Long, deleteBy: String) {
    }
}
