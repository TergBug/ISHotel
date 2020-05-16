package org.mycode.controller;

import org.mycode.dto.TempInfo;
import org.mycode.dto.UserSecurityDto;
import org.mycode.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("chsignup")
    public String chooseSignUp(Model model) {
        model.addAttribute("type", "");
        return "signup/chooseSignUp";
    }

    @PostMapping("chsignup")
    public String makeChoice(Model model, @ModelAttribute("type") String type) {
        if (type.equals("customer")) {
            model.addAttribute("passport", new TempInfo());
            return "signup/customerPassport";
        } else if (type.equals("employee")) {
            model.addAttribute("ein", new TempInfo());
            return "signup/employeeEIN";
        }
        return "redirect:/";
    }

    @PostMapping("signup")
    public String signUp(Model model, @ModelAttribute("ein") TempInfo ein) {
        if (signUpService.verifyEmployeeByEIN(ein.getInfo())) {
            model.addAttribute("user", new UserSecurityDto("EMP"));
            model.addAttribute("ein", ein);
            return "signup/signup";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("csignup")
    public String signUpForCustomer(Model model, @ModelAttribute("passport") TempInfo passport) {
        if (signUpService.verifyCustomerByPassport(passport.getInfo())) {
            model.addAttribute("user", new UserSecurityDto("CUSTOMER"));
            model.addAttribute("passport", passport);
            return "signup/signup";
        } else {
            return "redirect:/order/room";
        }
    }

    @PostMapping("signup_proc")
    public String signUpSubmission(@ModelAttribute("user") UserSecurityDto userSecurityDto) {
        signUpService.signUp(userSecurityDto);
        return "redirect:/";
    }
}
