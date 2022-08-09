import java.util.Scanner;

//--== CS400 Project Two File Header ==--
//Name: Tyler Auth
//Email: ttauth@wisc.edu
//Team: Red
//Group: CN
//TA: Evan Wireman
//Lecturer: Florian Heimerl
//Notes to Grader: None
// interface (implemented with proposal)
interface EmployeeHoursFrontEndInterface {
  public void run(EmployeeHoursBackEndInterface searchEngine);

  // Here is a sample of the minimal set of options that
  // this front end will support:
  // Employee Hours App Command Menu:
  // Welcome prompt
  final String WELCOME_PROMPT =
      "Hello, welcome to the Employee Hours App!" + "\n" + " Let's get started.";
  // Command prompt for the user
  final String COMMAND_PROMPT =
      "To hire a new employee into the employee database press \"H\" \n To reset hours to all"
          + " employees, press \"R\" \n To add hours to an Employee, press "
          + "\"A\" \n To display the hours of an employee, press"
          + " \"D\" \n To display an employee's data, press \"E\""
          + " \n At anytime if you want to quit the program, press \"Q\" ";
  //Conclusion prompt
  final String END_PROMPT = "Thank you for using Employee Hours App! \n This concludes the program.";
  // 1. Add Employee
  public void addEmployee(EmployeeHoursBackEndInterface employeeBackEnd);

  // 2. add Hours to an employee
  public void addHours(EmployeeHoursBackEndInterface employeeBackEnd);

  // 3. Reset Employee Hours (new week)
  public void resetAllHours(EmployeeHoursBackEndInterface employeeBackEnd);

  // 4. Print Hours worked in a week
  public void printHours(EmployeeHoursBackEndInterface employeeBackEnd);
  
  // 5. Display employee data
  public void displayEmployeeData(EmployeeHoursBackEndInterface employeeBackEnd);
  // 6. Quit
  public void quit();
}


// public class (implemented primarilly in final app week)
public class EmployeeHoursFrontEnd implements EmployeeHoursFrontEndInterface {

  private Scanner scnr; //Scanner that will be used to read the inputs
  
  /*
   * Constructor class for EmployeeHoursFrontEnd.
   */
  public EmployeeHoursFrontEnd() {

    scnr = new Scanner(System.in);
    
  }


  @Override
  public void run(EmployeeHoursBackEndInterface searchEngine) {
    
    
    System.out.println(WELCOME_PROMPT);
   
  
    //Press space to start
    
      System.out.println(COMMAND_PROMPT);
      String userInput = scnr.next();
    
        //Add new employee, press h
        if (userInput.equalsIgnoreCase("h")) {
          addEmployee(searchEngine);
        }
        //To reset hours, press r
        else if (userInput.equalsIgnoreCase("r")) {
          resetAllHours(searchEngine);
        }
        //To add hours to an employee, press a
        else if (userInput.equalsIgnoreCase("a")) {
          addHours(searchEngine);
        }
        //To display hours, press d
        else if (userInput.equalsIgnoreCase("d")) {
          printHours(searchEngine);
        }else if (userInput.equalsIgnoreCase("e")) {
          displayEmployeeData(searchEngine);
        }else if (userInput.equalsIgnoreCase("q")) {
          quit();
        }
        else {
          System.out.println("Error, please try again and enter a valid input.");
        }
        
      
      //Closes scanner and concludes program
      
    
    
    }


  

  /*
   * This method will ask the user to add an employee
   */
  @Override
  public void addEmployee(EmployeeHoursBackEndInterface employeeBackEnd) {
      int ID = employeeBackEnd.getBiggestID() + 1;
      System.out.println("Enter name: ");
      String name = scnr.next();
      System.out.println("Enter hours: ");
      int hours = scnr.nextInt();
      System.out.println("Enter email address: ");
      String email = scnr.next();
      System.out.println("Enter phone: ");
      String phone = scnr.next();
      
      employeeBackEnd.addEmployee(new EmployeeData(ID, name, hours, email, phone));
      run(employeeBackEnd);
    
   
  }

  @Override
  public void addHours(EmployeeHoursBackEndInterface hours) {
    System.out.println("Enter the employee ID(0-100) to add hours.");
    int timeCheck=0; //Variable created to check if the amount of hours that employee has is correct
    if (scnr.hasNext()) {
      int userInput = scnr.nextInt();
      System.out.println("Employee ID: " + hours.getEmployee(userInput));
      if (hours.getEmployee(userInput).getHours() != 0) {
      timeCheck = hours.getEmployee(userInput).getHours(); //Current amount of hours that employee has
      } else {
        timeCheck = 0; //To prevent null time Check
      }
      System.out.println("Please, specify the amount of hours that you would like to add.");
      
      int time = scnr.nextInt();
      hours.addHours(userInput, time);
      if (hours.getEmployee(userInput).getHours() == time + timeCheck) {
      System.out.println("Hours added to employee successfully");
      run(hours);
      }
    }

  }
  /*
   * This method is used to reset all employee hours when specified to do so my user
   */
  @Override
  public void resetAllHours(EmployeeHoursBackEndInterface hours) {
    System.out.println("To confirm that you would like to reset employee hours, press \"Y\"");
    System.out.println("To cancel, press \"N\"");
    if (scnr.hasNext()) {
      if (scnr.next().equalsIgnoreCase("y")) {
        hours.resetAllHours();
       
        run(hours);
      }
    }

  }
  /*
   * This method returns the hours based on Employee ID
   */
  @Override
  public void printHours(EmployeeHoursBackEndInterface hours) {
    System.out.println("Enter the employee ID(0-100) to search for the individual's hours.");
    if (scnr.hasNext()) {
      int userInput = scnr.nextInt();
      System.out.println("Employee ID: " + userInput +"\n" +"Employee Hours: " + 
      hours.getEmployee(userInput).getHours());
      run(hours);
    } 

  }

  /*
   * 
   */
  @Override
  public void quit() {
    // TODO Auto-generated method stub
    scnr.close();
    System.out.println(END_PROMPT);
  }



  @Override
  public void displayEmployeeData(EmployeeHoursBackEndInterface employeeBackEnd) {
    // TODO Auto-generated method stub
    System.out.println("Please enter the ID of the employee in order to acquire their data");
    if (scnr.hasNext()) {
      int userInput = scnr.nextInt();
      System.out.println("Employee ID: " + userInput);
      System.out.println("Employee Name: " + employeeBackEnd.getEmployee(userInput).getName());
      System.out.println("Employee email: " + employeeBackEnd.getEmployee(userInput).getEmail());
      System.out.println("Employee Phone number: " + employeeBackEnd.getEmployee(userInput).getNumber());
      System.out.println("Employee Hours: " + employeeBackEnd.getEmployee(userInput).getHours());
     
     //Return to menu
     
     run(employeeBackEnd);
      
      
    }
  }
}

