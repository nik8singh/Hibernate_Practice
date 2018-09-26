package com.hibernate.domain.onetoone;


import javax.persistence.*;

@Entity
@Table(name = "STUDENT_OneToOne")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long studentId;

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    private String studentName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AID")
    private Address studentAddress;

    public Student(){}

    public  Student(String studentName, Address studentAddress){
        this.studentName = studentName;
        this.studentAddress = studentAddress;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "<font color='blue'>" + getStudentId() + " " + getStudentName() + " </font><br><font color='green'> " + getStudentAddress() + "</font> <br><br>";

    }
}
