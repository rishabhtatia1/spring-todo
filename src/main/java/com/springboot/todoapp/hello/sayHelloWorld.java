package com.springboot.todoapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sayHelloWorld {

    @RequestMapping("/say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello. How are you doing!!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My first HTML Page </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My first html with body");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @RequestMapping("/say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }

}
