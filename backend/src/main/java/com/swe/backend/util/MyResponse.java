package com.swe.backend.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyResponse {
    private String additionalJSON;
    private Object originalData;

    public MyResponse(String additionalJSON, Object originalData) {
        this.additionalJSON = additionalJSON;
        this.originalData = originalData;
    }

}
