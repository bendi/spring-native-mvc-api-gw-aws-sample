package pl.bedkowski.spring.mvc.lambda.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.bedkowski.spring.mvc.lambda.model.Response;

@RestController
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response hello() {
        return new Response(true, "Hello World");
    }
}