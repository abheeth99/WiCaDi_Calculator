
package Scientific_Calculate;


public class TanFunctionElement extends FunctionElement{
    @Override
    public String toString() {
        FormulaElement argument_x = this.getArglist().get(0);
        return "tan(" + argument_x.toString() + ")";
    }

    @Override
    public void addarg(FormulaElement argument) {
        if (this.getArglist().size() < 1) {
            super.addarg(argument);
        }
    }

    @Override
    public double evaluate() {
        return Math.tan(getArglist().get(0).evaluate());

    }
}
