
package Scientific_Calculate;

public class PercentageFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        return this.getArglist().get(0).toString() + "%";
    }

    @Override
    public double evaluate() {
        return this.getArglist().get(0).evaluate()/100;

    }
}
