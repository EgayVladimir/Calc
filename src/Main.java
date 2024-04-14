import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        class Solution{
            static int getPriority (String operator){
                int priority = 0;
                if (operator.equals("+") || operator.equals("-")){
                    return 1;
                }
                if(operator.equals("*") || operator.equals("/")){
                    return 2;
                }
                return priority;
            };// getPriority

            static ArrayList<String> toPostFix(ArrayList<String> someArrayList){
                ArrayList<String> allowedNum = new ArrayList<String>(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
                ArrayList<String> allowedOp = new ArrayList<String>(List.of("+", "-", "*", "/"));
                ArrayList<String> Stack = new ArrayList<String>();
                ArrayList<String> postfix = new ArrayList<String>();

                for (String token : someArrayList){
                    if(allowedNum.contains(token)){
                        postfix.add(token);

                    }if (allowedOp.contains(token)){
                        while (!Stack.isEmpty() && getPriority(Stack.get(Stack.size() - 1)) >= getPriority(token)) {
                            postfix.add(Stack.remove(Stack.size() - 1));
                        }
                        Stack.add(token);
                    }
                    if(!allowedOp.contains(token) && !allowedNum.contains(token)) {
                        System.out.println("Incorrect input");
                        System.exit(0);
                    }
                };

                while (!Stack.isEmpty()) {
                    postfix.add(Stack.remove(Stack.size() - 1));
                }
                return postfix;

            };

            static int Evaluate (ArrayList<String> someArray){
                ArrayList<String> allowedOp = new ArrayList<String>(List.of("+", "-", "*", "/"));
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < someArray.size(); i++){
                    String s = someArray.get(i);
                    if (allowedOp.contains(s)){
                        int val_1 = stack.pop();
                        int val_2 = stack.pop();
                        switch (s) {
                            case "+":
                                stack.push(val_2 + val_1);
                                break;
                            case "-":
                                stack.push(val_2 - val_1);
                                break;
                            case "/":
                                stack.push(val_2 / val_1);
                                break;
                            case "*":
                                stack.push(val_2 * val_1);
                                break;
                        }
                    }else {
                        stack.push(Integer.parseInt(s));
                    }
                }
                return stack.pop();
            };
        }// Solution


        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter evaluation:");
            String inputList = sc.nextLine();
            ArrayList<String> input = new ArrayList(Arrays.asList(inputList.split(" ")));
            System.out.println(Solution.Evaluate(Solution.toPostFix(input)));
        }




    }
}