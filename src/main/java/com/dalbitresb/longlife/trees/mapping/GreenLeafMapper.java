package com.dalbitresb.longlife.trees.mapping;

import com.dalbitresb.longlife.shared.mapping.EnhancedModelMapper;
import com.dalbitresb.longlife.trees.domain.model.entity.GreenLeaf;
import com.dalbitresb.longlife.trees.resource.GreenLeafRequest;
import com.dalbitresb.longlife.trees.resource.GreenLeafResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GreenLeafMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public GreenLeafResource toResource(GreenLeaf model) {
    return mapper.map(model, GreenLeafResource.class);
  }

  public List<GreenLeafResource> toResourceList(List<GreenLeaf> models) {
    return mapper.mapList(models, GreenLeafResource.class);
  }

  public GreenLeaf toModel(GreenLeafRequest request) {
    return mapper.map(request, GreenLeaf.class);
  }
}
