import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.console.ConsoleLauncher;


public class EmployeeHoursTests {
    // Data Wrangler Code Tests

    /**
     * Tests the loadFile method in EmployeeLoader
     */
    @Test public void DataWrangler_TestLoadFile() {
        //try-catch block to find possible errors
        try {
            //loading Employees.csv
            EmployeeLoader loader = new EmployeeLoader();
            List<EmployeeDataInterface> list = loader.loadFile("Employees.csv");
            //testing size of list
            assertEquals(100, list.size());
            //testing first employee's name
            assertEquals("Lois Walker", list.get(0).getName());
            //testing second employee's email
            assertEquals("brenda.robinson@gmail.com", list.get(1).getEmail());
            //testing 55th employee's number
            assertEquals("423-772-0118", list.get(54).getNumber());
            //testing 100th employee's ID
            assertEquals(19, list.get(99).getID());
        } catch (Exception e) {
        }
    }

    /**
     * Tests the getters for EmployeeData class
     */
    @Test public void DataWrangler_TestEmployeeDataGetters() {
        try {
            //creating a new instance of employee
            EmployeeData employee =
                new EmployeeData(34, "Farris Alkassem", 37, "alkassem@wisc.edu", "608-376-3374");
            //testing ID
            assertEquals(34, employee.getID());
            //testing name
            assertEquals("Farris Alkassem", employee.getName());
            //testing hours
            assertEquals(37, employee.getHours());
            //testing email
            assertEquals("alkassem@wisc.edu", employee.getEmail());
            //testing number
            assertEquals("608-376-3374", employee.getNumber());
        } catch (Exception e) {

        }
    }
    /**
     * Tests the getters for EmployeeData class
     */
    @Test public void DataWrangler_TestHoursMutators() {
        //creating a new instance of employee
        EmployeeData employee =
            new EmployeeData(34, "Farris Alkassem", 37, "alkassem@wisc.edu", "608-376-3374");
        //adding hours
        employee.addHours(3);
        //testing new amount of hours
        assertEquals(40, employee.getHours());
        //resetting hours
        employee.resetHours();
        //testing 0 hours
        assertEquals(0, employee.getHours());
    }
    // Back End Developer Tests

    // Front End Developer Tests

    // Integration Manager Tests
    /**
     * Tests the employee data getters and addHours and resetHours
     */
    @Test public void IntegrationManager_TestEmployeeDataGetters() {
        //creating a new instance of employee
        EmployeeData employee =
            new EmployeeData(1, "Ili", 10, "ili@gmail.com", "608-000-3504");
        //get name
        assertEquals("Ili", employee.getName());
        //get email
        assertEquals("ili@gmail.com", employee.getEmail());
        //get number
        assertEquals("608-000-3504", employee.getNumber());
        //adding hours
        employee.addHours(10);
        //testing new amount of hours
        assertEquals(20, employee.getHours());
        //resetting hours
        employee.resetHours();
        //testing 0 hours
        assertEquals(0, employee.getHours());
    }
    /**
     * Tests the employee hours front end ui by using TextUITester
     */
    @Test public void IntegrationManager_TestFrontEnd(){
        try {
            EmployeeHoursBackEnd engine = new EmployeeHoursBackEnd();
            EmployeeHoursFrontEnd ui = new EmployeeHoursFrontEnd();
            TextUITester tester = new TextUITester("h\nili\n");
            ui.run(engine);
            String output = tester.checkOutput();
            //check whether it prints out enter name and hours
            assertEquals(true, output.contains("Enter name:"));
            assertEquals(true, output.contains("Enter hours:"));
            tester = new TextUITester("d\n53\n");
            ui.run(engine);
            output = tester.checkOutput();
            assertEquals(true, output.contains("Employee hours: 26"));

        } catch (FileNotFoundException e) {
        }
        
    }
    /**
     * Tests the implemented methods of EmployeeHoursBackEnd
     */
    @Test public void IntegrationManager_TestBackEnd(){
        try {
            EmployeeHoursBackEnd tree = new EmployeeHoursBackEnd();
            tree.addEmployee(new EmployeeData(101, "iliyas", 0, "ili", "6080000000"));
            //adding 2 hours and checking to make sure it did add it
            tree.addHours(101, 2);
            assertEquals(2, tree.getEmployee(101).getHours());
            //testing biggest id
            assertEquals(101, tree.getBiggestID());
            //resetting all hours then checking
            tree.resetAllHours();
            assertEquals(0, tree.getEmployee(1).getHours());
            assertEquals(0, tree.getEmployee(54).getHours());
        } catch (FileNotFoundException e) {
        }
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {"-cp", classPath, "--include-classname=.*",
            "--select-class=" + className};
        ConsoleLauncher.main(arguments);
    }
}
