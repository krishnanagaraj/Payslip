package com.pipemonk.validator;

import com.pipemonk.bean.Payslip;
import com.pipemonk.util.PayslipConstants;
import com.pipemonk.util.PayslipUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Validates the get payslip API request.
 */
public class PayslipValidator {

    private Payslip.Input payslipInput;
    public String errors;

    public PayslipValidator(Payslip.Input payslipInput) {
        this.payslipInput = payslipInput;
    }

    public void validate() {
        this.processInputValidation();
        this.postValidation();
    }

    public void processInputValidation() {
        if (PayslipUtils.isNullOrEmpty(payslipInput.getGrade())) {
            errors = String.format(ErrorRepository.MANDATORY_PARAMETER_MISSING.getErrorMessage(), PayslipConstants.PARAM_GRADE);
        }

        if (errors != null) {
            throw new ValidationException(errors);
        }
    }

    public void postValidation() {

        String employeeGrade = payslipInput.getGrade();
        if (!(StringUtils.containsIgnoreCase(employeeGrade, PayslipConstants.GRADE_VALUE_G1) || StringUtils.containsIgnoreCase(employeeGrade, PayslipConstants.GRADE_VALUE_G2))) {
            errors = String.format(ErrorRepository.INVALID_PARAMETER.getErrorMessage(), PayslipConstants.PARAM_GRADE);
        }

        if (errors != null) {
            throw new ValidationException(errors);
        }
    }


}
