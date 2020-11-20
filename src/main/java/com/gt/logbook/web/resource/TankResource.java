package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;
import static com.gt.logbook.web.resource.Paths.TANKS_API_PATH;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.gt.logbook.web.dto.Group;
import com.gt.logbook.web.dto.TankDto;
import com.gt.logbook.web.endpoint.TankEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = BASE_API_PATH + TANKS_API_PATH)
public class TankResource {

    private final TankEndpoint endpoint;

    public TankResource(TankEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<TankDto> findAll() {
        return endpoint.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TankDto>> findOne(@PathVariable Long id) {
        return endpoint.findOne(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/revisions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TankDto> findAllRevisions(@PathVariable Long id) {
        return endpoint.findAllRevisions(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<TankDto> save(@Validated(Group.Create.class) @RequestBody TankDto dto, ServerWebExchange exchange) {
        return endpoint.save(dto);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TankDto> update(@Validated(Group.Update.class) @RequestBody TankDto dto) {
        return endpoint.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return endpoint.delete(id);
    }
}