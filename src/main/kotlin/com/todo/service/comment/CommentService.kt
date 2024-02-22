package com.todo.service.comment

import com.todo.domain.entity.Board
import com.todo.domain.entity.Comment
import com.todo.domain.repository.CommentRepository
import com.todo.exception.comment.CommentCreatedByNotMatchWithDeletedByException
import com.todo.exception.comment.CommentCreatedByNotMatchWithUpdatedByException
import com.todo.exception.comment.CommentNotFoundException
import com.todo.service.board.BoardService
import com.todo.service.comment.dto.CreateCommentRequestDto
import com.todo.service.comment.dto.UpdateCommentRequestDto
import com.todo.service.comment.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentRepository: CommentRepository,
    private val boardService: BoardService,
) {
    @Transactional
    fun createComment(
        id: Long,
        request: CreateCommentRequestDto,
    ): Long {
        val board = getBoardById(id)
        val savedComment = commentRepository.save(request.toEntity(board))
        return savedComment.id ?: throw IllegalStateException("Comment must not be null")
    }

    @Transactional
    fun updateComment(
        id: Long,
        request: UpdateCommentRequestDto,
    ): Long {
        val comment = getCommentById(id)
        checkCreatedByIsMatchWithUpdatedBy(comment, request)
        comment.updateComment(request)
        return id
    }

    @Transactional
    fun deleteComment(
        id: Long,
        deleteBy: String,
    ) {
        val comment = getCommentById(id)
        checkCreatedByIsMatchWithDeletedBy(comment, deleteBy)
        commentRepository.delete(comment)
    }

    private fun getBoardById(id: Long): Board {
        return boardService.getBoardById(id)
    }

    private fun getCommentById(id: Long): Comment {
        return commentRepository.findByIdOrNull(id)
            ?: throw CommentNotFoundException("해당하는 댓글이 존재하지 않습니다.")
    }

    private fun checkCreatedByIsMatchWithUpdatedBy(
        comment: Comment,
        request: UpdateCommentRequestDto,
    ) {
        if (comment.createdBy != request.updatedBy) {
            throw CommentCreatedByNotMatchWithUpdatedByException("댓글을 수정할 수 없습니다.")
        }
    }

    private fun checkCreatedByIsMatchWithDeletedBy(
        comment: Comment,
        deletedBy: String,
    ) {
        if (comment.createdBy != deletedBy) {
            throw CommentCreatedByNotMatchWithDeletedByException("댓글을 삭제할 수 없습니다.")
        }
    }
}
