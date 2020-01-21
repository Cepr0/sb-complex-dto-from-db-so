package io.github.cepr0.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
