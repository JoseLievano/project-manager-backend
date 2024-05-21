package com.bgsystem.bugtracker.shared.models.geo.country;

import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.stereotype.Service;

@Service
public class countryMapper implements DefaultMapper<countryDTO, countryDTO, countryDTO, countryForm, countryEntity> {

    public countryMapper() {

    }

    @Override
    public countryDTO toDTO(countryEntity entity) {
        if (entity == null)
            return null;

        return countryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .phone(entity.getPhone())
                .symbol(entity.getSymbol())
                .capital(entity.getCapital())
                .currency(entity.getCurrency())
                .continent(entity.getContinent())
                .continentCode(entity.getContinentCode())
                .alpha3(entity.getAlpha3())
                .build();
    }

    @Override
    public countryDTO toSmallDTO(countryEntity entity) {
        return toDTO(entity);
    }

    @Override
    public countryEntity toEntity(countryForm form) {

        if (form == null)
            return null;

        return countryEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .code(form.getCode())
                .phone(form.getPhone())
                .symbol(form.getSymbol())
                .capital(form.getCapital())
                .currency(form.getCurrency())
                .continent(form.getContinent())
                .continentCode(form.getContinentCode())
                .alpha3(form.getAlpha3())
                .build();
    }

    @Override
    public countryDTO toListDTO(countryEntity entity) {
        return toDTO(entity);
    }
}
