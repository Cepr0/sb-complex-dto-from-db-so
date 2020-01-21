package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepo extends JpaRepository<Action, Long> {
}
