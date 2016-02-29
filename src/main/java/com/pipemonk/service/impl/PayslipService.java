package com.pipemonk.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Locale;

import com.pipemonk.bean.Payslip;
import com.pipemonk.service.iface.IPayslipService;
import com.pipemonk.util.PayslipConstants;
import com.pipemonk.util.PayslipUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Handles the get payslip API request and response
 */
public class PayslipService implements IPayslipService {


    /**
     * Retrieves the payslip details
     *
     * @param payslipInput
     * @return
     */
    public Payslip.Output computeEmployeePayslip(Payslip.Input payslipInput) throws Exception {

        Payslip.Output payslipOutput = new Payslip.Output();
        try {
            Calendar now = Calendar.getInstance();
            String month = payslipInput.getMonth();
            String year = payslipInput.getYear();
            if (PayslipUtils.isNullOrEmpty(year)) {
                year = String.valueOf(now.get(Calendar.YEAR));
            }

            if (PayslipUtils.isNullOrEmpty(month)) {
                month = now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            }

            String employeeName = payslipInput.getName();
            if (PayslipUtils.isNullOrEmpty(employeeName)) {
                employeeName = RandomStringUtils.randomAlphabetic(6);
            }

            double basicSalary = payslipInput.getBasic();

            double fuelAllowance = 1000;
            payslipOutput.setFuelAllowance(fuelAllowance);
            int professionalTax = 200;
            payslipOutput.setProfessionalTax(professionalTax);
            String employeeGrade = payslipInput.getGrade();
            payslipOutput.setGrade(employeeGrade);
            payslipOutput.setName(employeeName);
            payslipOutput.setYear(year);
            payslipOutput.setMonth(month);
            if (basicSalary > 0) {


                double hra = calculateHra(basicSalary);
                double providentFund = calculateProvidentFund(basicSalary);
                double gradeAllowance = calculateGradeAllowance(employeeGrade, basicSalary);


                double totalEarnings = calculateTotalEarnings(basicSalary, hra, gradeAllowance, fuelAllowance);


                double incomeTax = calculateIncomeTax(totalEarnings, providentFund, professionalTax);

                double totalDeductions = calculateTotalDeductions(providentFund, professionalTax, incomeTax);
                double netIncome = calculateNetIncome(totalEarnings, totalDeductions);

                payslipOutput.setBasic(Double.valueOf(basicSalary));

                payslipOutput.setHra(hra);
                payslipOutput.setGradeAllowance(gradeAllowance);
                payslipOutput.setProvidentFund(providentFund);
                payslipOutput.setIncomeTax(incomeTax);
                payslipOutput.setTotalEarnings(totalEarnings);
                payslipOutput.setTotalDeductions(totalDeductions);
                payslipOutput.setNetIncome(netIncome);
            }

        } catch (Exception e) {
            // Exception is handled in the upper layer.
            throw e;
        }
        return payslipOutput;
    }

    private double calculateHra(double basicSalary) {
        return round(basicSalary * 0.30, 2);
    }

    private double calculateProvidentFund(double basicSalary) {
        return round(basicSalary * 0.12, 2);
    }

    private double calculateGradeAllowance(String employeeGrade, double basicSalary) {
        if (employeeGrade.equalsIgnoreCase(PayslipConstants.GRADE_VALUE_G1)) {
            return round(basicSalary * 0.50, 2);
        } else if (employeeGrade.equalsIgnoreCase(PayslipConstants.GRADE_VALUE_G2)) {
            return round(basicSalary * 0.70, 2);
        }
        return 0;
    }

    private double calculateTotalEarnings(double basicSalary, double hra, double gradeAllowance, double fuelAllowance) {
        return round(basicSalary + hra + gradeAllowance + fuelAllowance, 2);
    }

    private double calculateIncomeTax(double totalEarnings, double providentFund, int professionalTax) {
        return round((totalEarnings - (providentFund + professionalTax)) * 0.30, 2);
    }

    private double calculateTotalDeductions(double providentFund, int professionalTax, double incomeTax) {
        return round(providentFund + professionalTax + incomeTax, 2);
    }

    private double calculateNetIncome(double totalEarnings, double totalDeductions) {
        return round(totalEarnings - totalDeductions, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
