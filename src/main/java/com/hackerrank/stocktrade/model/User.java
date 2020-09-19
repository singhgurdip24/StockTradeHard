package com.hackerrank.stocktrade.model;

import javax.persistence.*;
import java.util.Set;

@Table(name="users")
@Entity
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy="user")
    private Set<Trade> tradeSet;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
