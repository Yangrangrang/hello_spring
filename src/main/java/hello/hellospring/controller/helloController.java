package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // spring은 controller생성시 annotection을 꼭 붙여줘야함.
public class helloController {

    @GetMapping("hello")    // 웹어플리케이션에서 /hello라고 들어오면 이 메소드를 호출
    public String hello(Model model){   // MVC : Model, View, Controller
        model.addAttribute("data", "hello!");
        return "hello";
    }
}
