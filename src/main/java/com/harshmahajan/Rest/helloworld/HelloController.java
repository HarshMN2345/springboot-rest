package com.harshmahajan.Rest.helloworld;

import org.springframework.web.bind.annotation.*;

//REST-API
@RestController
public class HelloController {
    @RequestMapping(method = RequestMethod.GET,path = "/hello")
    public String sayHello() {
        return "Hello World";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/hellobean")
    public HelloWorldBean sayHelloBean() {
        return new HelloWorldBean("Hello World");
    }
    @RequestMapping(method = RequestMethod.GET,path = "/hellobean/path-variable/{name}")
    public HelloWorldBean sayHelloBeanpathvariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello %s!", name));
    }
    @GetMapping(path="hello-world-in")
    public String sayHelloWorldInternational() {
        return "Hello World";
    }

}
