// --== CS400 Project Two File Header ==--
// Name: Farris Alkassem
// Email: alkassem@wisc.edu
// Team: Red
// Group: CN
// TA: Evan Wireman
// Lecturer: Florian Heimerl
// Notes to Grader: None

// interface

interface EmployeeDataInterface extends Comparable<EmployeeDataInterface>{
    public int getID();

    public String getEmail();

    public String getNumber();

    public int getHours();

    public String getName();

    public void addHours(int hours);

    @Override
    public int compareTo(EmployeeDataInterface employee);

    public void resetHours();
}

/**
 * Employee Data class which contains hours, email, phone, ID, name
 * @author Farris Alkassem
 */
public class EmployeeData implements EmployeeDataInterface {
    //hours/week for employee
    private int hours;
    //email of employee
    private String email;
    //phone number of employee
    private String phone;
    //ID of employee
    private int ID;
    //name of employee
    private String name;

    @Override
    public int compareTo(EmployeeDataInterface employee){
        if(this.getID() > employee.getID()){
            return 1;
        }
        else if(this.getID() == employee.getID()){
            return 0;
        }
        else{
            return -1;
        }
    }

    public EmployeeData(int ID, String name, int hours, String email, String phone) {
        this.name = name;
        this.hours = hours;
        this.email = email;
        this.phone = phone;
        this.ID = ID;
    }

    /**
     * getter for ID
     * @return id of employee
     */
    @Override public int getID() {
        return this.ID;
    }

    /**
     * getter for Email
     * @return email of employee
     */
    @Override public String getEmail() {
        return this.email;
    }

    /**
     * getter for phone number
     * @return phone number of employee
     */
    @Override public String getNumber() {
        return this.phone;
    }

    /**
     * getter for hours
     * @return hours/week of employee
     */
    @Override public int getHours() {
        return this.hours;
    }

    /**
     * getter for name
     * @return name of employee
     */
    @Override public String getName() {
        return this.name;
    }

    /**
     * adds a specified amount of hours
     * @param hours number of hours to be added
     */
    @Override public void addHours(int hours) {
        this.hours += hours;
    }

    /**
     * resets hours to zero
     */
    @Override public void resetHours() {
        this.hours = 0;
    }
}
