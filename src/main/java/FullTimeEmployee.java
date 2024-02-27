public class FullTimeEmployee extends Employee{
    public FullTimeEmployee(int id, String name, String typeOfEmployee, Double hour, Address address) {
        super(id, name, typeOfEmployee, hour, address);
    }

    @Override
    public Double calculateSalary() {
      return 100 * hour;
    }
}
