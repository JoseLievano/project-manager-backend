package com.bgsystem.bugtracker.models.HQ.mainHQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class MainHQForm {

    private Long id;

    @NotBlank
    private String name;

}
