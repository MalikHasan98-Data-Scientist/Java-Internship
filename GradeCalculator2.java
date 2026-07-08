import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();
        sc.nextLine();                              // clear the buffer

        String[] subjects = new String[n];
        int[] marks = new int[n];

        // get subject names
        System.out.println("\nEnter the names of the subjects:");
        for(int i = 0; i < n; i++) {
            System.out.print("Subject " + (i+1) + " name: ");
            subjects[i] = sc.nextLine();
        }
        System.out.println("\nEnter marks obtained (out of 100):");
        int total = 0;

        for(int i = 0; i < n; i++) {
            System.out.print("Marks for " + subjects[i] + ": ");
            marks[i] = sc.nextInt();

            // input error handling
            if(marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input! Marks must be between 0 and 100.");
                i--;              // repeat this iteration
                continue;
            }
            total += marks[i];
        }
        // calculate percentage
        double avg = (double) total / n;        // typecasting

        char grade;
        String remark;

        if(avg >= 90) {
            grade = 'A';
            remark = "Excellent";
        } else if(avg >= 80) {
            grade = 'B';
            remark = "Very Good";
        } else if(avg >= 70) {
            grade = 'C';
            remark = "Good";
        } else if(avg >= 60) {
            grade = 'D';
            remark = "Satisfactory";
        } else if(avg >= 50) {
            grade = 'E';
            remark = "Pass";
        } else {
            grade = 'F';
            remark = "Fail";
        }
        // print report card
        System.out.println("\n==============================");
        System.out.println("        REPORT CARD          ");
        System.out.println("==============================");

        for(int i = 0; i < n; i++) {
            System.out.printf("%-15s : %d/100\n", subjects[i], marks[i]);
        }
        System.out.println("-----------------------------");
        System.out.println("Total Marks: " + total + "/" + (n * 100));
        System.out.printf("Percentage : %.2f%%\n", avg);
        System.out.println("Grade      : " + grade);
        System.out.println("Remark     : " + remark);
        System.out.println("-----------------------------");

        sc.close();
    }
}