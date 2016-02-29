package com.pipemonk.controller;

import com.pipemonk.bean.Payslip;
import com.pipemonk.service.iface.IFileService;
import com.pipemonk.service.iface.IPayslipService;
import com.pipemonk.service.impl.PayslipService;
import com.pipemonk.service.impl.FileService;
import com.pipemonk.util.PayslipConstants;
import com.pipemonk.validator.PayslipValidator;
import com.pipemonk.validator.ErrorRepository;
import com.pipemonk.validator.ValidationException;

/**
 * Handles the get Payslip API request
 */
public class PayslipController {

    /**
     * File Service.
     */
    protected IFileService fileService;

    /**
     * Payslip Service.
     */
    protected IPayslipService payslipService;

    /**
     * Core method handling the Payslip generation API request
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            if (args.length > 1) {

                String basicSalary = args[0];
                String employeeGrade = args[1];

                PayslipController payslipController = new PayslipController();
                Payslip.Output payslipOutput = payslipController.computePayslip(basicSalary, employeeGrade);

                if (payslipOutput.getFuelAllowance() > 0) {
                    payslipController.createPayslipFile(payslipOutput);
                } else {
                    System.out.println("Failed to compute Payslip.");
                }
            } else {
                System.out.println(String.format(ErrorRepository.MANDATORY_PARAMETERS_MISSING.getErrorMessage(), PayslipConstants.PARAM_BASIC, PayslipConstants.PARAM_GRADE));
            }
        } catch (ValidationException e) {
            System.out.println(e.getErrorResponse());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method calculates the payslip information and creates a payslip PDF.
     *
     * @param basicSalary
     * @param employeeGrade
     * @return status TRUE or FALSE
     */
    public Payslip.Output computePayslip(String basicSalary, String employeeGrade) throws Exception {

        Payslip.Input payslipInput = new Payslip.Input();
        payslipInput.setBasic(Double.valueOf(basicSalary));
        payslipInput.setGrade(employeeGrade);

        // Validate the provided input
        new PayslipValidator(payslipInput).validate();

        // Compute payslip information
        payslipService = new PayslipService();
        Payslip.Output payslipOutput = payslipService.computeEmployeePayslip(payslipInput);

        return payslipOutput;
    }

    private void createPayslipFile(Payslip.Output payslipOutput) {
        try {
            // Create a PDF file for the payslip
            fileService = new FileService();
            fileService.createPdfFileForPayslips(payslipOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setPayslipService(IPayslipService payslipService) {
        this.payslipService = payslipService;
    }

    public void setFileService(IFileService fileService) {
        this.fileService = fileService;
    }

}
