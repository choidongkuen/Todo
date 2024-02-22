package com.todo.exception

import com.todo.api.BoardController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [BoardController::class])
class BoardExceptionHandler {
    @ExceptionHandler(BoardNotFoundException::class)
    fun handleBoardNotFoundException(exception: BoardNotFoundException): ResponseEntity<ErrorResponse> = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(exception, HttpStatus.BAD_REQUEST))

    @ExceptionHandler(BoardCreatedByNotMatchException::class)
    fun handleBoardNotFoundException(exception: BoardCreatedByNotMatchException): ResponseEntity<ErrorResponse> = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(exception, HttpStatus.BAD_REQUEST))

    @ExceptionHandler(Exception::class)
    fun handlerInternalServerException(exception: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of(exception, "Internal Server Error", HttpStatus.BAD_REQUEST))
}
