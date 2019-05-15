package com.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
  @PostMapping("/login")
  public String hello() {
    return "22222212111你好";
  }
  @GetMapping("/logout")
  public String baibai() {
    return "大爷再见,欢迎大爷下次再来111";
  }

  @PutMapping("/regist")
  public String regist() {
    return "大爷来包月了1111111111";
  }
}
