package com.pucp.lab5gtics.repository;

import com.pucp.lab5gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//Completar

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "SELECT * FROM employees where first_name like %?1% ",
            nativeQuery = true)
    List<Employee> buscarNombre(String nombre);

    @Query(value="select * from employees where email= ?1 ;",nativeQuery = true)
    Employee employeeCorreo(String codigo);


    @Query(value = "SELECT * FROM employees order by first_name desc",
            nativeQuery = true)
    List<Employee> ordenDesc();

    @Query(value = "SELECT * FROM employees order by first_name asc",
            nativeQuery = true)
    List<Employee> ordenAsc();
}

