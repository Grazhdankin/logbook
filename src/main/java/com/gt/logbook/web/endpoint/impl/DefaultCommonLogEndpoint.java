package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.service.CommonLogService;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.web.dto.CommonLogDto;
import com.gt.logbook.web.dto.mapper.CommonLogDtoMapper;
import com.gt.logbook.web.endpoint.CommonLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultCommonLogEndpoint implements CommonLogEndpoint {

    private final CommonLogService service;
    private final CommonLogDtoMapper mapper;
    private final GeneralLogService generalLogService;

    public DefaultCommonLogEndpoint(CommonLogService service,
                                    CommonLogDtoMapper mapper,
                                    GeneralLogService generalLogService) {
        this.service = service;
        this.mapper = mapper;
        this.generalLogService = generalLogService;
    }

    @Override
    public Flux<CommonLogDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<CommonLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<CommonLogDto> findByGeneralLogId(Long id) {
        return service.findByGeneralLogId(id).map(mapper::toDto);
    }

    @Override
    public Flux<CommonLogDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Transactional
    @Override
    public Mono<CommonLogDto> save(CommonLogDto dto) {
        return generalLogService.findOne(dto.getGeneralLogId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))
                .map(generalLog -> mapper.toEntity(dto).setGeneralLog(generalLog))
                .flatMap(service::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}