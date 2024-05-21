package com.bgsystem.bugtracker.shared.models.geo.country;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/country")
public class countryController extends DefaultController<countryDTO, countryDTO, countryDTO, countryForm, Long> {

    private final countryServiceImplements service;

    @Autowired
    protected countryController(countryServiceImplements service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/bulk_add")
    public ResponseEntity<Set<countryDTO>> bulkAdd(@RequestBody Set<countryForm> countryForms) {
        return ResponseEntity.ok(service.bulkAdd(countryForms));

    }
}
