import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrainfuckCompailer {


    public boolean compile(Brainfuck brainfuck){
        if(lexicalAnalysis(brainfuck) && grammar(brainfuck)) {
            optimization(brainfuck);
            return true;
        }
        else return false;
    }

    private boolean lexicalAnalysis(Brainfuck brainfuck){
        Pattern pattern = Pattern.compile("[^><+-.\\[\\]]");
        Matcher matcher = pattern.matcher(brainfuck.getRowString());
        if(matcher.find())return false;
        else return true;
    }

    private boolean grammar(Brainfuck brainfuck){
        char arr [] = brainfuck.getRawCode();
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case '[': {
                    stack.push(arr[i]);
                    break;
                }
                case ']': {
                    if (stack.size() != 0 && stack.peek() == '[')
                        stack.poll();
                    else {
                        return false;
                    }
                    break;
                }
            }
        }

        if(stack.size()==0)
            return true;
        else
            return false;
    }

    private void optimization(Brainfuck brainfuck){

        char arr [] = brainfuck.getRawCode();
        ArrayList<Character>  optimizationArray = new ArrayList<>();
        ArrayList<Integer>  countOfOperation = new ArrayList<>();


        for (int i = 0; i < arr.length; i++) {
            char operation = arr[i];
            int count = 1;
            if(operation!='[' && operation!=']')
                while (i != arr.length - 1 && operation == arr[i + 1]) {
                    count++;
                    i++;
                }

            optimizationArray.add(operation);
            countOfOperation.add(count);
        }

        brainfuck.setOptimizationCode(optimizationArray);
        brainfuck.setCountOfOperation(countOfOperation);

    }

}
