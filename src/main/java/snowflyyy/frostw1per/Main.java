package snowflyyy.frostw1per;

import java.util.Scanner;
import snowflyyy.frostw1per.type.pdf;

public class Main {
    public static String filePath;
    public static String message = "cleaned by snowf1ake.net";
    public static void main(String[] args){
        Scanner getFilePath = new Scanner(System.in);
        System.out.println("Enter filepath:");
        filePath = getFilePath.nextLine();
        pdf.stripMDPDF(filePath);

    }
}
