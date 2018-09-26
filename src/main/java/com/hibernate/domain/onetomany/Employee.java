package com.hibernate.domain.onetomany;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee_onetomany")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @Column(name = "EMPLOYEE_NAME", nullable = false, length = 100)
    private String employeeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    // mappedBy used for Bi-Directional. use JoinColumn if single direction
    private Set<Phone> employeePhoneNumbers = new HashSet<Phone>(0);

    public Employee() {
    }

    public Employee(String employeeName, Set<Phone> employeePhoneNumbers) {
        this.employeeName = employeeName;
        this.employeePhoneNumbers = employeePhoneNumbers;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Set<Phone> getEmployeePhoneNumbers() {
        return employeePhoneNumbers;
    }

    public void setEmployeePhoneNumbers(Set<Phone> employeePhoneNumbers) {
        this.employeePhoneNumbers = employeePhoneNumbers;
    }

    @Override
    public String toString() {
        return "<font color='blue'>" + getEmployeeId() + " " + getEmployeeName() + " </font><br><font color='green'> " + getEmployeePhoneNumbers() + "</font> <br><br>";

    }
}
