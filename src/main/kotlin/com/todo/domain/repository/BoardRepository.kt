package com.todo.domain.repository

import com.todo.domain.entity.Board
import com.todo.domain.entity.QBoard.board
import com.todo.service.board.dto.GetBoardsRequestDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

interface BoardRepository : JpaRepository<Board, Long>, CustomBoardRepository

interface CustomBoardRepository {
    fun findPageBy(
        pageRequest: org.springframework.data.domain.Pageable,
        getBoardsRequest: GetBoardsRequestDto,
    ): Page<Board>
}

class CustomBoardRepositoryImpl : CustomBoardRepository, QuerydslRepositorySupport(Board::class.java) {
    override fun findPageBy(
        pageRequest: org.springframework.data.domain.Pageable,
        getBoardsRequest: GetBoardsRequestDto,
    ): Page<Board> {
        val result =
            from(board)
                .where(
                    getBoardsRequest.title?.let { board.title.contains(it) },
                    getBoardsRequest.createdBy?.let { board.createdBy.eq(it) },
                    getBoardsRequest.firstTag?.let {
                        // tags name 중에 어떤 거라도 firstTag 와 같다면
                        board.tags.any().name.contains(it)
                    }
                )
                .orderBy(board.createdAt.desc()) // 최신순
                .offset(pageRequest.offset) // 시작
                .limit(pageRequest.pageSize.toLong()) // 개수
                .fetchResults()
        return PageImpl(result.results, pageRequest, result.total)
    }
}
