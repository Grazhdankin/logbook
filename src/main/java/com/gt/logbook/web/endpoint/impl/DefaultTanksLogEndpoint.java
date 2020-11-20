package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.service.TankService;
import com.gt.logbook.service.TanksLogService;
import com.gt.logbook.web.dto.TanksLogDto;
import com.gt.logbook.web.dto.mapper.TanksLogDtoMapper;
import com.gt.logbook.web.endpoint.TanksLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultTanksLogEndpoint implements TanksLogEndpoint {

    private final TanksLogService service;
    private final TanksLogDtoMapper mapper;
    private final GeneralLogService generalLogService;
    private final TankService tankService;

    public DefaultTanksLogEndpoint(TanksLogService service,
                                   TanksLogDtoMapper mapper,
                                   GeneralLogService generalLogService,
                                   TankService tankService) {
        this.service = service;
        this.mapper = mapper;
        this.generalLogService = generalLogService;
        this.tankService = tankService;
    }

    @Override
    public Flux<TanksLogDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<TanksLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<TanksLogDto> findByGeneralLogId(Long id) {
        return service.findByGeneralLogId(id).map(mapper::toDto);
    }

    @Override
    public Flux<TanksLogDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Transactional
    @Override
    public Mono<TanksLogDto> save(TanksLogDto dto) {
        return generalLogService.findOne(dto.getGeneralLogId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))
                .zipWith(tankService.findOne(dto.getTankId())
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!"))),
                        (generalLog, tank) -> mapper.toEntity(dto).setGeneralLog(generalLog).setTank(tank))
                .flatMap(service::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}