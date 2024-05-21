package com.bgsystem.bugtracker.shared.models.pageableRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class SortInfo {

    private String property;

    private Boolean isAscending;

}
