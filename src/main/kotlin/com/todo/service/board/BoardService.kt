package com.todo.service.board

import com.todo.api.model.board.GetBoardsRequest
import com.todo.domain.entity.Board
import com.todo.domain.repository.BoardRepository
import com.todo.exception.BoardCreatedByNotMatchException
import com.todo.exception.BoardNotFoundException
import com.todo.service.board.dto.CreateBoardRequestDto
import com.todo.service.board.dto.UpdateBoardRequestDto
import com.todo.service.board.dto.toEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BoardService(
    private val boardRepository: BoardRepository,
) {

    @Transactional
    fun creatBoard(request: CreateBoardRequestDto): Long {
        val savedBoard = boardRepository.save(request.toEntity())
        return savedBoard.id ?: throw IllegalStateException("Board must not be null")
    }

    @Transactional
    fun updateBoard(id: Long, request: UpdateBoardRequestDto): Long {
        val board = getBoardById(id)
        checkCreatedByIsMatch(board, request)
        board.updateBoard(request)
        return id
    }

    @Transactional
    fun deleteBoard(id: Long, createdBy: String): Long {
        val board = getBoardById(id)
        checkCreatedByIsMatch(board, createdBy)
        boardRepository.delete(board)
        return id
    }

    fun getBoard(id: Long) {
        return
    }

    fun getBoardsBySearch(pageable: Pageable, getBoardsRequest: GetBoardsRequest) {
        return
    }

    private fun getBoardById(id: Long): Board {
        return boardRepository.findByIdOrNull(id)
            ?: throw BoardNotFoundException("해당 게시글이 존재하지 않습니다.")
    }

    private fun checkCreatedByIsMatch(
        board: Board,
        request: UpdateBoardRequestDto,
    ) {
        if (board.createdBy != request.updatedBy) {
            throw BoardCreatedByNotMatchException("수정할 수 없는 게시글입니다.")
        }
    }

    private fun checkCreatedByIsMatch(
        board: Board,
        createdBy: String,
    ) {
        if (board.createdBy != createdBy) {
            throw BoardCreatedByNotMatchException("삭제할 수 없는 게시글입니다.")
        }
    }
}
