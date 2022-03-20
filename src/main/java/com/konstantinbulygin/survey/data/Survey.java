package com.konstantinbulygin.survey.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Start date is mandatory")
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Survey survey = (Survey) o;
        return id != null && Objects.equals(id, survey.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
