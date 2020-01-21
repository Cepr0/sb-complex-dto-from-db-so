package io.github.cepr0.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Action {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private ActionType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
