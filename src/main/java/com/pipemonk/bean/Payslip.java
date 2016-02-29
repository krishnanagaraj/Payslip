package com.pipemonk.bean;


/**
 * Bean capturing the payslip information
 */
public class Payslip {

    public abstract static class CommonFields {

        protected String year;
        protected String month;
        protected String name;
        protected String grade;
        protected double basic;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public double getBasic() {
            return basic;
        }

        public void setBasic(double basic) {
            this.basic = basic;
        }


    }

    public static class Input extends CommonFields {

    }

    public static class Output extends CommonFields {

        private double hra;
        private double fuelAllowance;
        private double gradeAllowance;
        private double providentFund;
        private int professionalTax;
        private double incomeTax;
        private double totalEarnings;
        private double totalDeductions;
        private double netIncome;

        public double getHra() {
            return hra;
        }

        public void setHra(double hra) {
            this.hra = hra;
        }

        public double getFuelAllowance() {
            return fuelAllowance;
        }

        public void setFuelAllowance(double fuelAllowance) {
            this.fuelAllowance = fuelAllowance;
        }

        public double getGradeAllowance() {
            return gradeAllowance;
        }

        public void setGradeAllowance(double gradeAllowance) {
            this.gradeAllowance = gradeAllowance;
        }

        public double getProvidentFund() {
            return providentFund;
        }

        public void setProvidentFund(double providentFund) {
            this.providentFund = providentFund;
        }

        public int getProfessionalTax() {
            return professionalTax;
        }

        public void setProfessionalTax(int professionalTax) {
            this.professionalTax = professionalTax;
        }

        public double getIncomeTax() {
            return incomeTax;
        }

        public void setIncomeTax(double incomeTax) {
            this.incomeTax = incomeTax;
        }

        public double getTotalEarnings() {
            return totalEarnings;
        }

        public void setTotalEarnings(double totalEarnings) {
            this.totalEarnings = totalEarnings;
        }

        public double getTotalDeductions() {
            return totalDeductions;
        }

        public void setTotalDeductions(double totalDeductions) {
            this.totalDeductions = totalDeductions;
        }

        public double getNetIncome() {
            return netIncome;
        }

        public void setNetIncome(double netIncome) {
            this.netIncome = netIncome;
        }
    }

}