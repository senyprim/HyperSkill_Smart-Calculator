package calculator;

import java.util.Scanner;

public class CommandShell {
    public static final String WRONG_ASSIGN="Invalid assignment";
    public static final String INVALID_EXP="Invalid expression";
    public final Variables _variables;

    public CommandShell(){this._variables =new Variables();}

    public String executeCommand(String command){
        String result=null;
        if (command.trim().equals("")) return null;
        try {
            if (command.contains("=")) {
                String[] splitAssignOperation = command.split("=");
                if (splitAssignOperation.length > 2) {
                    throw new IllegalArgumentException(WRONG_ASSIGN);
                }
                String variable = splitAssignOperation[0].replace(" ", "");
                String expression = splitAssignOperation[1].trim();
                _variables.setVariable(variable, Expression.getResultExpression(expression,_variables));
            }
            else result= Expression.getResultExpression(command,_variables).toString();
        }
        catch (Exception e){
            result=e.getMessage();
        }
        finally {
            return result;
        }
    }

    public void run(Scanner scanner){
        String answer=null;
        while(true){
            String command=scanner.nextLine();
            if (command.equals("/exit")) {
                System.out.print("Bye!");
                break;
            }
            else if (command.startsWith("/")){
                System.out.println("Unknown command");
            }
            else {
                answer=executeCommand(command);
                if (answer!=null) System.out.println(answer);
            }
        }
    }

}