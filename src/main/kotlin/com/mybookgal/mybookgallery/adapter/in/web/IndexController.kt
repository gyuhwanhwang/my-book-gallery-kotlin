package com.mybookgal.mybookgallery.adapter.`in`.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping
    fun home(): String {
        return "home"
    }
}