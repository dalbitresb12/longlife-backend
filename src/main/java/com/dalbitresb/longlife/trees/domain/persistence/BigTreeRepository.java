package com.dalbitresb.longlife.trees.domain.persistence;

import com.dalbitresb.longlife.trees.domain.model.entity.BigTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BigTreeRepository extends JpaRepository<BigTree, Long> {
  boolean existsById(Long id);
  Optional<BigTree> findByUsername(String username);
}
