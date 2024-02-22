package com.todo.api

import com.todo.api.dto.board.CreateBoardRequest
import com.todo.api.dto.board.GetBoardsRequest
import com.todo.api.dto.board.UpdateBoardRequest
import com.todo.api.dto.board.toDto
import com.todo.service.board.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boards")
class BoardController(
    private val boardService: BoardService,
) {

    @PostMapping
    fun createBoard(
        @RequestBody createBoardRequest: CreateBoardRequest,
    ) =
        ok(boardService.creatBoard(createBoardRequest.toCreateBoardRequestDto()))

    @PutMapping("/{id}")
    fun updateBoard(
        @PathVariable id: Long,
        @RequestBody updateBoardRequest: UpdateBoardRequest,
    ) =
        ok(boardService.updateBoard(id, updateBoardRequest.toUpdateBoardRequestDto()))

    @DeleteMapping("/{id}")
    fun deleteBoard(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ) =
        ok(boardService.deleteBoard(id, createdBy))

    @GetMapping("/{id}")
    fun getBoard(
        @PathVariable id: Long,
    ) =
        ok(boardService.getBoard(id))

    @GetMapping
    fun getBoardsBySearch(
        pageable: Pageable,
        getBoardsRequest: GetBoardsRequest,
    ) =
        ok(boardService.getBoardsBySearch(pageable, getBoardsRequest.toDto()))
}
