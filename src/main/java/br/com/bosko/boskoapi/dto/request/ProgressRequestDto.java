package br.com.bosko.boskoapi.dto.request;

import br.com.bosko.boskoapi.entity.Status;

public record ProgressRequestDto(
  Integer progress,
  Status status,
  Long bookId
) {
  
}
