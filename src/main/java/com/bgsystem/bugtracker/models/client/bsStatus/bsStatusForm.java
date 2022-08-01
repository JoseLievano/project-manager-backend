package com.bgsystem.bugtracker.models.client.bsStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class bsStatusForm {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String color;

    @NotNull(message = "Business is required")
    private Long business;

}
