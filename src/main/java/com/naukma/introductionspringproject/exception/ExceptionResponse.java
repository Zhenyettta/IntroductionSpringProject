package com.naukma.introductionspringproject.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private String timeStamp;
    private String trace;
    private String path;

    public ExceptionResponse(Map<String, Object> errorAttributes) {
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimeStamp(errorAttributes.get("timestamp").toString());
        this.setTrace((String) errorAttributes.get("trace"));
    }
}
