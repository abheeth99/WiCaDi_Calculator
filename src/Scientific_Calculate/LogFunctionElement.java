
package Scientific_Calculate;


public class LogFunctionElement extends FunctionElement {
    @Override
    public String toString() {
        FormulaElement argument_x = this.getArglist().get(0);
        return "ln(" + argument_x.toString() + ")";
    }

    @Override
    public void addarg(FormulaElement argument) {
        if (this.getArglist().size() < 1) {
            super.addarg(argument);
        }
    }

    @Override
    public double evaluate() {
        return Math.log(getArglist().get(0).evaluate());

    }
}
