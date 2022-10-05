package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.repository.DepartmentRepository;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import com.pucp.lab5gtics.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JobRepository jobRepository;

    @GetMapping({"empleado/lista", "empleado"})
    public String listEmployee(Model model, @RequestParam(name = "search",required = false) String search, @RequestParam(name = "order", required = false) Integer order, RedirectAttributes attributes){


        return "XXXXXX";
    }


    //Buscar Empleado
    public String searchEmployee(Model model, @RequestParam(name = "search",required = false) String search, @RequestParam(name = "order", required = false) Integer order, RedirectAttributes attributes){

        return "XXXXXX";
    }


    public List<Employee> getListaDepartamento() {
        List<Employee> listaJefes = employeeRepository.findAll();
        Employee e = new Employee();
        e.setDepartmentId(0);
        listaJefes.add(0, e);
        return listaJefes;
    }

    //Editar Empleado
    @GetMapping("/edit")
    public String informEmployee(Model model, @RequestParam("id") int id) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("employee", optional.get());
            model.addAttribute("listaJefes", getListaDepartamento());
            return "employee/editFrm";
        } else {
            return "redirect:/employee";
        }
    }

    //Guardar Empleado
    @PostMapping("empleado/guardar")
    public String saveEmployee(  ) {
        //        COMPLETAR
        return "XXXXXX";
    }

    //Nuevo Empleado
    @GetMapping("employee/nuevo")
    public String newEmployee(@ModelAttribute("employee") Employee employee,
                                Model model) {

        return "employee/datos";
    }
}
