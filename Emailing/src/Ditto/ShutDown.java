/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ditto;

import static Ditto.Ditto.UserName;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Mishko
 */
public class ShutDown {

    public static void ClearData() {
        QuantumArray temp = FolderC.getFiles(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "CBT Files" + System.getProperty("file.separator")), 0, 1);
        ArrayList<String> arr = Convert.stringToArray(temp.toString(), "\n");
        for (int i = 1; i < arr.size(); i++) {
            FolderC.deleteFile(arr.get(0) + arr.get(i).substring(arr.get(i).length() - 8));
        }
    }

    public static void SaveMail() {
        String Mailloc = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf(System.getProperty("file.separator")) + 1) + "SystemFiles" + System.getProperty("file.separator") + "MailBox.txt";
        ArrayList<String> Mailbox = Convert.stringToArray(Ditto.Mail.toString().replaceAll(",", ";").replaceAll("\\[", "").replaceAll("\\]", ""), ";");
        FolderC.writeToFile(Mailloc,"",false);
        for (int i = 1; i < Mailbox.size(); i++) {
            FolderC.writeToFile(Mailloc, Convert.removeFirst(Mailbox.get(i)) + System.getProperty("line.separator"), true);
        }
    }
}
