package com.todo.api

import org.springframework.web.bind.annotation.*

@RestController
class SampleController {

    @GetMapping("/sample")
    fun sample(): String {
        return "sample"
    }

    @PostMapping("/sample")
    fun samplePost(
        @RequestParam name: String,
    ): String {
        return "sample name : $name"
    }
}
