package com.pipemonk.controller;

import com.pipemonk.bean.Payslip;
import com.pipemonk.service.iface.IPayslipService;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for Controllers
 */
public class TestPayslipController {
    PayslipController payslipController = new PayslipController();

    @Mocked
    IPayslipService payslipService;


    @Test
    public void getPayslip() throws Exception {

        Payslip.Input payslipInput = new Payslip.Input();

        payslipInput.setBasic(10000);
        payslipInput.setGrade("G1");

        String basicSalary = "10000";
        String grade = "G1";

        payslipController.setPayslipService(payslipService);
        new Expectations() {
            {
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
                payslipService.computeEmployeePayslip((Payslip.Input)any);

                returns(payslipOutput);
            }
        };

        payslipService.computeEmployeePayslip(payslipInput);

        Payslip.Output payslipOutput = payslipController.computePayslip(basicSalary, grade);

        Assert.assertEquals(12320.0, payslipOutput.getNetIncome(), 0);

    }

    @Test
    public void getEmptyPayslip() throws Exception {

        Payslip.Input payslipInput = new Payslip.Input();

        payslipInput.setBasic(0);
        payslipInput.setGrade("G1");

        String basicSalary = "0";
        String grade = "G1";

        payslipController.setPayslipService(payslipService);
        new Expectations() {
            {
                Payslip.Output payslipOutput = new Payslip.Output();
                payslipOutput.setBasic(0);
                payslipOutput.setGrade("G1");
                payslipOutput.setMonth("February");
                payslipOutput.setYear("2016");
                payslipOutput.setName("Krishna");
                payslipOutput.setHra(0);
                payslipOutput.setGradeAllowance(0);
                payslipOutput.setTotalEarnings(0);
                payslipOutput.setTotalDeductions(0);
                payslipOutput.setNetIncome(0);
                payslipOutput.setProvidentFund(0);
                payslipOutput.setIncomeTax(0);
                payslipOutput.setFuelAllowance(1000.0);
                payslipOutput.setProfessionalTax(200);

                payslipService.computeEmployeePayslip((Payslip.Input)any);
                returns(payslipOutput);
            }
        };

        payslipService.computeEmployeePayslip(payslipInput);


        Payslip.Output payslipOutput = payslipController.computePayslip(basicSalary, grade);

        Assert.assertEquals(0, payslipOutput.getNetIncome(), 0);
    }


/*    @Test
    public void createPayslipValidations() throws Exception {
        Assert.assertEquals("Mandatory 'grade' parameter missing.", payslipController.computePayslip("10000", null));
    }*/

}
