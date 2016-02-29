Pipemonk Payslip App
=========

This service is tested on OS X, installed with git version 2.6.4, and Apache Maven 3.3.9

Pre requisite for running the service
-------------------------------------

1) A system should have a stable internet connection

2) git should be installed on your system. If it's not installed, refer :

https://help.github.com/articles/set-up-git
You should have a read access to clone the public repository from github.

3) Apache maven should be installed on your system. If it's not installed, refer :

 http://maven.apache.org/download.cgi
 
Setting up the project
----------------------

It's a Pipemonk payslip app. To run the project, follow the instructions

1) First clone the repository in your local system
   git clone https://github.com/krishnanagaraj/Payslip.git
   
2) Run the following command

   sudo mvn clean; sudo mvn package
   
Testing the service
-------------------

We need to use the command line for testing.

Following are sample request and output:

java -jar PipemonkTest-1.0.0.jar 10000 g1

Created Payslip_employeeName_month_year.pdf file. File can be found here: /path/to/pdf/

For any assistance please reach out to me at
--------------------------------------------

krishna.nagaraj@yahoo.com
