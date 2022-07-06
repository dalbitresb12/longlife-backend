package com.dalbitresb.longlife.trees.domain.persistence;

import com.dalbitresb.longlife.trees.domain.model.entity.GreenLeaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GreenLeafRepository extends JpaRepository<GreenLeaf, Long> {
  List<GreenLeaf> findByBigTreeId(Long bigTreeId);
  Optional<GreenLeaf> findByIdAndBigTreeId(Long id, Long bigTreeId);
  Optional<GreenLeaf> findByTitleAndBigTreeId(String title, Long bigTreeId);
}
