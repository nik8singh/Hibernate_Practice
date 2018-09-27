package com.hibernate.domain.manytoone;

import javax.persistence.*;

@Entity
@Table(name = "Food_manytoone")
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "FOOD_ID")
    private Long foodId;

    @Column(name = "FOOD_NAME", nullable = false, length = 100)
    private String foodName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category foodCategory;

    public Food() {
    }

    public Food(String foodName, Category foodCategory) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Category getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(Category foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public String toString() {
        return "<font color='blue'>" + getFoodId() + " " + getFoodName() + " </font><br><font color='green'> " + getFoodCategory() + "</font> <br><br>";
    }
}
