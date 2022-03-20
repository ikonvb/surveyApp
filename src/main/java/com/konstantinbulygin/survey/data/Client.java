package com.konstantinbulygin.survey.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    String name;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    String password;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    String role;
}
