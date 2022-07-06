package com.dalbitresb.longlife.trees.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "big_trees")
public class BigTree {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String gender;

  @Column(nullable = false)
  private Date bornAt;

  @OneToMany(mappedBy = "bigTree", cascade = CascadeType.REMOVE)
  private Set<GreenLeaf> greenLeaves;
}
