package com.hibernate.domain.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Jewelry_manytomany")
public class Jewelry {

    @Id
    @GeneratedValue
    @Column(name = "JEWELRY_ID")
    private Long jewelryId;

    @Column(name = "JEWELRY_NAME")
    private String jewelryName;

    @ManyToMany
    @JoinTable(name = "JEWELRY_STONE", joinColumns = {@JoinColumn(name = "JEWELRIES_ID")}, inverseJoinColumns = {@JoinColumn(name = "STONES_ID")})
    private Set<Stone> stones = new HashSet<Stone>(0);

    public Jewelry() {
    }

    public Jewelry(String jewelryName) {
        this.jewelryName = jewelryName;
    }

    public Jewelry(String jewelryName, Set<Stone> stones) {
        this.jewelryName = jewelryName;
        this.stones = stones;
    }

    public Long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(Long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getJewelryName() {
        return jewelryName;
    }

    public void setJewelryName(String jewelryName) {
        this.jewelryName = jewelryName;
    }

    public Set<Stone> getStones() {
        return stones;
    }

    public void setStones(Set<Stone> stones) {
        this.stones = stones;
    }

    public String toString() {
        return "<font color='blue'>" + getJewelryId() + " " + getJewelryName() + " </font><br><font color='green'> " + getStones() + "</font> <br><br>";
    }
}
