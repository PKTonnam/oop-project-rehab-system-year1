import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCustomers {
    File file = new File("/Users/pk/Desktop/Year 1/My Project OOP Java/ProjectFreshmanOOP/src/CustomerInformation.txt");

    //คืนค่าข้อมูลในไฟล์ลูกค่าออกมาเป็น ArrayList
    protected ArrayList<String> inputFiles() {
        try {
            ArrayList<String> dataFile = new ArrayList<>();
            Scanner data = new Scanner(file);
            while (data.hasNext()){
                String key = data.nextLine();
                dataFile.add(key);
            }
            return dataFile;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //แสดงข้อมูลการรักษาทั้งหมดของลูกค่าคนนั้น
    protected void getFiles(String id){
        try {
            boolean checkData = false;
            for (int i = 0; i < inputFiles().size(); i++) {
                String data = inputFiles().get(i);
                if (data.contains(id)){
                    System.out.println(data);
                    checkData = true;
                }
            }
            if (!checkData){
                System.out.println("|           No patient information.          |");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
