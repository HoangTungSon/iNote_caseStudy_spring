package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/user")
    public String user(Principal principal) {
        // Get authenticated user name from Principal
        System.out.println(principal.getName());
        return "/loginForm/user";
    }

    @GetMapping("/")
    public String index(){
        return "/loginForm/index";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "/loginForm/loginPage";
    }
}
