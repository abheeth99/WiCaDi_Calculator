
package Scientific_Calculate;


public class SqrRootFunctionElement extends FunctionElement{
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "^0.5";
    }

    @Override
    public double evaluate() {
        return Math.pow(this.getArglist().get(0).evaluate(), 0.5);

    }
}
