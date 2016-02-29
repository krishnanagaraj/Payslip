package com.pipemonk.service;

import com.pipemonk.bean.Payslip;
import com.pipemonk.controller.PayslipController;
import com.pipemonk.service.impl.PayslipService;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * Test cases for Payslip service
 */
public class TestPayslipService {
    PayslipController payslipController = new PayslipController();


    PayslipService payslipService = new PayslipService();

    @Test
    public void getPayslip() throws Exception {

        Payslip.Input payslipInput = new Payslip.Input();

        payslipInput.setBasic(10000);
        payslipInput.setGrade("G1");
        payslipInput.setMonth("February");
        payslipInput.setYear("2016");
        payslipInput.setName("Krishna");

        Payslip.Output payslipOutput = new Payslip.Output();
        payslipOutput.setBasic(10000);
        payslipOutput.setGrade("G1");
        payslipOutput.setMonth("February");
        payslipOutput.setYear("2016");
        payslipOutput.setName("Krishna");
        payslipOutput.setHra(3000.0);
        payslipOutput.setGradeAllowance(5000.0);
        payslipOutput.setTotalEarnings(19000.0);
        payslipOutput.setTotalDeductions(6680.0);
        payslipOutput.setNetIncome(12320.0);
        payslipOutput.setProvidentFund(1200.0);
        payslipOutput.setIncomeTax(5280.0);
        payslipOutput.setFuelAllowance(1000.0);
        payslipOutput.setProfessionalTax(200);


        Payslip.Output result = payslipService.computeEmployeePayslip(payslipInput);
        ReflectionAssert.assertReflectionEquals(payslipOutput, result);
    }



}
