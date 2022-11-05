package com.bgsystem.bugtracker.shared.models.pageableRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@Builder
@Getter
@Setter
public class PageableRequest {

    private int page;

    private int size;

    private String sort;

    private Boolean isAscending;

    public PageRequest getPageRequest(){

        Sort.Direction newDirection = Sort.Direction.DESC;

        if (isAscending){
            newDirection = Sort.Direction.ASC;
        }


        return PageRequest.of(page, size, Sort.by(newDirection, this.sort));

    }

}
