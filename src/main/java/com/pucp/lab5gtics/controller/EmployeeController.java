package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.Department;
import com.pucp.lab5gtics.entity.Employee;
import com.pucp.lab5gtics.entity.Job;
import com.pucp.lab5gtics.repository.DepartmentRepository;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import com.pucp.lab5gtics.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        if (search == null){
            if (order !=  null) {
                switch (order) {
                    case 1 -> model.addAttribute("listaEmpleados", employeeRepository.ordenDesc());
                    case 2 -> model.addAttribute("listaEmpleados", employeeRepository.ordenAsc());
                    default -> model.addAttribute("listaEmpleados", employeeRepository.findAll());
                }
            }else{
                model.addAttribute("listaEmpleados", employeeRepository.ordenDesc());
            }
        }else{
            model.addAttribute("listaEmpleados",employeeRepository.buscarNombre(search));
            model.addAttribute("busqueda",search);
        }
        return "employee/list";
    }



    public List<Department> getListaDepartamento() {
        List<Department> listaJefes = departmentRepository.findAll();
        Department e = new Department();
        e.setDepartmentId(0);
        e.setDepartmentName("--No tiene departamento--");
        listaJefes.add(0, e);
        return listaJefes;
    }

    public List<Job> getListaTrabajo() {
        List<Job> ListaTrabajo = jobRepository.findAll();
        Job e = new Job();
        e.setJobId(null);
        e.setJobTitle("--No tiene departamento--");
        ListaTrabajo.add(0, e);
        return ListaTrabajo;
    }

    //Editar Empleado
    @GetMapping("empleado/editar")
    public String informEmployee(@ModelAttribute("employee") Employee employee,
                                 Model model, @RequestParam("id") int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employee = optional.get();
            model.addAttribute("empleado", employee);
            model.addAttribute("listaDepartamento",getListaDepartamento());
            model.addAttribute("listaTrabajo",getListaTrabajo());
            return "employee/datos";
        } else {
            return "redirect:/empleado/lista";
        }
    }

    //Guardar Empleado
    @PostMapping("empleado/guardar")
    public String saveEmployee( @ModelAttribute("employee")  Employee employee,
                                RedirectAttributes attr) {

        if(employee.getEmployeeId()==0){
            attr.addFlashAttribute("msg","Empleado registrado exitosamente");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            Date date = new Date();
            String strDate = formatter.format(date);
            employee.setHireDate(strDate);
        } else {

            attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
        }

        employeeRepository.save(employee);
        return "redirect:/empleado/lista";
    }

    //Nuevo Empleado
    @GetMapping("empleado/nuevo")
    public String newEmployee(@ModelAttribute("employee") Employee employee,
                                Model model) {
        model.addAttribute("empleado",employee);
        model.addAttribute("listaDepartamento",getListaDepartamento());
        model.addAttribute("listaTrabajo",getListaTrabajo());
        return "employee/datos";
    }
}
