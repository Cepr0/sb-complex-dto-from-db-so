package io.github.cepr0.demo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
class UserDto {
    long userId;
    long contactCount;
    Map<ActionType, Long> actions = new HashMap<>();
}