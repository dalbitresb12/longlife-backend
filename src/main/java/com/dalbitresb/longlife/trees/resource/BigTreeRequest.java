package com.dalbitresb.longlife.trees.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BigTreeRequest {
  @NotNull
  @NotBlank
  private String username;

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String firstName;

  @NotNull
  @NotBlank
  private String lastName;

  @NotNull
  @NotBlank
  private String gender;

  @NotNull
  @NotBlank
  private Date bornAt;
}
