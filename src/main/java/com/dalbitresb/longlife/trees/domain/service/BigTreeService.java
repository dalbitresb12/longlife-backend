package com.dalbitresb.longlife.trees.domain.service;

import com.dalbitresb.longlife.trees.domain.model.entity.BigTree;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BigTreeService {
  List<BigTree> getAll();
  BigTree getById(Long id);
  BigTree create(BigTree request);
  BigTree update(Long id, BigTree request);
  ResponseEntity<?> delete(Long id);
}
