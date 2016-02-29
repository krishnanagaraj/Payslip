package com.pipemonk.validator;

/**
 * Used in Payslip API validations.
 */
public class ValidationException extends RuntimeException
{
    String errorResponse;

    public ValidationException(String error)
    {
        this.errorResponse = error;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(String errorResponse) {
        this.errorResponse = errorResponse;
    }
}