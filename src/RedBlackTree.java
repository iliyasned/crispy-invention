// --== CS400 Project Two File Header ==--
// Name: William Salton
// Email: salton@wisc.edu
// Team: Red
// Group: CN
// TA: Evan Wireman
// Lecturer: Florian Heimerl
// Notes to Grader: None

public class RedBlackTree<T> extends RBTree<EmployeeDataInterface> {
  public RedBlackTree() {
    super();
  }

  /**
   * Search method that searches the RBTree for a give ID value
   * 
   * @param id
   * @return
   */
  public EmployeeDataInterface getID(int id) {
    return getIDHelper(id, root);
  }

  /**
   * Helper method to find a node
   * 
   * @param id
   * @param current
   * @return
   */
  public EmployeeDataInterface getIDHelper(int id, Node<EmployeeDataInterface> current) {
    // makes sure the current node is not null
    if (current != null) {
      if (id > current.data.getID()) {
        if (current.rightChild != null) {
          return getIDHelper(id, current.rightChild);
        } else {
          // if employee in the RBT doesn't have the ID provided return null because employee
          // doesn't exist
          return null;
        }
      } else if (id < current.data.getID()) {
        if (current.leftChild != null) {
          return getIDHelper(id, current.leftChild);
        } else {
          // if employee in the RBT doesn't have the ID provided return null because employee
          // doesn't exist
          return null;
        }
      } else {
        // when the employee with the ID is found
        return current.data;
      }
    }
    return null; // if the root is null
  }

  /**
   * Method that gets the largest ID number from the red black tree
   * @return
   */
  public int getBiggest() {
    Node<EmployeeDataInterface>node = root;
    while(node.rightChild != null) {
      node = node.rightChild;
    }
    return node.data.getID();
  }
  
  


  public EmployeeDataInterface addHours(int id, int hours) {
    return addHoursHelper(id, root, hours);
  }


  /**
   * Add method that searched red black tree for given
   * 
   * @param id
   */
  public EmployeeDataInterface addHoursHelper(int id, Node<EmployeeDataInterface> current,
      int hours) {
    boolean nodeFound = false;
    // makes sure the current node is not null
    if (current != null) {
      if (id > current.data.getID()) {
        if (current.rightChild != null) {
          return addHoursHelper(id, current.rightChild, hours);
        }
      } else if (id < current.data.getID()) {
        if (current.leftChild != null) {
          return addHoursHelper(id, current.leftChild, hours);
        }
      } else if (id == current.data.getID()) {
        // when the employee with the ID is found
        current.data.addHours(hours);
        nodeFound = true;
      }
    }
    if (nodeFound == false) {
      throw new NullPointerException("Node does not exist");
    }
    return null;
  }

}



