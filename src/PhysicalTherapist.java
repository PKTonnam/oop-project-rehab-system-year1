public class PhysicalTherapist implements JobPosition{
    private int hour;
    private double rate;
    private String jobPosition;
    public PhysicalTherapist(){
        hour = 8;
        rate = 93.75;
        jobPosition = "Physical (นักกายภาพ)";
    }
    @Override
    public String getJobPosition() {
        return jobPosition;
    }

    @Override
    public double getWages() {
        return hour * rate;
    }
}
