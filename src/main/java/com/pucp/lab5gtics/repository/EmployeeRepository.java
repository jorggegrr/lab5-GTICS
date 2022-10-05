package com.pucp.lab5gtics.repository;

import com.pucp.lab5gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Completar
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "SELECT * FROM employees where first_name = ?1",
            nativeQuery = true)
    List<Employee> buscarNombre(String nombre);

}
