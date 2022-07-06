package com.dalbitresb.longlife.trees.service;

import com.dalbitresb.longlife.shared.exception.ResourceNotFoundException;
import com.dalbitresb.longlife.shared.exception.ResourceValidationException;
import com.dalbitresb.longlife.trees.domain.model.entity.BigTree;
import com.dalbitresb.longlife.trees.domain.persistence.BigTreeRepository;
import com.dalbitresb.longlife.trees.domain.service.BigTreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class BigTreeServiceImpl implements BigTreeService {
  private static final String ENTITY = "BigTree";

  private final BigTreeRepository repository;

  private final Validator validator;

  public BigTreeServiceImpl(BigTreeRepository repository, Validator validator) {
    this.repository = repository;
    this.validator = validator;
  }

  @Override
  public List<BigTree> getAll() {
    return repository.findAll();
  }

  @Override
  public BigTree getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
  }

  @Override
  public BigTree create(BigTree request) {
    Set<ConstraintViolation<BigTree>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      throw new ResourceValidationException(ENTITY, violations);
    }

    Optional<BigTree> bigTreeWithUsername = repository.findByUsername(request.getUsername());

    if (bigTreeWithUsername.isPresent()) {
      throw new ResourceValidationException(ENTITY, "A big tree with the same username already exists.");
    }

    validateAge(request);

    return repository.save(request);
  }

  @Override
  public BigTree update(Long id, BigTree request) {
    Set<ConstraintViolation<BigTree>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      throw new ResourceValidationException(ENTITY, violations);
    }

    Optional<BigTree> bigTreeWithUsername = repository.findByUsername(request.getUsername());

    if (bigTreeWithUsername.isPresent() && !bigTreeWithUsername.get().getId().equals(id)) {
      throw new ResourceValidationException(ENTITY, "A big tree with the same username already exists.");
    }

    Optional<BigTree> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new ResourceNotFoundException(ENTITY, id);
    }

    validateAge(request);

    BigTree updated = entity.get()
            .withUsername(request.getUsername())
            .withEmail(request.getEmail())
            .withFirstName(request.getFirstName())
            .withLastName(request.getLastName())
            .withGender(request.getGender())
            .withBornAt(request.getBornAt());

    return repository.save(updated);
  }

  @Override
  public ResponseEntity<?> delete(Long id) {
    Optional<BigTree> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new ResourceNotFoundException(ENTITY, id);
    }

    repository.delete(entity.get());
    return ResponseEntity.ok().build();
  }

  private static void validateAge(BigTree request) {
    Date now = new Date();
    Date bornAt = request.getBornAt();

    long ageInMilliseconds = Math.abs(now.getTime() - bornAt.getTime());
    long age = TimeUnit.DAYS.convert(ageInMilliseconds, TimeUnit.MILLISECONDS) / 365;

    if (age < 50) {
      throw new ResourceValidationException(ENTITY, "A big tree has to be more than 50 years old.");
    }
  }
}
