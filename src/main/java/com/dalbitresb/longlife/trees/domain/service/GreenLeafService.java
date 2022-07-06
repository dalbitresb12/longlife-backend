package com.dalbitresb.longlife.trees.domain.service;

import com.dalbitresb.longlife.trees.domain.model.entity.GreenLeaf;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GreenLeafService {
  List<GreenLeaf> getAllByBigTreeId(Long bigTreeId);
  GreenLeaf getByIdAndBigTreeId(Long id, Long bigTreeId);
  GreenLeaf create(Long bigTreeId, GreenLeaf request);
  GreenLeaf update(Long bigTreeId, Long id, GreenLeaf request);
  ResponseEntity<?> delete(Long bigTreeId, Long id);
}
