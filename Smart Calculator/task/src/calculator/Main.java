package calculator;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandShell shell=new CommandShell();
        shell.run(scanner);
    }
}