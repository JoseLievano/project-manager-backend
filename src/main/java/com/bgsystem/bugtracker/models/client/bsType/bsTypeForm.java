package com.bgsystem.bugtracker.models.client.bsType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsTypeForm {

    private Long id;

    private String name;

    private Long business;

    private Set<Long> tasks;


}
