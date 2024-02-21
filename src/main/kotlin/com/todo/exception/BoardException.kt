package com.todo.exception

open class BoardException(message: String) : RuntimeException(message)

class BoardNotFoundException(message: String) : BoardException(message)
class BoardCreatedByNotMatchException(message: String) : BoardException(message)
