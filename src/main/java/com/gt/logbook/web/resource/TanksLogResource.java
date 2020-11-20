package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;
import static com.gt.logbook.web.resource.Paths.TANKS_LOGS_API_PATH;

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
import com.gt.logbook.web.dto.TanksLogDto;
import com.gt.logbook.web.endpoint.TanksLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = BASE_API_PATH + TANKS_LOGS_API_PATH)
public class TanksLogResource {

    private final TanksLogEndpoint endpoint;

    public TanksLogResource(TanksLogEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TanksLogDto> findAll() {
        return endpoint.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TanksLogDto>> findOne(@PathVariable Long id) {
        return endpoint.findOne(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/general-logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TanksLogDto> findByGeneralLogId(@PathVariable Long id) {
        return endpoint.findByGeneralLogId(id);
    }

    @GetMapping(path = "/revisions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TanksLogDto> findAllRevisions(@PathVariable Long id) {
        return endpoint.findAllRevisions(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TanksLogDto> save(@Validated(Group.Create.class) @RequestBody TanksLogDto dto) {
        return endpoint.save(dto);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TanksLogDto> update(@Validated(Group.Update.class) @RequestBody TanksLogDto dto) {
        return endpoint.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return endpoint.delete(id);
    }
}