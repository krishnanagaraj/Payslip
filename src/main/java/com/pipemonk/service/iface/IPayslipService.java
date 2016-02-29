package com.pipemonk.service.iface;

import com.pipemonk.bean.Payslip;

/**
 * Created by Krishna on 2/29/16.
 */
public interface IPayslipService {

    Payslip.Output computeEmployeePayslip(Payslip.Input payslipInput) throws Exception;
}
