package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")        //template engine 사용
    public String helloMvc(@RequestParam(value = "name",required = true) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   //HTML body에 함수 내용을 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody               //이렇게 클래스로 넘기게 되면 json 형식으로 나옴 (요새는 html이나 xml보다 json으로 많이 사용)
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;        //json에서 key가 될 것

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
