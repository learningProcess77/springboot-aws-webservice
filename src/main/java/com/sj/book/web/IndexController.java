package com.sj.book.web;

import com.sj.book.web.config.auth.LoginUser;
import com.sj.book.web.config.auth.dto.SessionUser;
import com.sj.book.web.dto.PostsResponseDto;
import com.sj.book.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAll());

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); ==> 어노테이션 방식으로 변경

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }




}
