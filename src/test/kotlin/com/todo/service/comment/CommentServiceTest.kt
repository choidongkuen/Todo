package com.todo.service.comment

import com.todo.domain.entity.Board
import com.todo.domain.entity.Comment
import com.todo.domain.repository.BoardRepository
import com.todo.domain.repository.CommentRepository
import com.todo.exception.BoardNotFoundException
import com.todo.exception.comment.CommentCreatedByNotMatchWithDeletedByException
import com.todo.exception.comment.CommentCreatedByNotMatchWithUpdatedByException
import com.todo.service.comment.dto.CreateCommentRequestDto
import com.todo.service.comment.dto.UpdateCommentRequestDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CommentServiceTest(
    private val commentService: CommentService,
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository,
) : BehaviorSpec({
    beforeSpec {
        boardRepository.saveAll(listOf(Board(title = "제목", content = "내용", createdBy = "John")))
    }

    given("댓글 생성시") {
        val savedBoard = boardRepository.findAll().get(0)
        val comment = Comment(content = "댓글", createdBy = "John", board = savedBoard)
        val savedComment = commentRepository.save(comment)
        `when`("댓글 생성 요청이 정상적으로 들어오면") {
            val commentId = commentService.createComment(
                savedComment.id ?: 0L,
                CreateCommentRequestDto(content = "내용", createdBy = "John"),
            )
            then("댓글이 생성된다.") {
                val result = commentRepository.findByIdOrNull(commentId)
                savedComment.id shouldNotBe null
                savedComment.content shouldBe "댓글"
                savedComment.board shouldBe savedBoard
                savedComment.id shouldBe 1L
            }
        }
        `when`("게시글이 존재하지 않는다면") {
            then("댓글 생성에 실패하며, 해당 게시글이 존재하지 않습니다 라는 응답을 반환한다.") {
                shouldThrow<BoardNotFoundException> {
                    commentService.createComment(
                        999L,
                        CreateCommentRequestDto(content = "내용", createdBy = "John"),
                    )
                }
            }
        }
    }
    given("댓글 수정시") {
        val savedBoard = boardRepository.findAll().get(0)
        val comment = commentRepository.save(Comment(content = "댓글", createdBy = "John", board = savedBoard))
        `when`("댓글 수정 요청이 정상적으로 들어오면") {
            val result = commentService.updateComment(
                comment.id!!,
                UpdateCommentRequestDto(content = "수정 댓글", updatedBy = "John"),
            )
            then("댓글이 수정된다.") {
                result shouldBe comment.id
                val updatedComment = commentRepository.findByIdOrNull(result)
                updatedComment shouldNotBe null
                updatedComment?.content shouldBe "수정 댓글"
                comment.content shouldBe "댓글"
            }
        }
        `when`("댓글 수정자와 작성자가 다른 경우") {
            then("해당 댓글을 수정할 수 없습니다로 응답한다.") {
                shouldThrow<CommentCreatedByNotMatchWithUpdatedByException> {
                    commentService.updateComment(
                        comment.id!!,
                        UpdateCommentRequestDto(content = "수정 댓글", updatedBy = "Mike"),
                    )
                }
            }
        }
    }
    given("댓글 삭제시") {
        val savedBoard = boardRepository.findAll().get(0)
        val comment = commentRepository.save(Comment(content = "댓글", createdBy = "John", board = savedBoard))
        `when`("댓글 삭제 요청이 정상적으로 들어오면") {
            commentService.deleteComment(comment.id!!, comment.createdBy)
            then("댓글이 삭제된다.") {
            }
        }
        `when`("댓글 삭제자와 작성자가 다른 경우") {
            val comment02 = commentRepository.save(Comment(content = "댓글", createdBy = "John", board = savedBoard))
            then("해당 댓글을 삭제할 수 없습니다로 응답한다.") {
                shouldThrow<CommentCreatedByNotMatchWithDeletedByException> {
                    commentService.deleteComment(comment02.id!!, "Mike")
                }
            }
        }
    }
},)
