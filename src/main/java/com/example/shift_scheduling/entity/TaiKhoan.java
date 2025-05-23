package com.example.shift_scheduling.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Account")
@Getter
@Setter
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
