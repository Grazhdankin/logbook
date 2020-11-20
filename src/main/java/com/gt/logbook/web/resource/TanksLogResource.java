package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;
import static com.gt.logbook.web.resource.Paths.TANKS_LOGS_API_PATH;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

@RestController
@RequestMapping(path = BASE_API_PATH + TANKS_LOGS_API_PATH)
public class TanksLogResource {

    private final TanksLogEndpoint endpoint;

    public TanksLogResource(TanksLogEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TanksLogDto> findAll() {
        return endpoint.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TanksLogDto> findOne(@PathVariable Long id) {
        return endpoint.findOne(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping(path = "/general-logs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TanksLogDto> findByGeneralLogId(@PathVariable Long id) {
        return endpoint.findByGeneralLogId(id);
    }

    @Secured({"ROLE_USER", "ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping(path = "/revisions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TanksLogDto> findAllRevisions(@PathVariable Long id) {
        return endpoint.findAllRevisions(id);
    }

    @Secured({"ROLE_CREATOR", "ROLE_EDITOR", "ROLE_ADMIN"})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TanksLogDto save(@Validated(Group.Create.class) @RequestBody TanksLogDto dto) {
        return endpoint.save(dto);
    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TanksLogDto update(@Validated(Group.Update.class) @RequestBody TanksLogDto dto) {
        return endpoint.save(dto);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        endpoint.delete(id);
    }
}