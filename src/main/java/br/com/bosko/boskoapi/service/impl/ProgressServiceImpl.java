package br.com.bosko.boskoapi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.bosko.boskoapi.dto.request.ProgressRequestDto;
import br.com.bosko.boskoapi.entity.Progress;
import br.com.bosko.boskoapi.exception.BookNotFoundException;
import br.com.bosko.boskoapi.exception.ProgressNotFoundException;
import br.com.bosko.boskoapi.exception.UserNotFoundException;
import br.com.bosko.boskoapi.repository.BookRepository;
import br.com.bosko.boskoapi.repository.ProgressRepository;
import br.com.bosko.boskoapi.repository.UserRepository;
import br.com.bosko.boskoapi.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService{

  @Autowired
  private ProgressRepository progressRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Progress addProgressToUser(ProgressRequestDto progressDto) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();

    var user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException());
    var book = bookRepository.findById(progressDto.bookId()).orElseThrow(() -> new BookNotFoundException());
    var progress = new Progress(progressDto.progress(), progressDto.status(), book);

    user.getProgresses().add(progress);
    progressRepository.save(progress);
    userRepository.save(user);

    return progress;
  }

  @Override
  public Page<Progress> findAll(Pageable pageable) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    return progressRepository.findAllUserProgresses(pageable, username);
  }

  @Override
  public Progress findById(Long id) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException());
    Progress progress = progressRepository.findById(id).orElseThrow(() -> new ProgressNotFoundException());

    return progress;
  }

  @Override
  public Progress editUserProgress(Long id, ProgressRequestDto request) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    var user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException());
    var progress = progressRepository.findById(id).orElseThrow(() -> new ProgressNotFoundException());

    BeanUtils.copyProperties(request, progress);
    progress.setId(id);

    user.getProgresses().stream().forEach(newProgress -> {
      if (progress.getId() == newProgress.getId()) {
        BeanUtils.copyProperties(progress, newProgress);
        newProgress.setId(progress.getId());

        progressRepository.save(newProgress);
      }
    });
    return progress;
  }

  @Override
  public void removeUserProgress(Long id) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException());
    progressRepository.findById(id).orElseThrow(() -> new ProgressNotFoundException());
    progressRepository.deleteById(id);
  }
}
