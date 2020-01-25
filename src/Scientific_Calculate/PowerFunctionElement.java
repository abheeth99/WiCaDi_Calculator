
package Scientific_Calculate;


public class PowerFunctionElement extends FunctionElement{
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " ^ " + getArglist().get(1).toString();
    }

    @Override
    public double evaluate() {
        return Math.pow(this.getArglist().get(0).evaluate(), getArglist().get(1).evaluate());

    }
}
