package com.bgsystem.bugtracker.shared.models.listRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class FilterOperator {

    private String operator;

    private String value;

    private String field;

}
