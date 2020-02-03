package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Variables {
    private static final String WRONG_NAME="Invalid identifier";
    private static final String WRONG_ASSIGN="Invalid assignment";
    private static final String UNKNOWN_VAR ="Unknown variable";

    private Map<String, BigInteger> _variables;

    public Variables(){
        _variables =new HashMap<>();
    }

    private boolean isValidName(String name){
        return Pattern.matches("[A-Za-z]+",name);
    }

    public void setVariable(String name, BigInteger value){
        if (!isValidName(name)) throw new IllegalArgumentException(WRONG_NAME);
        _variables.put(name,value);
    }

    public BigInteger getVariable(String name){
        if (!isValidName(name)) throw new IllegalArgumentException(WRONG_ASSIGN);
        if (!_variables.containsKey(name)) throw new IllegalArgumentException(UNKNOWN_VAR);
        return _variables.get(name);
    }
}