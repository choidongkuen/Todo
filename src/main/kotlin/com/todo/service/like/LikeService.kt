package com.todo.service.like

import com.todo.domain.entity.Board
import com.todo.domain.entity.Like
import com.todo.domain.repository.LikeRepository
import com.todo.service.board.BoardService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LikeService(
    private val likeRepository: LikeRepository,
    private val boardService: BoardService,
) {
    fun createLike(
        id: Long,
        createdBy: String,
    ): Long {
        val board = boardService.getBoardById(id)
        val like = likeRepository.save(Like(createdBy = createdBy, board = board))
        return like.id ?: throw IllegalStateException("Like must not be null")
    }

    private fun getBoardById(id: Long): Board {
        return boardService.getBoardById(id)
    }
}
