package com.dalbitresb.longlife.trees.service;

import com.dalbitresb.longlife.shared.exception.ResourceNotFoundException;
import com.dalbitresb.longlife.shared.exception.ResourceValidationException;
import com.dalbitresb.longlife.trees.domain.model.entity.GreenLeaf;
import com.dalbitresb.longlife.trees.domain.persistence.BigTreeRepository;
import com.dalbitresb.longlife.trees.domain.persistence.GreenLeafRepository;
import com.dalbitresb.longlife.trees.domain.service.GreenLeafService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class GreenLeafServiceImpl implements GreenLeafService {
  private static final String ENTITY = "GreenLeaf";

  private final BigTreeRepository bigTreeRepository;

  private final GreenLeafRepository greenLeafRepository;

  private final Validator validator;

  public GreenLeafServiceImpl(BigTreeRepository bigTreeRepository, GreenLeafRepository greenLeafRepository, Validator validator) {
    this.bigTreeRepository = bigTreeRepository;
    this.greenLeafRepository = greenLeafRepository;
    this.validator = validator;
  }

  @Override
  public List<GreenLeaf> getAllByBigTreeId(Long bigTreeId) {
    return greenLeafRepository.findByBigTreeId(bigTreeId);
  }

  @Override
  public GreenLeaf getByIdAndBigTreeId(Long id, Long bigTreeId) {
    if (!bigTreeRepository.existsById(bigTreeId)) {
      throw new ResourceNotFoundException("BigTree", bigTreeId);
    }

    val entity = greenLeafRepository.findByIdAndBigTreeId(id, bigTreeId);

    if (entity.isEmpty()) {
      throw new ResourceNotFoundException(ENTITY, id);
    }

    return entity.get();
  }

  @Override
  public GreenLeaf create(Long bigTreeId, GreenLeaf request) {
    Set<ConstraintViolation<GreenLeaf>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      throw new ResourceValidationException(ENTITY, violations);
    }

    val greenLeafWithTitle = greenLeafRepository.findByTitleAndBigTreeId(request.getTitle(), bigTreeId);

    if (greenLeafWithTitle.isPresent()) {
      throw new ResourceValidationException(ENTITY, "A green leaf with the same title already exists.");
    }

    val bigTree = bigTreeRepository.findById(bigTreeId);

    if (bigTree.isEmpty()) {
      throw new ResourceNotFoundException("BigTree", bigTreeId);
    }

    request.setBigTree(bigTree.get());

    return greenLeafRepository.save(request);
  }

  @Override
  public GreenLeaf update(Long bigTreeId, Long id, GreenLeaf request) {
    Set<ConstraintViolation<GreenLeaf>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      throw new ResourceValidationException(ENTITY, violations);
    }

    val greenLeafWithTitle = greenLeafRepository.findByTitleAndBigTreeId(request.getTitle(), bigTreeId);

    if (greenLeafWithTitle.isPresent() && !greenLeafWithTitle.get().getId().equals(id)) {
      throw new ResourceValidationException(ENTITY, "A green leaf with the same title already exists.");
    }

    val entity = greenLeafRepository.findByIdAndBigTreeId(id, bigTreeId);

    if (entity.isEmpty()) {
      throw new ResourceNotFoundException(ENTITY, id);
    }

    GreenLeaf updated = entity.get()
            .withTitle(request.getTitle())
            .withScenario(request.getScenario())
            .withTip(request.getTip());

    return greenLeafRepository.save(updated);
  }

  @Override
  public ResponseEntity<?> delete(Long bigTreeId, Long id) {
    val entity = greenLeafRepository.findByIdAndBigTreeId(id, bigTreeId);

    if (entity.isEmpty()) {
      throw new ResourceNotFoundException(ENTITY, id);
    }

    greenLeafRepository.delete(entity.get());
    return ResponseEntity.ok().build();
  }
}
