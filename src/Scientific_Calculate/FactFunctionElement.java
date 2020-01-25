
package Scientific_Calculate;


public class FactFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "!";
    }

    @Override
    public double evaluate() {
        double arg = this.getArglist().get(0).evaluate();
        int fact = 1;
        int no = (int) Math.round(arg);
        
        while(no>0){
            fact = fact*no;
            no--;
        }
        return fact;

    }
}
