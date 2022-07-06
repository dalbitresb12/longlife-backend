package com.dalbitresb.longlife.trees.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class GreenLeafRequest {
  @NotNull
  @NotBlank
  private String title;

  @NotNull
  @NotBlank
  private String scenario;

  @NotNull
  @NotBlank
  private String tip;
}
