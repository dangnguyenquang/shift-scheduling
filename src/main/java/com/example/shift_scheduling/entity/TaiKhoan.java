package com.example.shift_scheduling.entity;

import com.example.shift_scheduling.util.Role;
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

    @Enumerated(EnumType.STRING)
    private Role role;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
