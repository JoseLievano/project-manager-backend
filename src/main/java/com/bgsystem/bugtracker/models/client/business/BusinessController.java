package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.exeptions.ElementNotFoundExeption;
import com.bgsystem.bugtracker.shared.controller.DefaultController;
import com.bgsystem.bugtracker.shared.models.pageableRequest.PageableRequest;
import com.bgsystem.bugtracker.shared.service.DefaultService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultController<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, Long> {

    private BusinessServiceImplements service;
    protected BusinessController(BusinessServiceImplements service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<BusinessListDTO>> getAllForList() throws ElementNotFoundExeption {
        return ResponseEntity.ok(service.getAllForList(Optional.empty())) ;
    }

    @GetMapping("/list/{id}")
    public Collection<BusinessListDTO> getAllForListByUserId(@PathVariable Long id) throws ElementNotFoundExeption {
        return service.getAllForList(Optional.of(id));
    }

    @GetMapping("/list/page")
    public Page<BusinessListDTO> getAllForListPageable(@RequestBody PageableRequest pageableRequest) throws ElementNotFoundExeption{

        return service.getPageableList(Optional.empty(), pageableRequest);

    }

    @GetMapping("/list/page/{id}")
    public Page<BusinessListDTO> getAllForListPageable(@RequestBody PageableRequest pageableRequest, @PathVariable Long id) throws ElementNotFoundExeption{
        return service.getPageableList(Optional.of(id), pageableRequest);
    }

    @GetMapping("update-list-view/{id}")
    public BusinessListDTO updateListView(@PathVariable Long id) throws ElementNotFoundExeption {
        return service.updateListView(id);
    }

}
