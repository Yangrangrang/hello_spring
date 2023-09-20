package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // spring은 controller생성시 annotection을 꼭 붙여줘야함.
public class helloController {

    @GetMapping("hello")    // 웹어플리케이션에서 /hello라고 들어오면 이 메소드를 호출
    public String hello(Model model){   // MVC : Model, View, Controller
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http(통신 프로토콜)에서 응답 body부에 내용을 직접 넣어주겠다. / 데이터를 그대로 넘겨준다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 템플릿과의 차이는 태그가 없다. 그냥 문자가 들어가 있다.
    }

    // 문자가 아니라 데이터를 내놓으라고 하면 JSON 방식 (키:벨류)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 문자가 아니고 객체야. >  스프링 입장에서는 디폴트가 json방식으로 만들어서 반환 하겠다. (ResponsBody)
    }

    static class Hello {
        private String name;

        // 자바빈표준 방식 (게터세터), 프로퍼티 접근방식
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
