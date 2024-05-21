package com.bgsystem.bugtracker.exeptions;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponseBody {

    private String error;
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
    private String trace;

}
