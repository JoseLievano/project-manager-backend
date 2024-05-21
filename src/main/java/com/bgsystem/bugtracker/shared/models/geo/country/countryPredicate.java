package com.bgsystem.bugtracker.shared.models.geo.country;

import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import org.springframework.stereotype.Service;

@Service
public class countryPredicate extends CommonPathExpression<countryEntity> {

    public countryPredicate() {
        super();
    }

}
