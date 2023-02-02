package com.fragile.activity_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column( nullable = false)
    private String name;

    @Column(nullable = false)
    private String  username;

    @Column(nullable = false)
    private String password;

    @NotBlank(message ="email can not be empty")
    @Column(name = "email",  nullable =false)
    private   String email;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task>  tasks = new ArrayList<>();


}
