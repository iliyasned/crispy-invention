import java.util.List;

public class EmployeeHoursApp {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Employee Hours Tracker");
        List<EmployeeDataInterface> employees = new EmployeeLoader().loadFile("Employees.csv");
        EmployeeHoursBackEndInterface engine = new EmployeeHoursBackEnd();
        for(EmployeeDataInterface employee : employees) engine.addEmployee(employee);
        EmployeeHoursFrontEndInterface ui = new EmployeeHoursFrontEnd();
        ui.run(engine);
    }

}
