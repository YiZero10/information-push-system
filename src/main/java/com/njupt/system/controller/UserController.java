package com.njupt.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:51 AM
 * @signature Do it while you can!
 */
@RestController
public class UserController {

    @RequestMapping("/test")
    public String init(){return "Welcome!";}
}
