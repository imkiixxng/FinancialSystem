package com.example.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "User")
public class User {

    @Id
    @Column(name = "UserID", length = 20)
    private String userID;

    @NotBlank
    @Column(name = "UserName", nullable = false)
    private String userName;

    @NotBlank
    @Email
    @Column(name = "Email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "Account", nullable = false)
    private String account;

    // 雙向關聯：一個使用者可以有多個喜好清單
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikeList> likeLists;
}
