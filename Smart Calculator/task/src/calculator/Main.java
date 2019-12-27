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
            inputText=inputText.replaceAll("\\++","+")
                    .replaceAll("(---)+","-")
                    .replaceAll("--","+")
                    .replaceAll("(\\+-)|(-\\+)","-")
            ;
            String[] term=inputText.split("\\s+");
            int result=0;
            String operation="+";
            for(int i=0;i<term.length;i++){
                if (term[i].equals("+") || (term[i].equals("-"))){
                    operation=term[i];
                }
                else{
                    int number=Integer.parseInt(term[i]);
                    result=result+(operation.equals("+")?number:-number);
                }
            }
            System.out.println(result);
        }
    }
}
