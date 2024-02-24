package com.todo.domain.repository

import com.todo.domain.entity.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like,Long> {
}
