package io.github.cepr0.demo;

import lombok.Value;

@Value
class UserPlainDto {
    long userId;
    long contactCount;
    ActionType actionType;
    Long actionCount;
}