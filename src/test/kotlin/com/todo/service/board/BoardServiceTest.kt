package com.todo.service.board

import com.todo.domain.entity.Board
import com.todo.domain.repository.BoardRepository
import com.todo.exception.BoardCreatedByNotMatchException
import com.todo.exception.BoardNotFoundException
import com.todo.service.board.dto.CreateBoardRequestDto
import com.todo.service.board.dto.UpdateBoardRequestDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class BoardServiceTest(
    private val boardService: BoardService,
    private val boardRepository: BoardRepository,
) : BehaviorSpec({
    given("게시글 생성시") {
        `when`("게시글 생성 요청이 정상적으로 들어오면") {
            val boardId = boardService.creatBoard(
                CreateBoardRequestDto(
                    title = "제목",
                    content = "내용",
                    createdBy = "John"
                )
            )
            Then("게시글이 정상적으로 생성된다.") {
                boardId shouldBeGreaterThan 0L
                val board = boardRepository.findByIdOrNull(boardId)
                board shouldNotBe null
                board?.id shouldNotBe null
                board?.title shouldNotBe null
                board?.createdBy shouldBe "John"
            }
        }
    }

    given("게시글 수정시") {
        val board = Board(title = "제목", content = "내용", createdBy = "John")
        val savedBoard = boardRepository.save(board)

        `when`("게시글 수정 요청이 정상적으로 들어오면") {
            val updatedId = boardService.updateBoard(
                savedBoard.id!!,
                UpdateBoardRequestDto(
                    title = "수정 제목",
                    content = "수정 내용",
                    updatedBy = "John"
                )
            )
            Then("게시글이 정상적으로 수정된다.") {
                savedBoard.id shouldNotBe null
                val updatedBoard = boardRepository.findByIdOrNull(updatedId)
                updatedBoard shouldNotBe null
                updatedBoard?.id shouldBe savedBoard.id
                updatedBoard?.title shouldBe "수정 제목"
            }
        }
        `when`("게시글이 없을 때") {
            then("게시글을 찾을 수 없다는 에러가 발생한다.") {
                shouldThrow<BoardNotFoundException> {
                    boardService.updateBoard(
                        999L,
                        UpdateBoardRequestDto(
                            title = "수정 제목",
                            content = "수정 내용",
                            updatedBy = "John"
                        )
                    )
                }
            }
        }
        `when`("게시글 작성자와 수정자가 다른 경우") {
            then("수정할 수 없는 게시물입니다라는 에러가 발생한다.") {
                shouldThrow<BoardCreatedByNotMatchException> {
                    boardService.updateBoard(
                        savedBoard.id!!,
                        UpdateBoardRequestDto(
                            title = "수정 제목",
                            content = "수정 내용",
                            updatedBy = "Mike"
                        )
                    )
                }
            }
        }
    }
    given("게시글 삭제시") {
        val board = Board(title = "제목", content = "내용", createdBy = "Mike")
        val savedBoard = boardRepository.save(board)
        val createdBy = savedBoard.createdBy

        `when`("정상 삭제 요청 시") {
            val deletedBoardId = boardService.deleteBoard(
                savedBoard.id!!,
                createdBy
            )
            Then("게시글이 정상적으로 삭제된다.") {
                deletedBoardId shouldNotBe null
                deletedBoardId shouldBe savedBoard.id
            }
        }
        `when`("삭제 요청자와 작성자가 다른 경우") {
            val board = Board(title = "제목", content = "내용", createdBy = "Mike")
            val savedBoard = boardRepository.save(board)
            var createdBy = savedBoard.createdBy

            then("삭제할 수 없는 게시물이다라는 에러가 발생한다.") {
                shouldThrow<BoardCreatedByNotMatchException> {
                    boardService.deleteBoard(
                        savedBoard.id!!,
                        "John"
                    )
                }
            }
        }
        `when`("해당하는 게시글이 존재하지 않는다면") {
            then("해당 게시글이 존재하지 않습니다.") {
                shouldThrow<BoardNotFoundException> {
                    boardService.deleteBoard(
                        999L,
                        "Mike"
                    )
                }
            }
        }
    }
})
