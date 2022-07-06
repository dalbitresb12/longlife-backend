package com.dalbitresb.longlife.trees.api;

import com.dalbitresb.longlife.trees.domain.service.GreenLeafService;
import com.dalbitresb.longlife.trees.mapping.GreenLeafMapper;
import com.dalbitresb.longlife.trees.resource.GreenLeafRequest;
import com.dalbitresb.longlife.trees.resource.GreenLeafResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "GreenLeaf")
@RestController
@RequestMapping("api/trees/{treeId}/leafs")
public class GreenLeafController {
  private final GreenLeafService service;

  private final GreenLeafMapper mapper;

  public GreenLeafController(GreenLeafService service, GreenLeafMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<GreenLeafResource> getAll(@PathVariable Long treeId) {
    val entities = service.getAllByBigTreeId(treeId);
    return mapper.toResourceList(entities);
  }

  @GetMapping("{id}")
  public GreenLeafResource getById(@PathVariable Long treeId, @PathVariable Long id) {
    val entity = service.getByIdAndBigTreeId(id, treeId);
    return mapper.toResource(entity);
  }

  @PostMapping
  public GreenLeafResource create(@PathVariable Long treeId, @RequestBody GreenLeafRequest request) {
    val mapped = mapper.toModel(request);
    val entity = service.create(treeId, mapped);
    return mapper.toResource(entity);
  }

  @PutMapping("{id}")
  public GreenLeafResource update(@PathVariable Long treeId, @PathVariable Long id, @RequestBody GreenLeafRequest request) {
    val mapped = mapper.toModel(request);
    val entity = service.update(treeId, id, mapped);
    return mapper.toResource(entity);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long treeId, @PathVariable Long id) {
    return service.delete(treeId, id);
  }
}
