package com.bgsystem.bugtracker.models.client.bsPriority;

import com.bgsystem.bugtracker.exeptions.ElementNotFoundException;
import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/bs_priority")
public class bsPriorityController extends DefaultController <bsPriorityDTO, bsPriorityMiniDTO, bsPriorityListDTO, bsPriorityForm, Long> {

    private final bsPriorityServiceImplements bsPriorityService;

    public bsPriorityController(bsPriorityServiceImplements service) {
        super(service);
        this.bsPriorityService = service;
    }

    @PutMapping("/update-order")
    public ResponseEntity<Set<bsPriorityListDTO>> updateOrder (@RequestBody Set<bsPriorityForm> forms) throws ElementNotFoundException {
        return ResponseEntity.ok(bsPriorityService.updateOrder(forms));
    }

}
