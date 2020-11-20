package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.service.PassageLogService;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.dto.PassageLogDto;
import com.gt.logbook.web.dto.mapper.PassageLogDtoMapper;
import com.gt.logbook.web.endpoint.PassageLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultPassageLogEndpoint implements PassageLogEndpoint {

    private final PassageLogService service;
    private final PassageLogDtoMapper mapper;
    private final GeneralLogService generalLogService;
    private final UserService userService;

    public DefaultPassageLogEndpoint(PassageLogService service,
                                     PassageLogDtoMapper mapper,
                                     GeneralLogService generalLogService,
                                     UserService userService) {
        this.service = service;
        this.mapper = mapper;
        this.generalLogService = generalLogService;
        this.userService = userService;
    }

    @Override
    public Flux<PassageLogDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<PassageLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<PassageLogDto> findByGeneralLogId(Long id) {
        return service.findByGeneralLogId(id).map(mapper::toDto);
    }

    @Override
    public Flux<PassageLogDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Transactional
    @Override
    public Mono<PassageLogDto> save(PassageLogDto dto) {
        return Mono.zip(
                generalLogService.findOne(dto.getGeneralLogId())
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!"))),
                userService.findOne(dto.getOfficerOfTheWatchId())
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid officerOfTheWatchId '" + dto.getOfficerOfTheWatchId() + "'!"))),
                userService.findOne(dto.getSeamenOfTheWatchId())
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid seamenOfTheWatchId '" + dto.getSeamenOfTheWatchId() + "'!"))))
                .map(tuple -> mapper.toEntity(dto).setGeneralLog(tuple.getT1()).setOfficerOfTheWatch(tuple.getT2()).setSeamenOfTheWatch(tuple.getT3()))
                .flatMap(service::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}