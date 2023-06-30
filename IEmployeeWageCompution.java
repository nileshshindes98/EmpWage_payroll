public interface IEmployeeWageCompution {
    public void addCompanyEmpWage(String company, int empPerHrWage, int numOFWorkingDays, int totalHourPerMonth);
    public void empWageCompute();
    public int getTotalWage(String company);
}