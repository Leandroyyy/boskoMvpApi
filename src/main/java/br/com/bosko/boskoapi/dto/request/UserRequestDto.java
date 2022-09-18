package br.com.bosko.boskoapi.dto.request;

import br.com.bosko.boskoapi.entity.Gender;

public record UserRequestDto(
    String name,
    String lastName,
    Long phone,
    String email,
    String password,
    Gender gender,
    Long birthDate, 
    Long experiencePoints, 
    Integer level) {
}
