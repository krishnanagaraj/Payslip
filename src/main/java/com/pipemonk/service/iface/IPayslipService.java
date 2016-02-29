package com.pipemonk.service.iface;

import com.pipemonk.bean.Payslip;

/**
 * This service interface defines the payslip computation
 */
public interface IPayslipService {

    /**
     * Calculates the payslip data
     *
     * @param payslipInput
     * @return
     * @throws Exception
     */
    Payslip.Output computeEmployeePayslip(Payslip.Input payslipInput) throws Exception;
}
