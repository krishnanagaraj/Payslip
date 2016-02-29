package com.pipemonk.validator;

/**
 * Error enums
 */
public enum ErrorRepository {

    MANDATORY_PARAMETERS_MISSING("Mandatory '%s', '%s' parameter missing."),
    MANDATORY_PARAMETER_MISSING("Mandatory '%s' parameter missing."),
    INVALID_PARAMETER("Invalid '%s' parameter.");



    private String error = "";

    ErrorRepository(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return error;
    }

}
