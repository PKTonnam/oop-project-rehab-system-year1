import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RehabSystemMain {
    public static void loginSystemAppearance(){//Method หน้า Login
        System.out.println("==========================================");
        System.out.println("================= System =================");
        System.out.println("|     l = Login     |     r = Register   |");
        System.out.println("|========================================|");
        System.out.println("|=============== q = Quit ===============|");
        System.out.println("==========================================");
        System.out.println("||=========Select your options==========||");
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        FileWriter systemLogin;//สร้างตัวแปรไฟล์เพ่ือเอาข้อมูล User ไปเก็บไว้ที่ systemLogin.txt
        ArrayList<String> dataLogin = new ArrayList<>();//สร้าง ArrayList มาเก็บข้อมูลของ systemLogin.txt
        String userName = null;

        //เช็ค Error ของไฟล์ systemLogin.txt
        try {
            systemLogin = new FileWriter("/Users/pk/Desktop/Year 1/My Project OOP Java/ProjectFreshmanOOP/src/systemLogin.txt", true);
        } catch (IOException e) {
            System.out.println("File missing!!");
            throw new RuntimeException();
        }

        boolean checkOption = false;//สร้างตัวแปรมาเช็คตัวเลือกว่าผู้ใช้ใส่มาถูกมั้ย
        boolean loginSuccess = false;//สร้างมาเพื่อเช็คว่า login เสร็จแล้ว

        //ลูปรับค่าตัวเลือก
        do {
            loginSystemAppearance();
            System.out.println("------------------------------------------");
            System.out.print("| Enter your option = ");
            char optionUser = kb.next().charAt(0);//สร้างตัวแปรมารับค่าตัวเลือกผู้ใช้

            //เลือก Register
            if (optionUser == 'r' || optionUser == 'R'){

                //Interface system
                System.out.println("------------------------------------------");
                System.out.println("================ Register ================");
                System.out.print("| Create your username : ");
                String user = kb.next();
                userName = user;
                boolean confirm; //สร้างตัวแปรมาเช็คว่ารหัสตรงกับที่ยืนยันมั้ย ถ้าตรงก็จบลูป
                String password;

                //ลูปสร้าง Password
                do {
                    System.out.println("------------------------------------------");
                    System.out.print("| Create your passwords : ");
                    password = kb.next();

                    //ลูป Confirm password
                    int checkPass = 0;//สร้างมาเพื่อเช็ครอบจำนวนที่ Confirm password ผิด
                    do {
                        System.out.print("|| Confirm your passwords : ");
                        String confirmPassword = kb.next();
                        confirm = password.equals(confirmPassword);//เช็คว่า Confirm password ตรงกับ password มั้ย
                        if (!confirm){//ถ้า Password ไม่ตรง
                            System.out.println("------------------------------------------");
                            System.out.println("Confirm password not match.");
                            System.out.println("------------------------------------------");
                            checkPass++;
                        }
                    }while (checkPass != 2 && !confirm);//ถ้าไม่ตรงวนรับใหม่อีกรอบ //วนรับได้แค่ 2 รอบหลังจากนั้นต้องไปสร้าง Password ใหม่

                    //ถ้า Password ยังไม่ตรงกันอีกให้สร้างใหม่ล่ะ Confirm อยู่ในนี้จนกว่า Password จะตรงกัน ถ้าเข้าเงื่อนไข if นี้จะให้วนสร้างและ Confirm Password ไปเรื่อยๆจนกว่า Password จะตรงกัน
                    if (!confirm){
                        System.out.println("---------- Create Password Again ---------");
                        System.out.println("------------------------------------------");
                        System.out.print("| Create your passwords : ");
                        password = kb.next();
                        System.out.print("|| Confirm your passwords : ");
                        String confirmPassword = kb.next();
                        confirm = password.equals(confirmPassword);//เช็ค Password ตรงกันมั้ย
                    }
                    //ถ้าไม่ตรงจะให้แสดง Confirm password not match. ด้วย
                    if (!confirm){
                        System.out.println();
                        System.out.println("Confirm password not match.");
                    }
                }while (!confirm);//ทำจนกว่า Password จะตรงกัน
                //จบลูปรับค่า Register

                //นำข้อมูลที่รับมาเมื่อกี้ไปเก็บไว้ในไฟล์ systemLogin.txt
                PrintWriter outputFile = new PrintWriter(systemLogin);
                outputFile.println("======== User ========");
                outputFile.println("Username : " + user);
                outputFile.println("Password : " + password);
                outputFile.println("======================");
                System.out.println("------------------------------------------");
                System.out.println("||---------- Register Success ----------||");
                System.out.println("------------------------------------------");
                outputFile.close();
                loginSuccess = true;
                checkOption = true;

            //เลือก Login
            }else if (optionUser == 'l' || optionUser == 'L') {

                //เริ่มหน้า Login
                boolean checkLogin = false;//สร้างมาเพื่อเช็คว่า Login เสร็จแล้วจบลูป
                boolean checkPassword = false;//สร้างมาเพื่อเช็ค Password ถ้ามีในระบบก็จบลูป

                //ลูป Login รับ user กับ password
                do {
                    dataLogin.clear();
                    boolean checkDataFile = false;
                    boolean checkBack = false;
                    System.out.println("------------------------------------------");
                    System.out.println("================== Login =================");
                    System.out.print("| Enter username : ");
                    userName = kb.next();
                    boolean checkEqualUser = false;
                    try {
                        //เริ่มลูปอ่านไฟล์ //ตรวจสอบชื่อ user ว่ามีในระบบมั้ยและชื่อถูกต้องมั้ย
                        while (!checkDataFile){
                            Scanner inputFile = new Scanner(new File("/Users/pk/Desktop/Year 1/My Project OOP Java/ProjectFreshmanOOP/src/systemLogin.txt"));

                            //เอาข้อมูลจาก systemLogin.txt มาเก็บไว้ใน ArrayList
                            if (inputFile.hasNext()){
                                while (inputFile.hasNext()) {
                                    dataLogin.add(inputFile.nextLine());
                                }
                                inputFile.close();
                            }else {//ถ้าในไฟล์ systemLogin.txt ไม่มีข้อมูล
                                System.out.println("Data not found!!, Please register.");
                                checkDataFile = true;
                                checkPassword = true;
                                checkLogin = true;
                                checkEqualUser = true;
                            }

                            //ตรวจสอบข้อมูล username ที่รับมาว่ามีในระบบมั้ยและชื่อถูกต้องมั้ย
                            for (int i = 0; i < dataLogin.size()-1; i++) {
                                if (dataLogin.get(i).contains(userName)){
                                    System.out.println("|-------------- User found --------------|");
                                    checkDataFile = true;
                                    checkLogin = true;
                                    checkEqualUser = true;
                                    break;
                                }
                            }

                            //ถ้าไม่มีข้อมูล username ในระบบหรือชื่อไม่ถูกต้อง
                            if (!checkEqualUser){
                                System.out.println("|=========== User not found !! ==========|");
                                checkDataFile = true;
                                checkBack = true;
                            }
                        }//จบลูปอ่านไฟล์ และตรวจสอบชื่อ user

                        //ถ้าไม่พบข้อมูลหรือชื่อ username ไม่ถูกต้อง
                        if (checkBack){
                            boolean loopBack = false;
                            //ลูปไว้เผื่อคนใส่ตัวเลือกผิด
                            do {
                                System.out.print("|Back to register press y/n : ");
                                char back = kb.next().charAt(0);
                                if (back == 'y' || back == 'Y'){
                                    checkLogin = true;
                                    loopBack = true;
                                    checkPassword = true;
                                } else if (back == 'n' || back == 'N') {
                                    loopBack = true;
                                } else {
                                    System.out.println("------------------------------------------");
                                    System.out.println("Your selection is out of available option.");
                                    System.out.println("Please try again.");
                                    System.out.println("------------------------------------------");
                                }
                            }while (!loopBack);
                        }
                      //ดักจับ ถ้าไม่พบไฟล์
                    } catch (FileNotFoundException e) {
                        System.out.println("Data not found!!, Please register.");
                        checkPassword = true;
                        checkLogin = true;
                    }
                } while (!checkLogin);
                //จบลูป User

                //เริ่มหน้า Password และเริ่มลูปรับ Password
                while (!checkPassword){
                    boolean checkDataFile = false;//เช็คว่า Password ถูกต้องมั้ย
                    boolean checkBack = false;//เช็คว่า จะให้ลองใส่ Password ใหม่มั้ยหรือจะออกไปหน้าแรก เพื่อไป Register
                    boolean checkEqualPass = false;//เช็คว่าถ้า Password ไม่ถูกต้อง
                    System.out.print("| Enter your password : ");
                    String password = kb.next();

                    //ลูปเช็ค Password
                    while (!checkDataFile) {
                        for (int i = 0; i < dataLogin.size() - 1; i++) {
                            //ถ้า Password ถูกต้อง
                            if (dataLogin.get(i).contains(password)) {
                                System.out.println("------------------------------------------");
                                System.out.println("|------------ Password correct ----------|");
                                System.out.println("------------------------------------------");
                                System.out.println("||==========|| Login success ||=========||");
                                checkOption = true; //ออกจากหน้า Login //ออกจากลูปใหญ่
                                loginSuccess = true; //จบลูปใหญ่ และLogin สำเร็จแล้วไปในหน้าโปรปกรมหลักได้
                                checkDataFile = true; //จบลูปนี้
                                checkPassword = true; //จบลูปรับ Password
                                checkEqualPass = true; //Password ถูกไม่ต้องไปกรอกใหม่
                                break;
                            }
                        }
                        //ถ้า Password ไม่ถูก
                        if (!checkEqualPass) {
                            System.out.println("------------------------------------------");
                            System.out.println("|=========== Invalid password ===========|");
                            System.out.println("============= Please try again ===========");
                            checkBack = true; //ไปถ้าว่าจะลองใส่อีกครั้งมั้ย
                            checkDataFile = true;//จบลูปนี้
                        }
                    }//จบลูปเช็ค Password

                    //ลูปถามว่าจะลองใส่อีกครั้งมั้ย
                    if (checkBack){
                        boolean loopBack = false;

                        //ลูปเผื่อผู้ใช้ใส่ตัวเลือกผิดให้วนรับใหม่
                        do {
                            System.out.print("|Try again press y/n : ");
                            char back = kb.next().charAt(0);
                            if (back == 'n' || back == 'N'){
                                loopBack = true; //จบลูปนี้
                                checkPassword = true; //จบลูปรับ Password
                                System.out.println("------------------------------------------");
                            } else if (back == 'y' || back == 'Y') {
                                loopBack = true;//จบลูปนี้ และให้วนรับ Password ใหม่
                                System.out.println("------------------------------------------");
                            } else {
                                System.out.println("------------------------------------------");
                                System.out.println("Your selection is out of available option.");
                                System.out.println("Please try again.");
                                System.out.println("------------------------------------------");
                            }
                        }while (!loopBack);
                    }
                }
                //จบลูปรับ Password
            }else if (optionUser == 'q' || optionUser == 'Q'){
                System.out.println("|============ Shutdown system ===========|");
                checkOption = true;//จบลูปใหญ่และสิ้นสุดโปรแกรม
            }else {
                System.out.println("------------------------------------------");
                System.out.println("Your selection is out of available option.");
                System.out.println("Please try again.");
                System.out.println("------------------------------------------");
            }
        }while (!checkOption);
        //จบลูป Login และ Register

        FileWriter customerData;//สร้างไว้เพื่อเก็บข้อมูลลูกค้าทั้งหมด
        FileWriter employeeData;//สร้างไว้เพื่อเก็บข้อมูลพนักงานทั้งหมด

        //ถ้า Login สำเร็จ
        if (loginSuccess){
            ArrayList<Employee> emp = new ArrayList<>();//สร้างไว้เพื่อเก็บข้อมูลพนักงานแต่ล่ะตำแหน่งงาน
            Case casePatient = new Case();//สร้างไว้เพื่อตรวจและเช็ครายล่ะเอียดตัวเลือกการรักษา

            try {
                customerData = new FileWriter("/Users/pk/Desktop/Year 1/My Project OOP Java/ProjectFreshmanOOP/src/CustomerInformation.txt", true);
            } catch (IOException e) {
                System.out.println("File missing!!");
                throw new RuntimeException(e);
            }
            try {
                employeeData = new FileWriter("/Users/pk/Desktop/Year 1/My Project OOP Java/ProjectFreshmanOOP/src/EmployeeInformation.txt", true);
            } catch (IOException e) {
                System.out.println("File missing!!");
                throw new RuntimeException(e);
            }

            char optionSystem;//เช็คจบการทำงานโปรแกรม

            System.out.println("| Welcome " + userName +  " |");
            System.out.println("------------------------------------------");
            System.out.print("| Date (dd/mm/yyyy) : ");
            String date = kb.next();

            PrintWriter saveEmpData = new PrintWriter(employeeData);//เก็บข้อมูลพนักงานในไฟล์ EmployeeInformation.txt
            PrintWriter saveCusData = new PrintWriter(customerData);//เก็บข้อมูลลูกค้าในไฟล์ CustomerInformation.txt

            saveEmpData.println("| " + date + " |");
            saveCusData.println("| " + date + " |");

            //เริ่มลูปการทำงานโปรแกรม
            do {
                boolean checkErrorQuit = false;//เช็คว่าผู้ใช้ป้อนตัวเลือกผิดรึเปล่า
                System.out.println("----------------------------------------------");
                System.out.println();
                System.out.println("||=============== Menu System ==============||");
                System.out.println("|Press E : Add employee attendance work.");
                System.out.println("|Press P : Add a patient case.");
                System.out.println("|Press O : Add OT employee.");
                System.out.println("|Press T : Total income of each employee.");
                System.out.println("|Press I : Total income of the company today.");
                System.out.println("|----------------- Q = Quit -----------------|");
                System.out.print("|Enter your selection : ");
                optionSystem = kb.next().charAt(0);

                //ถ้าป้อน Q ให้สิ้นสุดโปรแกรม
                if (optionSystem == 'Q'){
                    checkErrorQuit = true;
                }

                //ถ้าเลือก E
                if (optionSystem == 'E' || optionSystem == 'e'){
                    System.out.println("----------------------------------------------");
                    System.out.println("       | Add employee attendance work |       ");
                    System.out.print("|Name (First name / Last name) : ");
                    String nameEmployee = kb.next();
                    String lastName = kb.next();
                    nameEmployee += " ";
                    nameEmployee += lastName;
                    saveEmpData.print(nameEmployee + " ");
                    String jopPos;

                    //ลูปรับและเช็คตำแหน่งงานว่าป้อนถูกมั้ย
                    boolean checkJobPos = false;
                    while (!checkJobPos){
                        System.out.println("----------------------------------------------");
                        System.out.println("|=============== Job Position ===============|");
                        System.out.println("| Cashier (แคชเชียร์) |");
                        System.out.println("| Physical (นักกายภาพ) |");
                        System.out.println("| Chiropractor (หมอนวด) |");
                        System.out.println("----------------------------------------------");
                        System.out.print("|Job position : ");
                        jopPos = kb.next();
                        switch (jopPos) {
                            case "Cashier" -> {
                                saveEmpData.println(jopPos);
                                Employee cashier = new CashierEmployee();
                                cashier.setName(nameEmployee);
                                emp.add(cashier);
                                checkJobPos = true;
                            }
                            case "Physical" -> {
                                saveEmpData.println(jopPos);
                                Employee phy = new PhysicalTherapistEmployee();
                                phy.setName(nameEmployee);
                                emp.add(phy);
                                checkJobPos = true;
                                System.out.println("----------------------------------------------");
                                System.out.println("||================= Success ================||");
                            }
                            case "Chiropractor" -> {
                                saveEmpData.println(jopPos);
                                Employee chi = new ChiropractorEmployee();
                                chi.setName(nameEmployee);
                                emp.add(chi);
                                checkJobPos = true;
                            }
                            default -> System.out.println("----------------------------------------------" + "\n" +
                                    "Job position doesn't match.");
                        }
                    }
                    saveEmpData.close();//ปิด File ข้อมูลพนักงาน
                    //จบตัวเลือก E

                //ถ้าเลือก P
                }else if (optionSystem == 'P' || optionSystem == 'p'){
                    FileCustomers filePatient = new FileCustomers();
                    System.out.println("----------------------------------------------");
                    System.out.println("            | Add a patient case |            ");
                    System.out.print("|Patient name (First name / Last name): ");
                    String name = kb.next();
                    String lastName = kb.next();
                    name += " ";
                    name += lastName;
                    System.out.println("----------------------------------------------");
                    System.out.print("|Patient's date of birth (dd/mm/yyyy): ");

                    String birthDate = kb.next();
                    System.out.println("----------------------------------------------");
                    System.out.print("|Patient age: ");
                    String age = kb.next();
                    System.out.println("----------------------------------------------");
                    System.out.print("|Patient ID card number: ");
                    String id = kb.next();
                    System.out.println("----------------------------------------------");
                    System.out.println("|--------- Patient treatment history --------|");
                    filePatient.getFiles(id);
                    System.out.println("----------------------------------------------");
                    System.out.print("|Patient's symptoms: ");
                    String symptoms = kb.next();
                    System.out.println("----------------------------------------------");
                    casePatient.listTreatmentOptions(); //แสดงตัวเลือกการรักษาว่ามีอะไรบ้าง
                    boolean checkPerform;
                    int perform = 0;

                    //ลูปรับจำนวนว่าจะรักษากี่แบบ และเช็คว่าป้อนตัวเลขผิดรึเปล่า
                    do {
                        try {
                            System.out.println("----------------------------------------------");
                            System.out.print("|How many treatments: ");
                            perform = kb.nextInt();
                            checkPerform = true;
                            if (perform < 0){
                                System.out.println("----------------------------------------------");
                                System.out.println("Your input can't be negative!!!!!");
                                System.out.println("----------------------------------------------");
                                checkPerform = false;
                            }
                        }catch (InputMismatchException ie){
                            System.out.println("----------------------------------------------");
                            System.out.println("Your input is miss match.");
                            System.out.println("----------------------------------------------");
                            kb.next();
                            checkPerform = false;

                        }
                    }while (!checkPerform);//จบลูปรับจำนวนการรักษา

                    String treat;
                    ArrayList<String> treatment = new ArrayList<>();
                    String preTreat;
                    double preCost = 0;

                    //ลูปรับว่าจะรักษาอะไรบ้าง
                    for (int i = 0; i < perform; i++) {
                        System.out.println("----------------------------------------------");
                        System.out.print("|Treated with: ");
                        treat = kb.next();
                        if (casePatient.checkTrue(treat)){
                            preTreat = casePatient.checkTreatment(treat);
                            treatment.add(casePatient.checkTreatment(treat));
                            preCost += casePatient.setTreatmentCost(preTreat);
                        }else {
                            System.out.println("----------------------------------------------");
                            System.out.println("Treatment is incorrect, Please try again.");
                            i--;
                        }
                    }//จบลูป

                    System.out.println("------------------------------------------------");
                    //แสดงจำนวนค่ารักษา
                    System.out.println("| Total treatment costs : " + preCost + " Baht |");

                    boolean checkCost;
                    double cost = 0;

                    //ลูปกรอกค่ารักษา และเช็คว่าใส่ตัวเลขผิดมั้ย
                    do {
                        try {
                            System.out.println("------------------------------------------------");
                            System.out.print("|Treatment costs: ");
                            cost = kb.nextDouble();
                            checkCost = true;
                            if (cost < 0){
                                System.out.println("-------------------------------------------------");
                                System.out.println("Cost is incorrect!!!, Cost can't be negative!!!!!");
                                checkCost = false;
                            }
                        }catch (InputMismatchException ie){
                            System.out.println("----------------------------------------------");
                            System.out.println("Cost is incorrect!!!");
                            checkCost = false;
                        }
                    }while (!checkCost);//จบลูปรับค่ารักษา

                    System.out.println("------------------------------------------------");
                    System.out.println("||================== Success =================||");
                    System.out.println();
                    casePatient.updateIncome(cost);//เก็บรายได้
                    //เอาข้อมูลไปเก็บไว้ในไฟล์ CustomerInformation.txt
                    saveCusData.print(id + " " + name + " " + birthDate + " (" + age + " years old) " + symptoms + " : ");
                    for (String treats : treatment){
                        saveCusData.print(treats + " ");
                    }
                    saveCusData.println(cost + " Baht");
                    saveCusData.close();//ปิดไหล์
                    //จบตัวเลือก P

                //ถ้าเลือก O
                }else if (optionSystem == 'O' || optionSystem == 'o'){
                    System.out.println("----------------------------------------------");
                    System.out.println("              | Add OT employee |             ");
                    boolean checkOTName;
                    String name;
                    boolean checkErrorName = false;
                    boolean checkYN;

                    //ลูปเพิ่ม OT พนักงาน //รับชื่อพนักงานและเช็คว่าถูกต้องมั้ย
                    do {
                        checkOTName = false;
                        checkYN = false;
                        System.out.print("|Enter name (First name / Last name): ");
                        name = kb.next();
                        String lastName = kb.next();
                        System.out.println("----------------------------------------------");
                        name += " ";
                        name += lastName;

                        //ลูปเช็คว่าชื่อพนักงานถูกมั้ย
                        for (Employee em : emp){
                            if (em.getName().contains(name) || em.getName().toLowerCase().contains(name)){
                                checkOTName = true;
                                checkYN = true;
                                break;
                            }
                        }

                        //ถ้าไม่ตรง
                        if (!checkYN){
                            System.out.println("This name isn't found in system.");
                            while (!checkYN){
                                System.out.println("----------------------------------------------");
                                System.out.print("Try again? press y/n : ");
                                char back = kb.next().charAt(0);
                                if (back == 'n' || back == 'N'){
                                    checkErrorName = true;
                                    checkOTName = true;
                                    checkYN = true;
                                }else if (back == 'y' || back == 'Y'){
                                    checkYN = true;
                                    System.out.println("----------------------------------------------");
                                }else {
                                    System.out.println("----------------------------------------------");
                                    System.out.println("Your selection is out of available option.");
                                    System.out.println("Please try again.");
                                    System.out.println("----------------------------------------------");
                                }
                            }
                        }

                    }while (!checkOTName);
                    //จบลูปเช็คชื่อพนักงาน

                    //ถ้าชื่อถูก
                    if (!checkErrorName) {
                        boolean checkOTHour;
                        int hour = 0;

                        //เริ่มลูปรับค่าชั่วโมงและตรวจว่าป้อนค่าถูกมั้ย
                        do {
                            try {
                                System.out.print("|Hour : ");
                                hour = kb.nextInt();
                                System.out.println("----------------------------------------------");
                                checkOTHour = true;
                                if (hour < 0) {
                                    System.out.println("-------------------------------------------------");
                                    System.out.println("Hour is incorrect!!!, Hour can't be negative!!!!!");
                                    System.out.println("-------------------------------------------------");
                                    checkOTHour = false;
                                }
                            } catch (InputMismatchException ie) {
                                System.out.println("----------------------------------------------");
                                System.out.println("Hour is incorrect!!!");
                                System.out.println("----------------------------------------------");
                                kb.next();
                                checkOTHour = false;
                            }
                        } while (!checkOTHour);//จบลูป

                        boolean checkOTRate;
                        int rate = 0;

                        //เริ่มลูปรับค่าเรทราคาและตรวจว่าป้อนค่าถูกมั้ย
                        do {
                            try {
                                System.out.print("|Rate : ");
                                rate = kb.nextInt();
                                checkOTRate = true;
                                if (rate < 0) {
                                    System.out.println("-------------------------------------------------");
                                    System.out.println("Rate is incorrect!!!, Hour can't be negative!!!!!");
                                    System.out.println("-------------------------------------------------");
                                    checkOTRate = false;
                                }
                            } catch (InputMismatchException ie) {
                                System.out.println("----------------------------------------------");
                                System.out.println("Rate is incorrect!!!");
                                System.out.println("----------------------------------------------");
                                kb.next();
                                checkOTRate = false;
                            }
                        } while (!checkOTRate);//จบลูป

                        System.out.println("||================= Success ================||");
                        //บันทึกข้อมูลและคำนวนค่าจ้างรายวันของพนักงานแต่ล่ะคน
                        for (Employee em : emp) {
                            if (em.getName().contains(name) || em.getName().toLowerCase().contains(name)) {
                                em.setOT(hour, rate);
                            }
                        }
                    }
                    //จบตัวเลือก O

                //ถ้าเลือก T
                }else if (optionSystem == 'T' || optionSystem == 't'){
                    System.out.println("----------------------------------------------");
                    System.out.println("       | Total income of each employee |      ");
                    boolean checkTotal = false;
                    for (Employee em : emp){
                        System.out.println(em.totalIncome());
                        checkTotal = true;
                    }
                    //ไม่มีข้อมูล
                    if (!checkTotal){
                        System.out.println("             | Data Not Found! |              ");
                    }
                    //จบตัวเลือก T

                //ถ้าเลือก I
                }else if (optionSystem == 'I' || optionSystem == 'i'){
                    System.out.println("----------------------------------------------");
                    System.out.println("| Total income of the company today : " + casePatient.getTotalIncome() + " |");
                    //จบตัวเลือก I

                //ถ้าผู้ใช้ป้อนตัวเลือกไม่ถูก
                }else if (!checkErrorQuit){
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Please try again, Your option isn't available in the system.");
                }

            }while (optionSystem != 'Q');

            saveCusData.close();
            saveEmpData.close();
            //จบลูปและจบการทำงานโปรแกรม
            System.out.println("----------------------------------------------");
            System.out.println("||============== Logout System =============||");
        }
    }
}
