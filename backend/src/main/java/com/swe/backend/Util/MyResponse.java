package com.swe.backend.Util;

public class MyResponse {
    private String additionalJSON;
    private Object originalData;

    public MyResponse(String additionalJSON, Object originalData) {
        this.additionalJSON = additionalJSON;
        this.originalData = originalData;
    }

    public String getAdditionalJSON() {
        return additionalJSON;
    }

    public void setAdditionalJSON(String additionalJSON) {
        this.additionalJSON = additionalJSON;
    }

    public Object getOriginalData() {
        return originalData;
    }

    public void setOriginalData(Object originalData) {
        this.originalData = originalData;
    }
}
