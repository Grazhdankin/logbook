package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.service.TankService;
import com.gt.logbook.service.TanksLogService;
import com.gt.logbook.web.dto.TanksLogDto;
import com.gt.logbook.web.dto.mapper.TanksLogDtoMapper;
import com.gt.logbook.web.endpoint.TanksLogEndpoint;

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
    public List<TanksLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<TanksLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<TanksLogDto> findByGeneralLogId(Long id) {
        return mapper.toDto(service.findByGeneralLogId(id));
    }

    @Override
    public List<TanksLogDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Override
    public TanksLogDto save(TanksLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)
                .setGeneralLog(generalLogService.findOne(dto.getGeneralLogId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))
                .setTank(tankService.findOne(dto.getTankId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid tankId '" + dto.getTankId() + "'!")))));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}