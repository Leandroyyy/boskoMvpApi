package br.com.bosko.boskoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bosko.boskoapi.dto.request.ProgressRequestDto;
import br.com.bosko.boskoapi.entity.Progress;

public interface ProgressService {
  
  Progress addProgressToUser(ProgressRequestDto progress);

  Page<Progress> findAll(Pageable pageable);

  Progress findById(Long id);

  Progress editUserProgress(Long id, ProgressRequestDto progress);

  void removeUserProgress(Long  id);
}
