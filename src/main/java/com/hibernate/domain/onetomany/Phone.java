package com.hibernate.domain.onetomany;

import javax.persistence.*;

@Entity
@Table(name = "PHONE_onetomany")
public class Phone {

    @Id
    @GeneratedValue
    @Column(name = "PHONE_ID")
    private long phoneId;

    @Column(name = "PHONE_TYPE", nullable = false, length = 10)
    private String phoneType;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 15)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;    // Bi-Directional (Employee information can be retrieved using phone object


    public Phone() {
    }

    public Phone(String phoneType, String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(long phoneID) {
        this.phoneId = phoneID;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getPhoneId() + " " + getPhoneType() + " " + getPhoneNumber();
    }
}
