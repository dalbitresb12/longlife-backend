package com.dalbitresb.longlife.trees.api;

import com.dalbitresb.longlife.trees.domain.service.BigTreeService;
import com.dalbitresb.longlife.trees.mapping.BigTreeMapper;
import com.dalbitresb.longlife.trees.resource.BigTreeRequest;
import com.dalbitresb.longlife.trees.resource.BigTreeResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "BigTree")
@RestController
@RequestMapping("api/trees")
public class BigTreeController {
  private final BigTreeService service;

  private final BigTreeMapper mapper;

  public BigTreeController(BigTreeService service, BigTreeMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<BigTreeResource> getAll() {
    val resources = service.getAll();
    return mapper.toResourceList(resources);
  }

  @GetMapping("{id}")
  public BigTreeResource getById(@PathVariable Long id) {
    val entity = service.getById(id);
    return mapper.toResource(entity);
  }

  @PostMapping
  public BigTreeResource create(@RequestBody BigTreeRequest request) {
    val mapped = mapper.toModel(request);
    val entity = service.create(mapped);
    return mapper.toResource(entity);
  }

  @PutMapping("{id}")
  public BigTreeResource update(@PathVariable Long id, @RequestBody BigTreeRequest request) {
    val mapped = mapper.toModel(request);
    val entity = service.update(id, mapped);
    return mapper.toResource(entity);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
