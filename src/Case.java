
import java.util.ArrayList;

public class Case {
    public ArrayList<String> treatmentOptions;
    private double totalIncome;

    public Case(){
        treatmentOptions = new ArrayList<>();
        treatmentOptions.add("Shockwave");
        treatmentOptions.add("Lasers");
        treatmentOptions.add("Ultrasound");
        treatmentOptions.add("Electrical");
        treatmentOptions.add("Massage");
        treatmentOptions.add("Pilates");
        totalIncome = 0.0;
    }

    //เพิ่มเงินเข้าของคลินิก
    public void updateIncome(double cash){
        totalIncome += cash;
    }

    //คืนค่าเงินของคลินิก
    public double getTotalIncome(){
        return totalIncome;
    }

    //เช็คว่าแบบรักษาที่ป้อนมาถูกมั้ย
    public boolean checkTrue(String treat){
        boolean correctTreat = false;
        for (int i = 0; i < treatmentOptions.size(); i++) {
            if (treatmentOptions.get(i).contains(treat)){
                correctTreat = true;
            }
        }
        return correctTreat;
    }

    //เช็คและคืนค่าชื่อของแบบรักษาที่ถูกต้อง
    public String checkTreatment(String treat){
        String correctTreat = "";
        for (int i = 0; i < treatmentOptions.size(); i++) {
            if (treatmentOptions.get(i).contains(treat) || treatmentOptions.get(i).toLowerCase().contains(treat)){
                correctTreat = treatmentOptions.get(i);
            }
        }
        return correctTreat;
    }

    //เพิ่มค่ารักษาตามแบบที่จะรักษา
    public double setTreatmentCost(String treatment){
        double cost = 0;
        if (treatment.equals("Shockwave")){
            cost += 1000;
        }else if (treatment.equals("Lasers")){
            cost += 500;
        }else if (treatment.equals("Ultrasound")){
            cost += 300;
        }else if (treatment.equals("Electrical")){
            cost += 250;
        }else if (treatment.equals("Massage")){
            cost += 1500;
        }else if (treatment.equals("Pilates")){
            cost += 1200;
        }
        return cost;
    }

    //แสดงรายชื่อแบบการรักษาที่คลินิกนี้มี
    public void listTreatmentOptions(){
        System.out.println("| List treatment |");
        for (int i = 0; i < treatmentOptions.size(); i++) {
            System.out.print("|" + treatmentOptions.get(i) + " ");
            if (treatmentOptions.get(i).equals("Shockwave")){
                System.out.println("1000 Baht|");
            }else if (treatmentOptions.get(i).equals("Lasers")){
                System.out.println("500 Baht|");
            }else if (treatmentOptions.get(i).equals("Ultrasound")){
                System.out.println("300 Baht|");
            }else if (treatmentOptions.get(i).equals("Electrical")){
                System.out.println("250 Baht|");
            }else if (treatmentOptions.get(i).equals("Massage")){
                System.out.println("1500 Baht|");
            }else if (treatmentOptions.get(i).equals("Pilates")){
                System.out.println("1200 Baht|");
            }
        }
    }
}
