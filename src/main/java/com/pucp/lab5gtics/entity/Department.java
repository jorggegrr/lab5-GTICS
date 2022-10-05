package com.pucp.lab5gtics.entity;


import javax.persistence.*;

//Completar
@Entity
@Table(name = "departments")
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "department_id")
    private int departmentId;
    @Basic
    @Column(name = "department_name")
    private String departmentName;
    @Basic
    @Column(name = "manager_id")
    private Integer managerId;
    @Basic
    @Column(name = "location_id")
    private Integer locationId;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
