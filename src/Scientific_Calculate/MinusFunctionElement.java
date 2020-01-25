
package Scientific_Calculate;


public class MinusFunctionElement extends FunctionElement{
     @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " - " + getArglist().get(1).toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double evaluate() {
        return this.getArglist().get(0).evaluate() - getArglist().get(1).evaluate();

    }
}
