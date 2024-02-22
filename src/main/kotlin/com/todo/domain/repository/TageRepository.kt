package com.todo.domain.repository

import com.todo.domain.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TageRepository : JpaRepository<Tag, Long>
