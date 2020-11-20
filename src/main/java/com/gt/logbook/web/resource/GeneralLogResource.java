package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;
import static com.gt.logbook.web.resource.Paths.GENERAL_LOGS_API_PATH;

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
import com.gt.logbook.web.dto.GeneralLogDto;
import com.gt.logbook.web.dto.Group;
import com.gt.logbook.web.endpoint.GeneralLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = BASE_API_PATH + GENERAL_LOGS_API_PATH)
public class GeneralLogResource {

    private final GeneralLogEndpoint endpoint;

    public GeneralLogResource(GeneralLogEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<GeneralLogDto> findAll() {
        return endpoint.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GeneralLogDto>> findOne(@PathVariable Long id) {
        return endpoint.findOne(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/revisions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<GeneralLogDto> findAllRevisions(@PathVariable Long id) {
        return endpoint.findAllRevisions(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GeneralLogDto> save(@Validated(Group.Create.class) @RequestBody GeneralLogDto dto) {
        return endpoint.save(dto);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GeneralLogDto> update(@Validated(Group.Update.class) @RequestBody GeneralLogDto dto) {
        return endpoint.save(dto);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return endpoint.delete(id);
    }
}
