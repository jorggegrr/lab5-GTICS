package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LogController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"/login"})
    public String login( ) {
        return "login/login";
    }

    @GetMapping(value = "/redirecRol")
    public String redirecRol(Authentication authentication, HttpSession session, RedirectAttributes redirectAttributes){

        String rol="";
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority: authorities){
            System.out.println(grantedAuthority.getAuthority());
            rol= grantedAuthority.getAuthority();
        }

        String username= authentication.getName();
        Employee employee=employeeRepository.employeeCorreo(username);
        System.out.println(employee.getEmployeeId()+" algo");
        session.setAttribute("usuario",employee);

        switch (rol){
            case "0" -> {
                return "redirect:/usuario/listar";
            }
            case "1" -> {
                return "redirect:/employee/list";
            }
            default -> {
                String texto = "Credenciales invalidas";
                redirectAttributes.addFlashAttribute("msgLogin",texto);
                return "redirect:/login";
            }

        }
    }
}
