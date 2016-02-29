package com.pipemonk.service.iface;

import com.pipemonk.bean.Payslip;

/**
 * Created by Krishna on 2/29/16.
 */

public interface IFileService {

    void createPdfFileForPayslips(Payslip.Output payslip);
}
