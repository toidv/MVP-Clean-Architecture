package com.example.toidv.rxexample.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private List<Error> errors = new ArrayList<Error>();
    @SerializedName("documentation_url")
    @Expose
    private String documentationUrl;

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors The errors
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    /**
     * @return The documentationUrl
     */
    public String getDocumentationUrl() {
        return documentationUrl;
    }

    /**
     * @param documentationUrl The documentation_url
     */
    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }

}
