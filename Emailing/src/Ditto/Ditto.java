package Ditto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Micah
 */
import Jama.Matrix;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Ditto {//Dual 
    //Iteration 
    //Tele-
    //Transmition 
    //Operator

    /**
     * @param args the command line arguments
     */
    //Initiate Variables
    public static final String OVERRIDE = "EDLLRN";
    public static String Signature;
    public static boolean PMusic = false;
    public static boolean OverR = false;
    public static boolean OLeet = false;
    public static boolean CBT;
    public static boolean SayCBT;
    public static boolean SayO;
    public static boolean OtoFile;
    public static String IDcode;
    public static String TargetID;
    public static String UserName;
    public static boolean sendFile;
    public static boolean sendEmail;
    public static boolean Alive = true;
    public static boolean update = false;
    public static boolean mouseLock;
    public static boolean encodemessage;
    public static String fileToSend;
    public static ArrayList<ArrayList> Variables;
    public static Email User;
    public static int tarx = 0;
    public static int tary = 0;
    public static String[] dat;
    public static boolean[] ShowWork = new boolean[2];// 0 - Email, 1 - Command Line
    public static ArrayList<MailBox> Mail = new ArrayList<MailBox>();
    public static double[][] enc = new double[][]{
        {2, -17, 11},
        {-1, 10, -7},
        {0, 3, -2}
    };

    public static void main(String[] args) {
        //Launch Pre-operation routines;
        Encryption matx = new Encryption(new Matrix(enc));
        StartUp.loadVars();
        Convert.printArrayMB(Mail);
        System.out.println(User.toString());
        dat = args;
        //temp();
        Convert.printArray(Website.researchPackage("engineering"));
        Ditto();

    }
    public static int iat = 1;

    public static void temp() {
        int total = 0;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 2; j++) {
                int temp = 3 * i + 2 * j;
                System.out.println(temp + "\n");
                total += temp;
            }
        }
        System.out.println("Total = " + total);
    }

    public static void Ditto() {
        //Begin main processes
        ShowWork[0] = false;
        ShowWork[1] = false;
        startDittoT();
        startLocalControlT();
        startCBctrlT();
        startMouseT();
        Prompt();
    }

    public static void Prompt() {
        Scanner inpt = new Scanner(System.in);
        while (Alive) {

            System.out.println("Enter Command:");
            String in = inpt.nextLine()+"#";//.replaceAll(" ", "#").replaceAll("_", " ") + "#";
            try {
                String otpt = Convert.arrayToString(CommandProcessor(in));
                if (OLeet) {
                    System.out.println(Convert.arrayToString(Encryption.Leet(Convert.stringToArray(otpt, "\n")), "\n"));
                } else {
                    System.out.println(otpt);
                }
                if (SayO) {
                    Convert.say(otpt);
                }
                if (OtoFile) {
                    FolderC.writeToFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "SystemFiles" + System.getProperty("file.separator") + "Log.txt", "\n\n" + Time.getInstant() + otpt, true);
                }
            } catch (Exception e) {
                Writer writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                e.printStackTrace(printWriter);
                String s = writer.toString();
                FolderC.writeToFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "SystemFiles" + System.getProperty("file.separator") + "Log.txt", "\n\nError:" + in + "\n" + s, true);
                System.err.println(s);
                System.err.println("Enter Command:");
            }
        }
    }
    public static boolean[] checkpoint = new boolean[4];

    public static void switchToLocal() {
        //Processes code to allow user access to the GUI
        int wide = (int) Math.ceil(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        int high = (int) Math.ceil(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        if (!checkpoint[0] && !checkpoint[1] && !checkpoint[2] && !checkpoint[3] && Control.pos_check(0, 50, 0, 50)) {
            checkpoint[0] = true;
            iat = Time.getSec();
        }
        if (checkpoint[0] && !checkpoint[1] && !checkpoint[2] && !checkpoint[3] && Control.pos_check(wide - 50, wide, high - 50, high)) {
            checkpoint[1] = true;
        }
        if (checkpoint[0] && checkpoint[1] && !checkpoint[2] && !checkpoint[3] && Control.pos_check(wide - 50, wide, 0, 50)) {
            checkpoint[2] = true;
        }
        if (checkpoint[0] && checkpoint[1] && checkpoint[2] && !checkpoint[3] && Control.pos_check(0, 50, high - 50, high)) {
            checkpoint[3] = true;
        }
        if (checkpoint[0] && checkpoint[1] && checkpoint[2] && checkpoint[3]) {
            resetCheckPoints();
            GUIpass.main(dat);
        }
    }

    public static void resetCheckPoints() {
        //resets code. See switchToLocal()
        for (int i = 0; i < checkpoint.length; i++) {
            checkpoint[i] = false;
        }
    }

    public static void Emailer(Email email1, MailBox email2, String str) {
        //Process and sends email data
        if (encodemessage) {
            if (sendFile) {
                email1.sendEmails(email2.address, email2.ID, Encryption.encode(Signature + str), fileToSend);
            } else {
                email1.sendEmails(email2.address, email2.ID, Encryption.encode(Signature + str));
            }
        } else {
            if (sendFile) {
                email1.sendEmails(email2.address, email2.ID, Signature + str, fileToSend);
            } else {
                email1.sendEmails(email2.address, email2.ID, Signature + str);
            }
            //encodemessage = true;
        }
        sendEmail = false;
        sendFile = false;
    }

    public static String MessageMaker(ArrayList<String> arr1) {
        //Assembles return messages from entered commands
        ArrayList<String> MainMessage = new ArrayList<String>();
        MainMessage.add(Time.getInstant());
        for (int i = 0; i < arr1.size(); i++) {
            if (arr1.get(i).contains("ID: ")) {
                TargetID = arr1.get(i).substring(arr1.get(i).indexOf("ID: ") + 4);
            }
            if (arr1.get(i).contains("#")) {
                MainMessage.add("************************************");
                Convert.combineArrays(MainMessage, CommandProcessor(arr1.get(i)));
            }

        }
        return Convert.arrayToString(MainMessage);
    }

    public static Integer[] printlist() {
        Convert.printArrayMB(Mail);
        Integer[] tempint = new Integer[2];
        System.out.println("Please Select Target ID:");
        Scanner inpt = new Scanner(System.in);
        String tempstr = inpt.next();
        for (int i = 0; i < tempstr.length(); i++) {
            if (!MathWorks.isNum(tempstr.charAt(i))) {
                tempstr = tempstr.substring(0, i) + tempstr.substring(i + 1);
                i--;
            }
        }

        tempint[0] = Convert.toInt(tempstr);
        if (tempint[0] > Mail.size()) {
            System.out.println(tempint[0] + " is not an option.");
            printlist();
        }

        System.out.println("Please Select A Setting:");
        System.out.println("0) Encoding On");
        System.out.println("1) Encoding Off");
        System.out.println("2) Exit");
        tempstr = inpt.next();
        for (int i = 0; i < tempstr.length(); i++) {
            if (!MathWorks.isNum(tempstr.charAt(i))) {
                tempstr = tempstr.substring(0, i) + tempstr.substring(i + 1);
                i--;
            }
        }

        tempint[1] = Convert.toInt(tempstr);
        if (tempint[1] > 2) {
            System.out.println(tempint[1] + " is not an option.");
            printlist();
        } else {
            return tempint;
        }

        tempint[0] = -1;
        tempint[1] = -1;
        return tempint;
    }

    public static void Target() {
        Scanner inpt = new Scanner(System.in);
        Integer[] tempint = printlist();
        if (tempint[1] != 2) {
            System.out.println("Targeting: " + Mail.get(tempint[0]).toString().substring(6));
            ArrayList<String> message = new ArrayList<String>();
            message.add("");
            while (!message.get(message.size() - 1).toUpperCase().equals("END")&&!message.get(message.size() - 1).toUpperCase().equals("EXIT")) {
                message.add(inpt.nextLine());
            }
            boolean tempbool = message.get(message.size() - 1).toUpperCase().equals("END");
            message.remove(0);
            message.remove(message.size() - 1);
            if (tempint[1] == 1) {
                encodemessage = false;
            }
            if(tempbool){
                System.out.println("Sending...");
                Emailer(User, Mail.get(tempint[0]), Convert.arrayToString(message));
            }
        }
    }

    public static void NewEmail() {
        Scanner inpt = new Scanner(System.in);
        String data[] = new String[4];
        System.out.println("Please Enter The Contact's First Name:");
        data[0] = inpt.nextLine();
        System.out.println("Please Enter The Contact's Last Name:");
        data[1] = inpt.nextLine();
        System.out.println("Please Enter The Contact's ID:");
        data[2] = inpt.nextLine();
        System.out.println("Please Enter The Contact's Address:");
        data[3] = inpt.nextLine();
        Mail.add(new MailBox(data[0] + " " + data[1], data[2], data[3]));
    }

    public static void RemoveEmail() {
        Scanner inpt = new Scanner(System.in);
        System.out.println("Please Select A Contact To Delete:");
        System.out.println(Convert.arrayToString(ShowCont()));
        Mail.remove(Mail.get(inpt.nextInt()));

    }

    public static ArrayList<String> CommandProcessor(String Command) {
        //process incoming commands.  see MessageMaker()
        ArrayList<String> MiniMessage = new ArrayList<String>();
        ArrayList<String> List = Convert.removeLast(Convert.stringToArray(Command, "#"));
        List.set(0, List.get(0).toUpperCase());
        if (ShowWork[1]) {
            MiniMessage.add(Command);
            Convert.combineArrays(MiniMessage, List);
        }
        if (List.get(0).equals("ADD EMAIL")) {
            if (List.size() == 4) {
                if (List.get(1).equals("LETU")) {
                    Mail.add(new MailBox(List.get(2) + " " + List.get(3), StartUp.IDGen(), List.get(2) + List.get(3) + "@letu.edu"));
                    MiniMessage.add(Mail.get(Mail.size() - 1).toString());
                    sendEmail = true;
                } else {
                    Mail.add(new MailBox(List.get(1), List.get(2), List.get(3)));
                    MiniMessage.add(Mail.get(Mail.size() - 1).toString());
                    sendEmail = true;
                }
            } else if (List.size() == 1) {
                NewEmail();
            } else{
                MiniMessage.add("Command: \"" + Convert.arrayToString(List).replaceFirst("[\r\n]", " ").replaceAll("[\r\n]", " ") + "\" is not recognized.");
            sendEmail = true;
            }
        } //
        //
        else if (List.get(0).equals("REMOVE EMAIL")) {
            if (List.size() == 2) {
                String name = Mail.get(Convert.toInt(List.get(1))).userName;
                Mail.remove(Mail.get(Convert.toInt(List.get(1))));
                MiniMessage.add(name + " has been removed.");
                sendEmail = true;
            } else if (List.size() == 1) {
                RemoveEmail();
            } else{
                MiniMessage.add("Command: \"" + Convert.arrayToString(List).replaceFirst("[\r\n]", " ").replaceAll("[\r\n]", " ") + "\" is not recognized.");
            sendEmail = true;
            }
        }//
        //
        else if (List.get(0).equals("SHOW CONTACTS") && List.size() == 1) {
            MiniMessage.add(Convert.arrayToString(ShowCont()));
            sendEmail = true;
        }//
        //
        else if (List.get(0).equals("SEND TO")) {
            //Emailer(User, Mail.get(tempint[0]), Convert.arrayToString(message));
            if (List.size() == 3) {
                Emailer(User, Mail.get(Convert.toInt(List.get(1))), List.get(2));
            } else if (List.size() == 4) {
                if (new File(List.get(3)).exists()) {
                    sendFile = true;
                    fileToSend = List.get(1);
                    MiniMessage.add("Send File: " + List.get(1) + "...");
                    Emailer(User, Mail.get(Convert.toInt(List.get(1))), List.get(2));
                    MiniMessage.add("==>Completed.");
                } else {
                    MiniMessage.add("==> File Not Found.");
                    sendEmail = true;
                }
            } else if (List.size() == 1) {
                Target();
            } else{
                MiniMessage.add("Command: \"" + Convert.arrayToString(List).replaceFirst("[\r\n]", " ").replaceAll("[\r\n]", " ") + "\" is not recognized.");
            sendEmail = true;
            }
        } //
        //
        else if (List.get(0).equals("HELP") && List.size() == 1) {
            MiniMessage.add("List Of Commands:");
            MiniMessage.add("Add Email-STRING_NAME STRING_ID STRING_ADDRESS");
            MiniMessage.add("Add Email-\"LETU\" STRING_FIRSTNAME STRING_LASTNAME");
            MiniMessage.add("Remove Email-INT_CONTACTID");
            MiniMessage.add("Show Contacts-");
            MiniMessage.add("Send To-INT_CONTACTID STRING_MESSAGE");
            MiniMessage.add("Send To-INT_CONTACTID STRING_MESSAGE STRING_FILELOC");
            MiniMessage.add("Help-");
            MiniMessage.add("Terminate-");
//            MiniMessage.add("Override Mouse-INT_XCOOR INT_YCOOR");
//            MiniMessage.add("Release Mouse-");
//            MiniMessage.add("Get Mouse Position-");
//            MiniMessage.add("Move Mouse-INT_XCOOR INT_YCOOR");
//            MiniMessage.add("Take Screenshot-");
//            MiniMessage.add("Take Screenshot-STRING_FILELOC");
//            MiniMessage.add("Move File-STRING_FILELOC~FROM STRING_FILELOC~TO");
//            MiniMessage.add("Create File-STRING_FILELOC");
//            MiniMessage.add("Write To File-STRING_FILELOC STRING_TEXT");
//            MiniMessage.add("Read File-STRING_FILELOC");
//            MiniMessage.add("Delete File-STRING_FILELOC");
//            MiniMessage.add("Get Files-STRING_FILELOC STRING_TYPE");
//            MiniMessage.add("Save Output-BOOLEAN_ACTIVE");
//            MiniMessage.add("Run-STRING_FILELOC");
//            MiniMessage.add("SayCBT-BOOLEAN_ACTIVE");
//            MiniMessage.add("Play Music-BOOLEAN_ACTIVE");
//            MiniMessage.add("CBT-BOOLEAN-ACTIVE");
//            MiniMessage.add("Copy From Clipboard-");
//            MiniMessage.add("Zip File-STRING_FILELOC (beta)");
//            MiniMessage.add("UnZip File-STRING_FILELOC");
//            MiniMessage.add("Calculate-STRING_EQUATION");
//            MiniMessage.add("Voice-BOOLEAN_ACTIVE");
//            MiniMessage.add("Define-STRING_WORD");
//            MiniMessage.add("Save To File-BOOLEAN_ACTIVE");
//            MiniMessage.add("Update-");
//            MiniMessage.add("Get OS-");
        } //
        //
        else if (List.get(0).equals("TERMINATE") && List.size() == 1) {
            MiniMessage.add(User.userName + "'s Ditto terminated." + Time.getInstant());
            MiniMessage.add("Saving MailBox...");
            ShutDown.SaveMail();
            MiniMessage.add("Clearing Data...");

            ShutDown.ClearData();
            Alive = false;
        }//
        // 
        //        else if (List.get(0).equals("OVERRIDE MOUSE") && List.size() == 3) {
        //            MiniMessage.add("Override Mouse");
        //            tarx = Convert.toInt(List.get(1));
        //            tary = Convert.toInt(List.get(2));
        //            mouseLock = true;
        //            MiniMessage.add("==>Successful");
        //            sendEmail = true;
        //        } //
        //        //
        //        
        //        else if (List.get(0).equals("RELEASE MOUSE") && List.size() == 1) {
        //            MiniMessage.add("Mouse Released");
        //            mouseLock = false;
        //            MiniMessage.add("==>Successful");
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("GET MOUSE POSITION") && List.size() == 1) {
        //            MiniMessage.add("Get Mouse Position");
        //            String temp = Control.getMousePos();
        //            if (!temp.equals("")) {
        //                MiniMessage.add("==> mouse at " + temp);
        //            } else {
        //                MiniMessage.add("Could not get mouse position.");
        //            }
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("MOVE MOUSE") && List.size() == 3) {
        //            MiniMessage.add("Move Mouse to [" + List.get(1) + "," + List.get(2) + "]");
        //            Control.jump_mouse(Convert.toInt(List.get(1)), Convert.toInt(List.get(2)));
        //            if (Control.pos_check(Convert.toInt(List.get(1)), Convert.toInt(List.get(2)))) {
        //                MiniMessage.add("==> Moved Mouse to [" + List.get(1) + "," + List.get(2) + "]");
        //            } else {
        //                MiniMessage.add("Could not move mouse.");
        //            }
        //            sendEmail = true;
        //        } //
        //        //  
        //        //
        //        //  
        //        //
        //        //
        //        
        //        }//
        //        //
        //        else if (List.get(0).equals("TAKE SCREENSHOT") && List.size() == 1) {
        //            MiniMessage.add("Take Screenshot: ");
        //            String fileloc = Control.takeScreenShot();
        //            System.out.println(fileloc);
        //            MiniMessage.add("File Saved To: " + fileloc);
        //            sendEmail = true;
        //            if (new File(fileloc).exists()) {
        //                sendFile = true;
        //                fileToSend = fileloc;
        //            } else {
        //                MiniMessage.add("==> File Not Found.");
        //            }
        //        } //
        //        //
        //        else if (List.get(0).equals("TAKE SCREENSHOT") && List.size() == 2) {
        //            MiniMessage.add("Take Screenshot: " + List.get(1));
        //            String fileloc = Control.takeScreenShot(List.get(1));
        //            MiniMessage.add("File Saved To: " + fileloc);
        //            sendEmail = true;
        //            if (new File(fileloc).exists()) {
        //                sendFile = true;
        //                fileToSend = fileloc;
        //            } else {
        //                MiniMessage.add("==> File Not Found.");
        //            }
        //        } //
        //        //  
        //        //
        //        //  
        //        //
        //        //
        //        else if (List.get(0).equals("MOVE FILE") && List.size() == 3) {
        //            MiniMessage.add("Move File " + List.get(1) + " To " + List.get(2));
        //            MiniMessage.add(FolderC.moveFile(List.get(1), List.get(2)));
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("CREATE FILE") && List.size() == 2) {
        //            MiniMessage.add("Create File " + List.get(1));
        //            MiniMessage.add(FolderC.createFile(List.get(1)));
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("WRITE TO FILE") && List.size() == 3) {
        //            MiniMessage.add("Write To " + List.get(2) + " to File " + List.get(1));
        //            FolderC.writeToFile(List.get(1), List.get(2), true);
        //            MiniMessage.add("==> Done");
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("READ FILE") && List.size() == 2) {
        //            MiniMessage.add("Read File " + List.get(1));
        //            FolderC.readFile(List.get(1));
        //            MiniMessage.add("==> Done");
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("DELETE FILE") && List.size() == 2) {
        //            MiniMessage.add("Delete File" + List.get(1));
        //            Convert.combineArrays(MiniMessage, FolderC.deleteFile(List.get(1)));
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("GET FILES") && List.size() == 3) {
        //            MiniMessage.add("Get Files: " + List.get(1) + "****." + List.get(2));
        //            int temp = MiniMessage.size();
        //            Convert.combineArrays(MiniMessage, Convert.stringToArray(FolderC.getFiles(new File(List.get(1)), 0, -1, List.get(2)).toString(), "\n"));
        //            if (temp == MiniMessage.size()) {
        //                MiniMessage.add("==> No Data.");
        //            }
        //            sendEmail = true;
        //        }//
        //        //
        //        else if (List.get(0).equals("RUN") && List.size() == 2) {
        //            MiniMessage.add("Run: " + List.get(1));
        //            FolderC.run(List.get(1));
        //            sendEmail = true;
        //        } //
        //        //
        //        //
        //        //  
        //        //
        //        //
        //        
        //        //
        //        //  
        //        //
        //        //
        //        
        //        else if (List.get(0).equals("VOICE") && List.size() == 2) {
        //            MiniMessage.add("Voice modified to " + List.get(1));
        //            SayO = Convert.toBoolean(List.get(1));
        //        } //
        //        // 
        //        else if (List.get(0).equals("SAYCBT") && List.size() == 2) {
        //            MiniMessage.add("CBT Voice modified to " + List.get(1));
        //            SayCBT = Convert.toBoolean(List.get(1));
        //        } //
        //        // 
        //        else if (List.get(0).equals("SAVE OUTPUT") && List.size() == 2) {
        //            MiniMessage.add("Save Output modified to " + List.get(1));
        //            OtoFile = Convert.toBoolean(List.get(1));
        //        }//
        //        // 
        //        else if (List.get(0).equals("PLAY MUSIC") && List.size() == 2) {
        //            MiniMessage.add("Play Music modified to " + List.get(1));
        //            PMusic = Convert.toBoolean(List.get(1));
        //            if (PMusic) {
        //                String[] temp = new String[1];
        //                temp[0] = System.getProperty("user.dir") + System.getProperty("file.separator") + "SystemFiles" + System.getProperty("file.separator") + "PMusic.mp3";
        //                FolderC.runGhost(temp);
        //            } else {
        //            }
        //
        //        }//
        //        // 
        //        else if (List.get(0).equals("CBT") && List.size() == 2) {
        //            MiniMessage.add("Clipboard control modified to " + List.get(1));
        //            CBT = Convert.toBoolean(List.get(1));
        //        } else if (List.get(0).equals("OLEET") && List.size() == 2) {
        //            MiniMessage.add("OLeet modified to " + List.get(1));
        //            OLeet = Convert.toBoolean(List.get(1));
        //        } //  
        //        //
        //        //  
        //        //
        //        //
        //        else if (List.get(0).equals("COPY FROM CLIPBOARD") && List.size() == 1) {
        //            MiniMessage.add("Copy From Clipboard");
        //            MiniMessage.add(FolderC.pasteFromClipboard());
        //            sendEmail = true;
        //        } //
        //        //
        //        //////        else if (List.get(0).equals("ZIP FILE") && List.size() == 1) {
        //        //////            MiniMessage.add("Zip File");
        //        //////            Convert.combineArrays(MiniMessage, FolderC.ZipIt(List.get(1)));
        //        //////            sendEmail = true;
        //        //////        } //
        //        //////        //
        //        else if (List.get(0).equals("UNZIP FILE") && List.size() == 1) {
        //            MiniMessage.add("UnZip File");
        //            Convert.combineArrays(MiniMessage, FolderC.unZipIt(List.get(1)));
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("GET OS") && List.size() == 1) {
        //            MiniMessage.add("Get OS");
        //            MiniMessage.add(FolderC.getOS());
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("UPDATE") && List.size() == 1) {
        //            MiniMessage.add("Update");
        //            Alive = false;
        //            sendEmail = true;
        //            update = true;
        //        } //
        //        //
                else if (List.get(0).equals("CALCULATE") && List.size() == 2) {
                    MiniMessage.add(Convert.symToText(List.get(1) + " Reduces To " + MathWorks.SolveEq(ShowWork[1], List.get(1))));
                    sendEmail = true;
                } 
//        else if (List.get(0).equals("RESEARCH") && List.size() == 2) {
//            MiniMessage.add(Convert.arrayToString(Website.researchPackage(List.get(1))));
//            MiniMessage.add(Convert.arrayToString(Dictionary.defineWord(List.get(1))));
//            sendEmail = true;
//        } //
        //        //
        //        else if (List.get(0).equals("DEFINE") && List.size() == 2) {
        //            MiniMessage.add("Searching...");
        //            MiniMessage.add(Convert.arrayToString(Dictionary.defineWord(List.get(1))));
        //            sendEmail = true;
        //        } //
        //        //
        //        
        //        else if (List.get(0).equals("OVERRIDE BLOCKADES") && List.size() == 2) {
        //            if (OverR) {
        //                MiniMessage.add("Blockades already overridden");
        //            } else {
        //                if (List.get(1).equals(OVERRIDE)) {
        //                    MiniMessage.add("Blockades overridden. Access granted.");
        //                    OverR = true;
        //                }
        //            }
        //            MiniMessage.add("==> Done");
        //            sendEmail = true;
        //        } //
        //        //
        //        else if (List.get(0).equals("WRITE TO CLIPBOARD") && List.size() == 2) {
        //            if (OverR) {
        //                MiniMessage.add("Write To Clipboard: " + List.get(1));
        //                FolderC.writeToClipboard(List.get(1));
        //                MiniMessage.add("==> Done");
        //                sendEmail = true;
        //            } else {
        //                MiniMessage.add("Command Blocked");
        //            }
        //        } //
        //        //
        //        else if (List.get(0).equals("CLEAR LOG") && List.size() == 1) {
        //            if (OverR) {
        //                MiniMessage.add("Log Cleared");
        //                FolderC.writeToFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "SystemFiles" + System.getProperty("file.separator") + "Log.txt", "", false);
        //                sendEmail = true;
        //            } else {
        //                MiniMessage.add("Command Blocked");
        //            }
        //        } //
        //        //
        else {
            MiniMessage.add("Command: \"" + Convert.arrayToString(List).replaceFirst("[\r\n]", " ").replaceAll("[\r\n]", " ") + "\" is not recognized.");
            sendEmail = true;
        }
        return MiniMessage;

    }

    public static ArrayList<String> ShowCont() {
        ArrayList temp = new ArrayList();
        for (int i = 0; i < Mail.size(); i++) {
            temp.add(i + ") " + Mail.get(i).toString());
        }
        return temp;
    }
    static String lastCB = "";
    static Boolean notAgain = true;

    public static void CBTctrl(String currCB) {
        if (!currCB.isEmpty() && !currCB.equals(lastCB)) {
            String results;
            boolean tempSW = ShowWork[0];
            ShowWork[0] = false;
            if (currCB.contains("\n")) {
                results = Convert.arrayToString(cleanResults(Convert.stringToArray(MessageMaker(Convert.stringToArray(currCB.replaceAll(" ", "#").replaceAll("_", " ").replaceAll("\n", "#\n") + "#", "\n")), "\n")));
            } else {
                results = Convert.arrayToString(CommandProcessor(currCB.replaceAll(" ", "#").replaceAll("_", " ") + "#"));
            }
            ShowWork[0] = tempSW;
            if (OtoFile) {
                FolderC.writeToFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "SystemFiles" + System.getProperty("file.separator") + "Log.txt", "\n\n" + Time.getInstant() + results, true);
            }
            if (OLeet) {
                results = Convert.arrayToString(Encryption.Leet(Convert.stringToArray(results, "\n")), "\n");
            }
            if (!results.contains("is not recognized.")) {
                CBTresults(results);
                lastCB = currCB;
                if (SayCBT) {
                    Convert.say(results);
                }
                notAgain = true;
            } else {
                if (notAgain) {
                    String temp = "CBT Error" + results;
                    CBTresults(temp);
                    if (SayCBT) {
                        Convert.say(temp);
                    }
                    notAgain = false;
                }
            }
        }
    }

    public static MailBox temp(String first, String last) {
        MailBox MB = new MailBox(first + " " + last, StartUp.IDGen(), first + last + "@letu.edu");
        return MB;
    }

    public static void CBTresults(String inpt) {
        FolderC.writeToFile(System.getProperty("user.dir") + System.getProperty("file.separator") + "CBT Files" + System.getProperty("file.separator") + inpt.substring(inpt.length() - 4) + ".txt", inpt, false);
        FolderC.run(System.getProperty("user.dir") + System.getProperty("file.separator") + "CBT Files" + System.getProperty("file.separator") + inpt.substring(inpt.length() - 4) + ".txt");
    }

    public static ArrayList<String> cleanResults(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Integer temp = Time.getYear();
            if (arr.get(i).contains("ID: ") || arr.get(i).contains("*****") || arr.get(i).contains(temp.toString())) {
                arr.remove(i);
                i--;
            }
        }
        return arr;
    }
    static Timer DittoTime;

    public static void startDittoT() {
        //Begins a clock that will initialize email scans every 10 seconds
        DittoTime = new Timer();
        DittoTime.scheduleAtFixedRate(new DittoT(), 0, 10000);

    }

    static class DittoT extends TimerTask {
        //Obtains commands from users email account

        ArrayList<String> Data;
        int i = 0;

        @Override
        public void run() {
            i++;

            Data = (User.readEmail(User.ID));
            for (i = 0; i < Data.size(); i++) {
                if (Data.get(i).contains("\n")) {
                    Data.add(i + 1, Data.get(i).substring(Data.get(i).indexOf("\n") + 1));
                    Data.set(i, Data.get(i).substring(0, Data.get(i).indexOf("\n")));

                }
            }
            if (!Data.isEmpty()) {

                System.out.println("Incoming Message from" + Data.get(1).substring(Data.get(1).indexOf(":") + 1) + ":");
                System.out.println(Convert.arrayToString(Data));
                System.out.println("Generating Response...");
                String temp = MessageMaker(Data);
                System.out.println(temp);

                if (sendEmail) {
                    Emailer(User, Mail.get(1), temp);
                }
                System.out.println("Enter Command:");
            }

            Data.clear();
            if ((Time.getSec() - iat) % 20 == 0) {
                resetCheckPoints();
            }
            if (update) {
                FolderC.run(System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf(System.getProperty("file.separator")) + 1).substring(0, System.getProperty("user.dir").lastIndexOf(System.getProperty("file.separator")) + 1) + "Update" + System.getProperty("file.separator") + "Update.jar");
                Alive = false;
                update = false;
            }
            if (!Alive) {
                System.exit(0);
            }
        }
    }
    static Timer LocalControlTime;

    public static void startLocalControlT() {
        //Begins a timer that will check for GUI code every 100 milliseconds  see switchToLocal()
        LocalControlTime = new Timer();
        LocalControlTime.scheduleAtFixedRate(new LocalControlT(), 0, 100);

    }

    static class LocalControlT extends TimerTask {
        //

        @Override
        public void run() {
            switchToLocal();
        }
    }
    static Timer MouseTime;

    public static void startMouseT() {
        //Begins a timer that will return the mouse to a set position every 100 milliseconds.  see mouseLock()
        MouseTime = new Timer();
        MouseTime.scheduleAtFixedRate(new MouseT(), 0, 100);

    }

    static class MouseT extends TimerTask {

        @Override
        public void run() {
            if (mouseLock) {
                Control.jump_mouse(tarx, tary);
            }

        }
    }
    static Timer ClipBoardctrl;

    public static void startCBctrlT() {
        //Begins a timer that will check the clipboard for commands every 100 milliseconds.  see CBTctrl()
        ClipBoardctrl = new Timer();
        ClipBoardctrl.scheduleAtFixedRate(new CB(), 0, 1000);

    }
    public static boolean noRepeat = true;
    public static boolean notFirst = true;

    static class CB extends TimerTask {

        @Override
        public void run() {
            if (CBT) {
                CBTctrl(FolderC.pasteFromClipboard());
                noRepeat = true;
                notFirst = false;
            } else if (!notFirst && noRepeat) {
                QuantumArray temp = FolderC.getFiles(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "CBT Files" + System.getProperty("file.separator")), 0, 1);
                ArrayList<String> arr = Convert.stringToArray(temp.toString(), "\n");
                for (int i = 1; i < arr.size(); i++) {
                    FolderC.deleteFile(arr.get(0) + arr.get(i).substring(arr.get(i).length() - 8));
                }
                noRepeat = false;
            }
        }
    }
}
