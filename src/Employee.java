public abstract class Employee {
    private final JobPosition jobPosition;
    private String name;
    private int ot;
    private int rate;

    public Employee(){
        ot = 0;
        rate = 0;
        jobPosition = createJobPositions();
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setOT(int ot, int rate){
        this.ot = ot;
        this.rate = rate;
    }
    public double getOT(){
        return ot * rate;
    }
    private double getDailyWage(){
        if (ot != 0 && rate != 0){
            return jobPosition.getWages() + getOT();
        }else {
            return jobPosition.getWages();
        }

    }
    public String totalIncome(){
        return getName() + " " + jobPosition.getJobPosition() + " " + getDailyWage() + " Baht.";
    }
    public abstract JobPosition createJobPositions();

}
