import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
// --== CS400 Project Two File Header ==--
// Name: Farris Alkassem
// Email: alkassem@wisc.edu
// Team: Red
// Group: CN
// TA: Evan Wireman
// Lecturer: Florian Heimerl
// Notes to Grader: None


/**
 * Interface for EmployeeLoader
 */
interface EmployeeLoaderInterface {
    public List<EmployeeDataInterface> loadFile(String csv) throws FileNotFoundException;
}


/**
 * Class to load employee files
 *
 * @author Farris Alkassem
 */
public class EmployeeLoader implements EmployeeLoaderInterface {

    /**
     * file loader to read contents of a given csv file and returns a list of all songs.
     *
     * @param csv name of csv file to be used
     * @return List employeeList of all employees in file
     * @throws FileNotFoundException if file doesn't exist
     */
    @Override public List<EmployeeDataInterface> loadFile(String csv) throws FileNotFoundException {
        //LinkedList of employees
        List<EmployeeDataInterface> employeeList = new LinkedList<>();
        //file object to scan through
        File file = new File(csv);
        Scanner scnr = new Scanner(file);
        //creating firstLine
        String currLine = scnr.nextLine();
        String[] firstLine = currLine.split(",");
        //indexes of columns
        int idIndex = 0;
        int nameIndex = 0;
        int emailIndex = 0;
        int phoneIndex = 0;
        int hoursIndex = 0;
        //for loop to iterate through columns
        for (int i = 0; i < firstLine.length; i++) {
            if (firstLine[i].contains("Emp ID")) {
                idIndex = i;
            } else if (firstLine[i].contains("Full Name")) {
                nameIndex = i;
            } else if (firstLine[i].contains("E Mail")) {
                emailIndex = i;
            } else if (firstLine[i].contains("Phone No.")) {
                phoneIndex = i;
            } else if (firstLine[i].contains("Hours/Week")) {
                hoursIndex = i;
            }
        }
        //while scanner has next line
        while (scnr.hasNextLine()) {
            //create a new over-sized array
            String[] employeeArray = new String[150];
            //set currLine to the line after it
            currLine = scnr.nextLine();
            //split line by using commas
            employeeArray = currLine.split(",");
            //set id/name/hours/email/phone
            int id = Integer.valueOf(employeeArray[idIndex]);
            String name = employeeArray[nameIndex];
            int hours = Integer.valueOf(employeeArray[hoursIndex]);
            String email = employeeArray[emailIndex];
            String phone = employeeArray[phoneIndex];
            //create new employee object and add it to employee list
            EmployeeData employee = new EmployeeData(id, name, hours, email, phone);
            employeeList.add(employee);
        }
        return employeeList;
    }
}
