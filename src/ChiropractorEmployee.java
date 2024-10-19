public class ChiropractorEmployee extends Employee{

    @Override
    public JobPosition createJobPositions() {
        return new Chiropractor();
    }
}
