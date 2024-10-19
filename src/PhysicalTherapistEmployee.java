public class PhysicalTherapistEmployee extends Employee{
    @Override
    public JobPosition createJobPositions() {
        return new PhysicalTherapist();
    }
}
