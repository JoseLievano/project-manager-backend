package com.bgsystem.bugtracker.shared.models.listRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Builder
@Getter
@Setter
public class FilterRequest {

    private String field;

    private String type;

    private ArrayList<FilterOperator> operations;

}


