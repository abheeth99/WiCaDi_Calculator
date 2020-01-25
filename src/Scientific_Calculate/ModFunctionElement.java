
package Scientific_Calculate;


public class ModFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " mod " + getArglist().get(1).toString();
    }

    @Override
    public double evaluate() {
        return this.getArglist().get(0).evaluate() % getArglist().get(1).evaluate();

    }
}
