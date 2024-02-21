package com.todo.api

import com.todo.api.dto.comment.CreateCommentRequest
import com.todo.api.dto.comment.UpdateCommentRequest
import com.todo.service.comment.CommentService
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping("/boards/{id}/comments")
    fun createComment(
        @PathVariable id: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ) = ok(commentService.createComment(id, createCommentRequest))

    @PutMapping("/comments/{id}")
    fun updateComment(
        @PathVariable id: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ) = ok(commentService.updateComment(id, updateCommentRequest))

    @DeleteMapping("/comments/{id}")
    fun deleteComment(
        @PathVariable id: Long,
        @RequestParam deletedBy: String
    ) = ok(commentService.deleteComment(id, deletedBy))
}
