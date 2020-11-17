package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;
import static com.gt.logbook.web.resource.Paths.TANKS_API_PATH;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Flux<TankDto> findAll() {
        return Flux.defer(() -> Flux.fromIterable(endpoint.findAll()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TankDto>> findOne(@PathVariable Long id) {
        return Mono.fromCallable(() -> endpoint.findOne(id)).map(ResponseEntity::of);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TankDto> save(@Validated(Group.Create.class) @RequestBody TankDto dto) {
        return Mono.fromCallable(() -> endpoint.save(dto));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TankDto> update(@Validated(Group.Update.class) @RequestBody TankDto dto) {
        return Mono.fromCallable(() -> endpoint.save(dto));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return Mono.fromRunnable(() -> endpoint.delete(id));
    }
}