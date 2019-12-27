package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            String inputText=scanner.nextLine();
            if (inputText.equals("/exit")) {
                System.out.println("Bye!");
                return;
            };
            if (inputText.equals("/help")) {
                System.out.println("The program calculates the sum of numbers");
                continue;
            };
            if (inputText.equals("")){
                continue;
            }

            String[] numbers=inputText.split("\\s+");
            int result=0;
            for(int i=0;i<numbers.length;i++){
                result+=Integer.parseInt(numbers[i]);
            }

            System.out.println(result);
        }
    }
}
