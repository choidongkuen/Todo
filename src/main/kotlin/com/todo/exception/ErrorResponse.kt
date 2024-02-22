package com.todo.exception

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val message: String,
    val errorName: String,
    val code: Int,
) {
    companion object {
        fun of(exception: Exception, httpStatus: HttpStatus) =
            ErrorResponse(exception.message.toString(), exception.javaClass.simpleName, httpStatus.value())

        fun of(exception: Exception, message: String, httpStatus: HttpStatus) =
            ErrorResponse(message, exception.javaClass.simpleName, httpStatus.value())
    }
}
