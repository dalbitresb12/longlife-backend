package com.dalbitresb.longlife.trees.domain.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "green_leaves")
public class GreenLeaf {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String scenario;

  @Column(nullable = false)
  private String tip;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "big_tree_id", nullable = false)
  private BigTree bigTree;
}
