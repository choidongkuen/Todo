package com.todo.exception.comment

abstract class CommentException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : CommentException(message)

class CommentCreatedByNotMatchWithUpdatedByException(message: String) : CommentException(message)

class CommentCreatedByNotMatchWithDeletedByException(message: String) : CommentException(message)
