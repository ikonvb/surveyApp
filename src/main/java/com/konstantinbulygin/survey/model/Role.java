package com.konstantinbulygin.survey.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<Client> clients;

}
