package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.service.PassageLogService;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.dto.PassageLogDto;
import com.gt.logbook.web.dto.mapper.PassageLogDtoMapper;
import com.gt.logbook.web.endpoint.PassageLogEndpoint;

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
    public List<PassageLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<PassageLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<PassageLogDto> findByGeneralLogId(Long id) {
        return mapper.toDto(service.findByGeneralLogId(id));
    }

    @Override
    public List<PassageLogDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Transactional
    @Override
    public PassageLogDto save(PassageLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)
                .setGeneralLog(generalLogService.findOne(dto.getGeneralLogId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))
                .setOfficerOfTheWatch(userService.findOne(dto.getOfficerOfTheWatchId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid officerOfTheWatchId '" + dto.getOfficerOfTheWatchId() + "'!")))
                .setSeamenOfTheWatch(userService.findOne(dto.getSeamenOfTheWatchId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid seamenOfTheWatchId '" + dto.getSeamenOfTheWatchId() + "'!")))));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}