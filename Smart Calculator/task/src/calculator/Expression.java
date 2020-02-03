package calculator;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.regex.Pattern;

public class Expression {
    private static Map<String, Integer> operationPriority= Map.of("+",1,"-",1,"*",2,"/",2,"^",3);

    public static boolean isDigit(String operand){
        return Pattern.matches("\\d+",operand);
    }

    public static boolean isBinaryOperation(String operand){return operationPriority.containsKey(operand);}

    public static int getOperationPriority(String operation){return operationPriority.get(operation);}

    private static BigInteger getOperationResult(String operation, BigInteger left, BigInteger right){
        BigInteger result= BigInteger.ZERO;
        switch (operation){
            case "+":result=left.add(right);break;
            case "-":result=left.subtract(right);break;
            case "*":result=left.multiply(right);break;
            case "/":result=left.divide(right);break;
            case "^":result=left.pow(right.intValue());break;
            default:throw new IllegalArgumentException(CommandShell.INVALID_EXP);
        }
        return result;
    }

    private static void getOperationResult(String operation, Deque<BigInteger> stack){
        BigInteger right=stack.pop();
        BigInteger left=stack.pop();
        stack.push(getOperationResult(operation,left,right));
    }
    public static BigInteger getResultExpression(String expression, Variables variables){
        return getResultParseExpression(Parser.parseExpression(Parser.updateExpression(expression)),variables);
    }

    private  static BigInteger getResultParseExpression(String[] parseExpression, Variables _variable){
        Deque<BigInteger> result=new ArrayDeque<>();
        Deque<String> stack=new ArrayDeque<>();
        try {
            for (String token : parseExpression) {
                if (isDigit(token)) result.push(new BigInteger(token));
                else if (token.equals("(")) stack.push(token);
                else if (token.equals(")")) {
                    while (!stack.peek().equals("("))
                        getOperationResult(stack.pop(), result);
                    stack.pop();
                } else if (isBinaryOperation(token)) {
                    if (!stack.isEmpty())
                        if (!stack.peek().equals("(") && getOperationPriority(stack.peek()) >= getOperationPriority(token)) {
                            getOperationResult(stack.pop(), result);
                        }

                    stack.push(token);
                } else result.push(_variable.getVariable(token));
            }
            while (!stack.isEmpty()) getOperationResult(stack.pop(), result);
            if (result.size() != 1) throw new IllegalArgumentException("Something wrong");
            return result.pop();
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (Exception e){
            throw new IllegalArgumentException(CommandShell.INVALID_EXP);
        }
    }

}
