package com.todo.service

import com.ninjasquad.springmockk.MockkBean
import com.todo.domain.Todo
import com.todo.domain.TodoRepository
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class TodoServiceTest {

    @MockkBean
    lateinit var repository: TodoRepository

    @Autowired
    lateinit var service: TodoService

    val stub: Todo by lazy {
        Todo(
            id = 1,
            title = "테스트",
            description = "테스트 상세",
            done = false,
        )
    }

    @BeforeEach
    fun setUp() {
        service = TodoService(repository)
    }

    @Test
    @DisplayName("findById 호출 시 한개의 TODO 을 반환한다")
    fun `한개의 TODO를 반환한다`() {
        // Given
        every { repository.findByIdOrNull(1)} returns stub

        // When
        val actual = service.findById(1L)

        // Then
        assertThat(actual).isNotNull
    }
}
