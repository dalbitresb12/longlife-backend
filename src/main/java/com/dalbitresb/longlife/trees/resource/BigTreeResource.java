package com.dalbitresb.longlife.trees.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BigTreeResource {
  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String gender;
  private Date bornAt;
}
