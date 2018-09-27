package com.hibernate.domain.manytoone;


import javax.persistence.*;

@Entity
@Table(name = "Category_manytoone")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 250)
    private String categoryName;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {

        return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getCategoryId() + " " + getCategoryName();

    }
}
