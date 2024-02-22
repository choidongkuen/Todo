package com.todo.service.board

import com.todo.domain.entity.Board
import com.todo.domain.entity.Comment
import com.todo.domain.repository.BoardRepository
import com.todo.domain.repository.CommentRepository
import com.todo.exception.BoardCreatedByNotMatchException
import com.todo.exception.BoardNotFoundException
import com.todo.service.board.dto.CreateBoardRequestDto
import com.todo.service.board.dto.GetBoardsRequestDto
import com.todo.service.board.dto.UpdateBoardRequestDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class BoardServiceTest(
    private val boardService: BoardService,
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository,
) : BehaviorSpec({
        beforeSpec {
            boardRepository.saveAll(
                listOf(
                    Board(title = "title11", content = "content1", createdBy = "Mike1"),
                    Board(title = "title12", content = "content2", createdBy = "Mike2"),
                    Board(title = "title13", content = "content3", createdBy = "Mike3"),
                    Board(title = "title14", content = "content4", createdBy = "Mike4"),
                    Board(title = "title15", content = "content5", createdBy = "Mike5"),
                    Board(title = "title16", content = "content6", createdBy = "Mike6"),
                    Board(title = "title17", content = "content7", createdBy = "Mike7"),
                    Board(title = "title18", content = "content8", createdBy = "Mike8"),
                ),
            )
        }
        given("게시글 생성시") {
            `when`("게시글 생성 요청이 정상적으로 들어오면") {
                val boardId =
                    boardService.creatBoard(
                        CreateBoardRequestDto(
                            title = "제목",
                            content = "내용",
                            createdBy = "John",
                        ),
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
                val updatedId =
                    boardService.updateBoard(
                        savedBoard.id!!,
                        UpdateBoardRequestDto(
                            title = "수정 제목",
                            content = "수정 내용",
                            updatedBy = "John",
                        ),
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
                                updatedBy = "John",
                            ),
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
                                updatedBy = "Mike",
                            ),
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
                val deletedBoardId =
                    boardService.deleteBoard(
                        savedBoard.id!!,
                        createdBy,
                    )
                Then("게시글이 정상적으로 삭제된다.") {
                    deletedBoardId shouldNotBe null
                    deletedBoardId shouldBe savedBoard.id
                }
            }
            `when`("삭제 요청자와 작성자가 다른 경우") {
                val board2 = Board(title = "제목", content = "내용", createdBy = "Mike")
                val savedBoard2 = boardRepository.save(board2)
                var createdBy2 = savedBoard2.createdBy

                then("삭제할 수 없는 게시물이다라는 에러가 발생한다.") {
                    shouldThrow<BoardCreatedByNotMatchException> {
                        boardService.deleteBoard(
                            savedBoard2.id!!,
                            "John",
                        )
                    }
                }
            }
            `when`("해당하는 게시글이 존재하지 않는다면") {
                then("해당 게시글이 존재하지 않습니다.") {
                    shouldThrow<BoardNotFoundException> {
                        boardService.deleteBoard(
                            999L,
                            "Mike",
                        )
                    }
                }
            }
        }
        given("게시글 상세 조회 시") {
            val board = Board(title = "제목", content = "내용", createdBy = "Mike")
            val savedBoard = boardRepository.save(board)
            `when`("정상적인 상세 조회 요청 시") {
                val foundBoard = boardService.getBoard(savedBoard.id!!)
                then("게시글에 대한 상세 조회 응답이 반환된다.") {
                    savedBoard.id shouldNotBe null
                    savedBoard.title shouldBe "제목"
                }
            }
            `when`("게시글이 존재하지 않는 경우") {
                then("해당 게시글이 존재하지 않습니다 예외 응답을 반환한다.") {
                    shouldThrow<BoardNotFoundException> { boardService.getBoard(999L) }
                }
            }
            `when`("댓글 추가 시") {
                val savedComment = commentRepository.save(Comment(content = "댓글", createdBy = "John", board = savedBoard))
                val foundBoard = boardService.getBoard(savedBoard.id!!)
                then("댓글과 함께 게시글에 대한 상세 조회 응답이 반환된다.") {
                }
            }
        }
        given("게시글 목록 조회 시") {
            `when`("조건 없이 정상적인 목록 조회 요청 시") {
                val result = boardService.getBoardsBySearch(PageRequest.of(0, 5), GetBoardsRequestDto())
                then("게시글에 대한 목록 조회 응답이 반환된다.") {
                    result.size shouldBe 5
                    result.content.size shouldBe 5
                    result.content[0].createdBy shouldContain "Mike"
                }
            }
            `when`("제목 조건으로 정상적인 목록 조회 요청 시") {
                val result = boardService.getBoardsBySearch(PageRequest.of(0, 5), GetBoardsRequestDto(title = "title"))
                then("제목에 해당하는 게시물을 반환한다.") {
                    result.size shouldBe 5
                    result.content.size shouldBe 5
                }
            }
            `when`("작성자 조건으로 정상적인 목록 조회 요청 시") {
                val result = boardService.getBoardsBySearch(PageRequest.of(0, 5), GetBoardsRequestDto(createdBy = "Mike1"))
                result.size shouldBe 5
                result.content.size shouldBe 1
            }
        }
    })
