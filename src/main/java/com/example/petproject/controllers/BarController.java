package com.example.petproject.controllers;

import com.example.petproject.services.ProjectService;
import com.example.petproject.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class BarController {

    @Autowired
    VKService vkService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }


    @ModelAttribute
    public void barInfo(Model model) throws ClientException, ApiException {
        model.addAttribute("projects", projectService.getAllProjects(vkService.getUserId()))
                .addAttribute("userName",vkService.getUserName());
    }

    @GetMapping("/add")
    public String showAddPage(Model model) throws ClientException, ApiException {
        model.addAttribute("accounts", vkService.getAccounts());
        return "choose_account";
    }

}
