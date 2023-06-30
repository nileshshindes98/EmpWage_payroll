import java.util.*;

public class EmployeeWageCompution {
    public final String company;
    public final int empPerHrWage;
    public int numOFWorkingDays;
    public int totalHourPerMonth;
    public int totalEmpWage;

    public EmployeeWageCompution(String company, int empPerHrWage, int numOFWorkingDays, int totalHourPerMonth) {
        this.company = company;
        this.empPerHrWage = empPerHrWage;
        this.numOFWorkingDays = numOFWorkingDays;
        this.totalHourPerMonth = totalHourPerMonth;
    }

    public void setTotalEmpWage(int totalEmpWage) {
        this.totalEmpWage = totalEmpWage;
    }

    public String toString() {
        return "Total emp wage For " + company + " is: " + totalEmpWage;
    }

    public static void main(String[] args) {
        EmpWageBuilder empWage = new EmpWageBuilder();
        empWage.addCompanyEmpWage("TCS", 60, 10, 70);
        empWage.addCompanyEmpWage("Capgemini", 80, 20, 100);
        empWage.addCompanyEmpWage("DXC", 50, 8, 55);
        empWage.addCompanyEmpWage("Flipkart", 100, 20, 60);
        empWage.empWageCompute();
        ArrayList<EmpWageBuilder> arrayList = new ArrayList<>(Arrays.asList(empWage));
//        System.out.println("Company list"+arrayList.toString());
        System.out.println();
    }

    //emp wage for companys


    public static class EmpWageBuilder implements IEmployeeWageCompution{

        public static final int FULL_TIME = 1;
        public static final int PART_TIME = 2;

        private int numofCompanys = 0;
        private LinkedList<EmployeeWageCompution> employeeWageComputionsList;
        private Map<String,EmployeeWageCompution> employeeWageComputionMap;

        public EmpWageBuilder() {
            employeeWageComputionsList = new LinkedList<>();
            employeeWageComputionMap = new HashMap<>();
        }

        public void addCompanyEmpWage(String company, int empPerHrWage, int numOFWorkingDays, int totalHourPerMonth) {
            EmployeeWageCompution employeeWageCompution  = new EmployeeWageCompution(company, empPerHrWage, numOFWorkingDays, totalHourPerMonth);
            employeeWageComputionsList.add(employeeWageCompution);
            employeeWageComputionMap.put(company,employeeWageCompution);
        }

        public void empWageCompute() {
            for (int i = 0; i < employeeWageComputionsList.size(); i++) {
                EmployeeWageCompution employeeWageCompution = employeeWageComputionsList.get(i);
                employeeWageCompution.setTotalEmpWage(this.empWageCompute(employeeWageCompution));
                System.out.println(employeeWageCompution);
            }

        }

        public int getTotalWage(String company) {
            return employeeWageComputionMap.get(company).totalEmpWage;
        }


        public int empWageCompute(EmployeeWageCompution employeeWageCompution) {
            int empHour = 0;
            int totalEmpHrs = 0;
            int totalWorkingDays = 0;
            //computation
            while (totalEmpHrs <= employeeWageCompution.totalHourPerMonth && totalWorkingDays < employeeWageCompution.numOFWorkingDays) {
                totalWorkingDays++;
                int empCheck = (int) (Math.floor(Math.random() * 10) % 3);
                switch (empCheck) {
                    case FULL_TIME:
                        empHour = 8;
                        break;
                    case PART_TIME:
                        empHour = 4;
                        break;
                    default:
                        empHour = 0;
                }
                totalEmpHrs += empHour;
                System.out.println("Day#: " + totalWorkingDays + " Emp Hr: " + empHour);
            }
            return totalEmpHrs * employeeWageCompution.empPerHrWage;
        }
    }
}