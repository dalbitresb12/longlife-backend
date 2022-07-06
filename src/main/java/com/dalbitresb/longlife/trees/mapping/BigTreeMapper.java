package com.dalbitresb.longlife.trees.mapping;

import com.dalbitresb.longlife.shared.mapping.EnhancedModelMapper;
import com.dalbitresb.longlife.trees.domain.model.entity.BigTree;
import com.dalbitresb.longlife.trees.resource.BigTreeRequest;
import com.dalbitresb.longlife.trees.resource.BigTreeResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BigTreeMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public BigTreeResource toResource(BigTree model) {
    return mapper.map(model, BigTreeResource.class);
  }

  public List<BigTreeResource> toResourceList(List<BigTree> models) {
    return mapper.mapList(models, BigTreeResource.class);
  }

  public BigTree toModel(BigTreeRequest request) {
    return mapper.map(request, BigTree.class);
  }
}
