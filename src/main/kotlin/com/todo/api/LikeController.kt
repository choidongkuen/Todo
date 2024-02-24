package com.todo.api

import com.todo.service.like.LikeService
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LikeController(
    private val likeService: LikeService,
) {
    @PostMapping("/boards/{id}/likes")
    fun createLike(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ) = ok(likeService.createLike(id, createdBy))
}
