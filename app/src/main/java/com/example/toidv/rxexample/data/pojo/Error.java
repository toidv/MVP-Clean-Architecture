package com.example.toidv.rxexample.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("code")
    @Expose
    private String code;

    /**
     * @return The resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * @param resource The resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * @return The field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field The field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

}
