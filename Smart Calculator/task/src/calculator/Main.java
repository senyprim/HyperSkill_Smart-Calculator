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
            if (inputText.startsWith("/")){
                System.out.println("Unknown command");
                continue;
            }
            Calculator calc=new Calculator(inputText);
            if (!calc.isValid) {
                System.out.println("Invalid expression");
            }
            else {
                System.out.println(calc.calculate());
            }
        }
    }
}
