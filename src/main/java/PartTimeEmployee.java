public class PartTimeEmployee extends Employee {

    public PartTimeEmployee(int id, String name) {
        super(id, name);

    }

    public PartTimeEmployee(int id, String name, String typeOfEmployee, Double hour, Address address) {
        super(id, name, typeOfEmployee, hour, address);
    }

    @Override
    public Double calculateSalary() {
       return 100 * hour;
    }
}
