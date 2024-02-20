package com.todo.service.board

import com.todo.api.model.board.CreateBoardRequest
import com.todo.api.model.board.GetBoardsRequest
import com.todo.api.model.board.UpdateBoardRequest
import com.todo.domain.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {

    fun creatBoard(request: CreateBoardRequest): Long {
        return 1L
    }

    fun updateBoard(id: Long, request: UpdateBoardRequest): Long {
        return 1L
    }

    fun deleteBoard(id: Long, createdBy: String) {
        return
    }

    fun getBoard(id: Long) {
        return
    }

    fun getBoardsBySearch(pageable: Pageable, getBoardsRequest: GetBoardsRequest) {
        return
    }
}
