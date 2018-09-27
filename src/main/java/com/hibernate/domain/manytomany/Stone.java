package com.hibernate.domain.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Stone_manytomany")
public class Stone {

    @Id
    @GeneratedValue
    @Column(name = "STONE_ID")
    private Long stoneId;

    @Column(name = "STONE_NAME", nullable = false)
    private String stoneName;

    // USE JOINTABLE annotation
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JEWELRY_STONE", joinColumns = {@JoinColumn(name = "STONES_ID")}, inverseJoinColumns = {@JoinColumn(name = "JEWELRIES_ID")})
    private Set<Jewelry> jewelries = new HashSet<Jewelry>(0);

    public Stone() {
    }

    public Stone(String stoneName) {
        this.stoneName = stoneName;
    }

    public Long getStoneId() {
        return stoneId;
    }

    public void setStoneId(Long stoneId) {
        this.stoneId = stoneId;
    }

    public String getStoneName() {
        return stoneName;
    }

    public void setStoneName(String stoneName) {
        this.stoneName = stoneName;
    }

    public Set<Jewelry> getJewelries() {
        return jewelries;
    }

    public void setJewelries(Set<Jewelry> jewelries) {
        this.jewelries = jewelries;
    }

    public String toString() {

        return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + getStoneId() + " " + getStoneName();

    }
}
