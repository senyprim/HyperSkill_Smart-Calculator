package calculator;

import java.util.regex.Pattern;

public class Calculator {
    private static final Pattern pattern= Pattern.compile("^\\s*[-+]?(\\d+|((\\d+\\s*[\\+-]\\s*)+\\d+))\\s*$");
    public final String inputExpression;
    public final String expression;
    public final boolean isValid;

    public Calculator(String expression){
        this.inputExpression=expression;
        this.expression =updateExpression(this.inputExpression);
        this.isValid=isValidExpression(this.expression);

    }
    private static String updateExpression(String expression){
        return expression
                .replaceAll("\\++","+")//Несколько + в один
                .replaceAll("(---)+","-")//Три минуса в один
                .replaceAll("--","+")//Два минуса в плюс
                .replaceAll("(\\+-)|(-\\+)","-");// +- или -+ в минус
    }
    public static boolean isValidExpression(String expression){
        return pattern.matcher(updateExpression(expression)).matches();
    }

    public int calculate(){
        String[] terms=this.expression.split("\\s+");
        int result=0;
        String operation="+";
        for(int i=0;i< terms.length;i++){
            if (terms[i].equals("+") || (terms[i].equals("-"))){
                operation=terms[i];
            }
            else{
                int number=Integer.parseInt(terms[i]);
                result=result+(operation.equals("+")?number:-number);
            }
        }
        return result;
    }
}
