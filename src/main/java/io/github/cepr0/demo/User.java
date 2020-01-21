package io.github.cepr0.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;
}
