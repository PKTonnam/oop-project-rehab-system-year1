public class CashierEmployee extends Employee{
    @Override
    public JobPosition createJobPositions() {
        return new Cashier();
    }
}
