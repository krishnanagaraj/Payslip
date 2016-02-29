package com.pipemonk.service.impl;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pipemonk.bean.Payslip;
import com.pipemonk.service.iface.IFileService;
import com.pipemonk.util.PayslipConstants;


import java.io.FileOutputStream;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Handles the PDF file creation and modification
 */
public class FileService implements IFileService {

    /**
     * Creates a payslip PDF file
     *
     * @param payslip
     */
    public void createPdfFileForPayslips(Payslip.Output payslip) {
        Document document = null;
        PdfWriter writer = null;

        String fileName = PayslipConstants.PAYSLIP.concat(PayslipConstants.UNDERSCORE).concat(payslip.getName()).concat(PayslipConstants.UNDERSCORE).concat(payslip.getMonth()).concat(PayslipConstants.UNDERSCORE).concat(payslip.getYear()).concat(PayslipConstants.PDF_FILE_EXTENTION);
        try {
            document = new Document();
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            // Table with four columns
            PdfPTable table = new PdfPTable(4);
            // the cell object
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph(PayslipConstants.PAYSLIP.concat(PayslipConstants.SPACE).concat(PayslipConstants.FOR).concat(PayslipConstants.SPACE).concat(payslip.getMonth()).concat(PayslipConstants.SPACE).concat(payslip.getYear()), normalFont));
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(PayslipConstants.EMPLOYEE.concat(PayslipConstants.COLON).concat(PayslipConstants.SPACE).concat(payslip.getName()).concat(PayslipConstants.SPACE).concat(PayslipConstants.GRADE).concat(PayslipConstants.COLON).concat(payslip.getGrade()), normalFont));
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(PayslipConstants.EARNINGS, boldFont));
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(PayslipConstants.DEDUCTIONS, boldFont));
            cell.setColspan(2);
            table.addCell(cell);


            cell = new PdfPCell(new Paragraph(PayslipConstants.BASIC, normalFont));
            cell.setRowspan(1);
            table.addCell(cell);
            table.addCell(new Paragraph(String.valueOf(payslip.getBasic()), boldFont));
            table.addCell(new Paragraph(PayslipConstants.PROVIDENT_FUND, normalFont));
            table.addCell(new Paragraph(String.valueOf(payslip.getProvidentFund()), boldFont));

            cell = new PdfPCell(new Paragraph(PayslipConstants.HRA, normalFont));
            cell.setRowspan(1);
            table.addCell(cell);
            table.addCell(new Paragraph(String.valueOf(payslip.getHra()), boldFont));
            table.addCell(new Paragraph(PayslipConstants.PROFESSIONAL_TAX, normalFont));
            table.addCell(new Paragraph(String.valueOf(payslip.getProfessionalTax()), normalFont));


            cell = new PdfPCell(new Paragraph(PayslipConstants.FUEL_ALLOWANCE, normalFont));
            cell.setRowspan(1);
            table.addCell(cell);
            table.addCell(new Paragraph(String.valueOf(payslip.getFuelAllowance()), normalFont));
            table.addCell(new Paragraph(PayslipConstants.INCOME_TAX, normalFont));
            table.addCell(new Paragraph(String.valueOf(payslip.getIncomeTax()), boldFont));


            cell = new PdfPCell(new Paragraph(PayslipConstants.GRADE_ALLOWANCE, normalFont));
            cell.setRowspan(1);
            table.addCell(cell);
            table.addCell(new Paragraph(String.valueOf(payslip.getGradeAllowance()), boldFont));
            table.addCell(PayslipConstants.SPACE);
            table.addCell(PayslipConstants.SPACE);

            cell = new PdfPCell(new Paragraph(PayslipConstants.TOTAL_EARNINGS, boldFont));
            cell.setRowspan(1);
            table.addCell(cell);
            table.addCell(new Paragraph(String.valueOf(payslip.getTotalEarnings()), boldFont));
            table.addCell(new Paragraph(PayslipConstants.TOTAL_DEDUCTIONS, boldFont));
            table.addCell(new Paragraph(String.valueOf(payslip.getTotalDeductions()), boldFont));

            cell = new PdfPCell(new Paragraph(PayslipConstants.NET_INCOME, boldFont));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(String.valueOf(payslip.getNetIncome()), boldFont));
            cell.setColspan(3);
            table.addCell(cell);

            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            writer.close();
        }
        String path = System.getProperty("user.dir");
        System.out.println(String.format("Created %s file. File can be found here: %s", fileName, path));
    }
}
