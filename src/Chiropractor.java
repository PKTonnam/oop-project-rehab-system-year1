public class Chiropractor implements JobPosition{
    private int hour;
    private double rate;
    private String jobPosition;
    public Chiropractor(){
        hour = 8;
        rate = 87.5;
        jobPosition = "Chiropractor (หมอนวด)";
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
