package com.pipemonk.service.iface;

import com.pipemonk.bean.Payslip;

/**
 * This service interface defines the file creation
 */
public interface IFileService {

    /**
     * Creates a Payslip PDF file.
     *
     * @param payslip
     */
    void createPdfFileForPayslips(Payslip.Output payslip);
}
