package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String updateExpression(String expression){
        return expression
                .replaceAll("\\++","+")//Несколько + в один
                .replaceAll("(---)+","-")//Три минуса в один
                .replaceAll("--","+")//Два минуса в плюс
                .replaceAll("(\\+-)|(-\\+)","-")// +- или -+ в минус
                .replaceAll("^-","0-");// +- или -+ в минус
    }

    public static String[] parseExpression(String expression){
        expression=updateExpression(expression);
        List<String> result=new ArrayList<>();

        Pattern pattern= Pattern.compile("([0-9]+|\\++|-+|\\*+|\\/+|\\^+|[A-Z0-9]+|\\)|\\()", Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(expression);
        while (match.find()){
            result.add(match.group(1));
        }
        return result.toArray(new String[0]);
    }
}
