package com.bgsystem.bugtracker.models.client.business;

import com.bgsystem.bugtracker.shared.controller.DefaultController;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController extends DefaultController<BusinessDTO, BusinessMiniDTO, BusinessListDTO, BusinessForm, Long> {

    private BusinessServiceImplements service;
    protected BusinessController(BusinessServiceImplements service) {
        super(service);
        this.service = service;
    }

    @GetMapping("test/{name}")
    public Page<BusinessListDTO> test(@PathVariable String name){
        return ResponseEntity.ok(service.test(name)).getBody();
    }

    /*
    @GetMapping("/list")
    public ResponseEntity<Collection<BusinessListDTO>> getAllForList() throws ElementNotFoundException {
        return ResponseEntity.ok(service.getAllForList(Optional.empty())) ;
    }

    @GetMapping("/list/page")
    public Page<BusinessListDTO> getAllForListPageable(@RequestBody PageableRequest pageableRequest) throws ElementNotFoundException{

        return service.getPageableList(Optional.empty(), pageableRequest);

    }

    @GetMapping("/list/page/{id}")
    public Page<BusinessListDTO> getAllForListPageable(@RequestBody PageableRequest pageableRequest, @PathVariable Long id) throws ElementNotFoundException{
        return service.getPageableList(Optional.of(id), pageableRequest);
    }

    @GetMapping("update-list-view/{id}")
    public BusinessListDTO updateListView(@PathVariable Long id) throws ElementNotFoundException {
        return service.updateListView(id);
    }*/

}
