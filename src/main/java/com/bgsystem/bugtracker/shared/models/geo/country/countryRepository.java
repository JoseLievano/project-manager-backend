package com.bgsystem.bugtracker.shared.models.geo.country;

import com.bgsystem.bugtracker.shared.repository.DefaultRepository;

public interface countryRepository extends DefaultRepository<countryEntity, Long> {

    countryEntity findByName(String name);

    boolean existsByName(String name);

}
