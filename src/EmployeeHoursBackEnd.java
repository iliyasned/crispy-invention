import java.io.FileNotFoundException;
import java.util.List;


// --== CS400 Project Two File Header ==--
// Name: William Salton
// Email: salton@wisc.edu
// Team: Red
// Group: CN
// TA: Evan Wireman
// Lecturer: Florian Heimerl
// Notes to Grader: None

interface EmployeeHoursBackEndInterface {

  // Adds a new employee (e.g. new hire) and returns their new ID number (calculated in this method)
  public void addEmployee(EmployeeDataInterface employee);

  // Returns all info for an employee given their ID for use by the front end
  public EmployeeDataInterface getEmployee(int id);

  // Add hours worked for the employee with the given ID
  public void addHours(int id, int hours);

  // Use to reset the value of every employee's hours for the week to 0 (i.e. use at end of week)
  public void resetAllHours();

  public int getBiggestID();
}


class EmployeeHoursBackEnd implements EmployeeHoursBackEndInterface {
  // Creates a redBlackTree containing all employee's and a list of all employee's
  private RedBlackTree<EmployeeDataInterface> tree;
  private List<EmployeeDataInterface> objectList;


  public EmployeeHoursBackEnd() throws FileNotFoundException {
    tree = new RedBlackTree<EmployeeDataInterface>();
    objectList = new EmployeeLoader().loadFile("Employees.csv");
  }

  /**
   * Allows front end to add employee into the red black tree
   */
  @Override
  public void addEmployee(EmployeeDataInterface employee) {
    tree.insert(employee);
    objectList.add(employee);
  }

  /**
   * Method that searches the RBTree for employee with ID value in the parameter
   */
  @Override
  public EmployeeDataInterface getEmployee(int id) {
    return tree.getID(id);
  }

  /**
   * Allows front end to add hours into an Employee ID with the given node
   */
  @Override
  public void addHours(int id, int hours) {
    try {
      tree.addHours(id, hours);
    } catch (NullPointerException e) {
    }
  }

  @Override
  public void resetAllHours() {
    for (int i = 0; i < objectList.size(); i++) {
      objectList.get(i).resetHours();
    }
  }

  /**
   * Calls getBiggest and returns the largest method
   * 
   * @return
   */
  public int getBiggestID() {
    return tree.getBiggest();
  }


}


