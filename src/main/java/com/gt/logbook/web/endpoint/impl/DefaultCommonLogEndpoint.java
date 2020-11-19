package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.service.CommonLogService;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.web.dto.CommonLogDto;
import com.gt.logbook.web.dto.mapper.CommonLogDtoMapper;
import com.gt.logbook.web.endpoint.CommonLogEndpoint;

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
    public List<CommonLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<CommonLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<CommonLogDto> findByGeneralLogId(Long id) {
        return mapper.toDto(service.findByGeneralLogId(id));
    }

    @Override
    public List<CommonLogDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Transactional
    @Override
    public CommonLogDto save(CommonLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)
                .setGeneralLog(generalLogService.findOne(dto.getGeneralLogId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}