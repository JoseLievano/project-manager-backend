package com.bgsystem.bugtracker.shared.models.listRequest;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter

public class FilterRequest {

    private String by;

    private Long id;

}
