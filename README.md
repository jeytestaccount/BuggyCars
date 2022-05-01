#Buggy Cars Project
Buggy cars Rating is a website that contains details of different cars. we can vote and add comments to rate the car. We can also see the different make and their models grouped together.
The major few functionalities of the website is automation using selenium cucumber. More details are as follows.  

# Test Automation Framework
This automation project is designed with Selenium Cucumber Java framework and libraries. Behavior Driven Development (BDD) approach is used to provide more readability and reusability.
Selenium Web driver is used for browser automation. TestNG is used to encapsulate Selenium code into discrete tests, provide features such as control execution flow, test configuration, data providers and parallel execution. In case of error, it will capture the screenshot.
This framework supports parallel execution and all passwords are encrypted using good algorithm. It will generate a detailed report using Maven Sure fire plugin. It will also generate cucumber default html and json report.

#Author
Jeyakkannan Alagumuthu 

#Tools used
 - Java: Programming language
 - Selenium: Test Automation Tool
 - Cucumber-JVM: Java implementation of Cucumber
 - TestNG: Testing framework
 - Maven: Build automation tool
 - Dependency Injection (DI) with PicoContainer
 - slf4j for logger management

#Pre-requisites to Run the Test
 - Java
 - Maven
 - Browsers - Chrome, Edge, Firefox (At least one of these)
 - IDE - Intellij/Eclipse
 

#How to Run the Test

_Maven command to run the test in Command Line:_
  
  mvn test -Dcucumber.filter.tags="@smoke" -Dbrowser=edge
  
#Tags

 The following tags are created to run the test. 
 
     - @smoke 
     - @regression  
     
     @smoke - smoke tag will run only the positive test
     @regression - regression tag will run all test including positive and negative
     
#Browser Support

 This test supports the following browser.
 
     - Chrome
     - Edge
     - Firefox
     
 Buggy cars website has some critical issues in firefox browser. Try with Chrome and edge initially. 

#Execution

 This test provides parallel execution features. Test execution will happen in parallel mode with 3 threads by default. we can modify it to different thread counts in testng.xml file.
 
#Driver Support 
 The drivers for Chrome, Firefox and Edge are provided within the code. All 3 drivers supports the latest version of browsers. 
 If the browser version is differ from the driver version, then please download the required version of driver and add it
 to the test->resources->drivers folders. 
 
#Project Structure:
The framework follows a Java-Cucumber Maven default folder structure. All the page object class are placed under page objects folder and step definitions
are placed in the stepdefinition folder. All test data are available in test data folder and path definitions are provided in datamanager.
All configuration details are placed config file under config folder.

#Reports
The reports for the test are generated using Maven sure fire plugin. Once the test execution is completed, you can find the 
reports in the folder - C:\Work\Test\BuggyCars\target\surefire-reports. index.xml provides a neat report of the test execution. 

#Thank you for reading. 