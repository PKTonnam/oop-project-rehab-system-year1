public class Cashier implements JobPosition{
    private int hour;
    private double rate;
    private String jobPosition;
    public Cashier(){
        hour = 8;
        rate = 56.25;
        jobPosition = "Cashier (แคชเชียร์)";
    }

    public String getJobPosition(){
        return jobPosition;
    }
    @Override
    public double getWages() {
        return hour * rate;
    }
}
