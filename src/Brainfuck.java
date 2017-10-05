import java.util.ArrayList;

public class Brainfuck {

    private String rowString;
    private char [] rawCode;
    private ArrayList<Character> optimizationCode;
    private ArrayList<Integer> countOfOperation;

    public Brainfuck(String code) {
        this.rowString = code;
        this.rawCode = code.toCharArray();
    }

    public char[] getRawCode() {
        return rawCode;
    }

    public ArrayList<Character> getOptimizationCode() {
        return optimizationCode;
    }

    public void setOptimizationCode(ArrayList<Character> optimiationCode) {
        this.optimizationCode = optimiationCode;
    }

    public ArrayList<Integer> getCountOfOperation() {
        return countOfOperation;
    }

    public void setCountOfOperation(ArrayList<Integer> countOfOperation) {
        this.countOfOperation = countOfOperation;
    }

    public String getRowString() {
        return rowString;
    }
}
