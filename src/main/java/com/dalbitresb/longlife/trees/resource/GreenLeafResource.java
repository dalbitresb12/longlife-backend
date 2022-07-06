package com.dalbitresb.longlife.trees.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class GreenLeafResource {
  private Long id;
  private String title;
  private String scenario;
  private String tip;
  private BigTreeResource bigTree;
}
