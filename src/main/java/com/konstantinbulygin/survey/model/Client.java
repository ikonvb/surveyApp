package com.konstantinbulygin.survey.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    @Column(name = "client_name")
    String clientName;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    String password;

    @NotBlank(message = "role id is mandatory, 1 for client 2 for admin")
    @Column(name = "role_id")
    Integer roleId;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "client_roles",
//            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
//    private List<Role> roles;

}
