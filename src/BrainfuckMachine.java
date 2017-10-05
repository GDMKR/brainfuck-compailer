import java.util.ArrayList;
import java.util.LinkedList;

public class BrainfuckMachine {
    private int pointer = 0;
    private static char [] array;
    private LinkedList<Integer> stack = new LinkedList<>(); // for left brackets
    
    public BrainfuckMachine(int n ) {
        this.array = new char[n];
    }
    
    void execute(Brainfuck brainfuck){

        BrainfuckCompailer brainfuckCompailer = new BrainfuckCompailer();

        if(brainfuckCompailer.compile(brainfuck)){
            ArrayList<Character> optimizationCode = brainfuck.getOptimizationCode();
            ArrayList<Integer> countOfOperation = brainfuck.getCountOfOperation();
            int c = 0;
            for(int i = 0; i < optimizationCode.size(); i++) {
                switch (optimizationCode.get(i)){
                    case '>':
                        pointer +=countOfOperation.get(i);
                        break;
                    case '<':
                        pointer -=countOfOperation.get(i);
                        break;
                    case '+':
                        array[pointer]+=countOfOperation.get(i);
                        break;
                    case '-':
                        array[pointer]-=countOfOperation.get(i);
                        break;
                    case '.':
                        for (int j = 0; j < countOfOperation.get(i); j++) {
                            System.out.print(array[pointer]);
                        }
                        break;
                    case '[':

                    if(array[pointer] != 0)
                              stack.push(i);
                    else{
                        i++;
                        while(c > 0 || optimizationCode.get(i) != ']'){
                            if(optimizationCode.get(i)=='[')
                                c++;
                            if(optimizationCode.get(i)==']')
                                c--;
                            i++;
                        }
                    }
                    break;
                    case ']':
                        if(array[pointer] != 0) {
                            i = stack.peek();
                        }
                        else
                            stack.pop();
                        break;
                }
            }

        }
        else{
            throw new IllegalArgumentException("The code was not compiled.");
        }
    }

}
