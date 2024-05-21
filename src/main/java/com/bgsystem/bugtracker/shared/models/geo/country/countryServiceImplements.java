package com.bgsystem.bugtracker.shared.models.geo.country;

import com.bgsystem.bugtracker.shared.service.DefaultServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class countryServiceImplements extends DefaultServiceImplements<countryDTO, countryDTO, countryDTO, countryForm, countryEntity, Long> {

    private final countryRepository countryRepository;

    @Autowired
    @Lazy
    public countryServiceImplements(
            countryRepository repository,
            countryMapper mapper,
            countryPredicate commonPathExpression
    ) {
        super(repository, mapper, commonPathExpression);
        this.countryRepository = repository;
    }

    public Set<countryDTO> bulkAdd(Set<countryForm> countryForms) {

        if (countryForms == null || countryForms.isEmpty())
            return null;

        Set<countryEntity> countryEntities = countryForms.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toSet());

        for (countryEntity country : countryEntities){
            if (!countryRepository.existsByName(country.getName()))
                repository.save(country);

        }

        return countryEntities.stream().map(mapper::toListDTO).collect(Collectors.toSet());
    }

}
