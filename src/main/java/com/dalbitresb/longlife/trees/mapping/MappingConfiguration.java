package com.dalbitresb.longlife.trees.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("treesMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public BigTreeMapper bigTreeMapper() {
    return new BigTreeMapper();
  }

  @Bean
  public GreenLeafMapper greenLeafMapper() {
    return new GreenLeafMapper();
  }
}
