package com.todo.service.like

import com.todo.domain.entity.Board
import com.todo.domain.repository.BoardRepository
import com.todo.domain.repository.LikeRepository
import com.todo.exception.BoardNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class LikeServiceTest(
    private val likeService: LikeService,
    private val likeRepository: LikeRepository,
    private val boardRepository: BoardRepository,
) : BehaviorSpec(
        {
            given("좋아요 생성시") {
                val savedBoard = boardRepository.save(Board(title = "제목", content = "내용", createdBy = "John", tags = listOf("tag1", "tag2")))
                `when`("게시글 생성 요청이 정상적으로 들어오면") {
                    val savedLikeId = likeService.createLike(1L, "Mike")
                    then("게시글이 생성된다.") {
                        val savedLike = likeRepository.findByIdOrNull(savedLikeId)
                        savedLike shouldNotBe null
                        savedLike?.board?.title shouldBe savedBoard.title
                        savedLike?.createdBy shouldBe "Mike"
                    }
                }
                `when`("게시글이 존재하지 않는다면") {
                    then("게시글이 존재하지 않습니다라는 에러를 반환한다.") {
                        shouldThrow<BoardNotFoundException> { likeService.createLike(99, "John") }
                    }
                }
            }
        },
    )
