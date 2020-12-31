package com.jdy.mybatis2020.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Table(name="tabple_emp")
public class Employee {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator = "select SEQ_ID.nextval from dual"
    )
    private Integer empId;
    private String empName;
    @Column(name="emp_salary_apple")
    private Double empSalary;
    private Integer empAge;

}
