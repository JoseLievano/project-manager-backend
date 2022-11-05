package com.bgsystem.bugtracker.shared.models.pageableRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
public class PageableRequest {

    private int page;

    private int size;

    private List<SortInfo> sort;

    /*private Boolean isAscending;*/

    public PageRequest getPageRequest(){

        List<Sort.Order> sorts = new ArrayList<>();

        if (sort.size() > 0) {
            for (SortInfo sortInfo : sort){
                if (sortInfo.getIsAscending()){
                    Sort.Direction actualDirection = Sort.Direction.ASC;
                    sorts.add(new Sort.Order(actualDirection, sortInfo.getProperty()));
                }else{
                    Sort.Direction actualDirection = Sort.Direction.DESC;
                    sorts.add(new Sort.Order(actualDirection, sortInfo.getProperty()));
                }
            }
        }else{
            Sort.Direction actualDirection = Sort.Direction.ASC;
            sorts.add(new Sort.Order(actualDirection, "id"));
        }


        return PageRequest.of(page, size, Sort.by(sorts));

    }

}
