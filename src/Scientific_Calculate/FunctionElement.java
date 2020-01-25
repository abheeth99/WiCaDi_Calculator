
package Scientific_Calculate;

import java.util.*;

public class FunctionElement extends FormulaElement {
    private Vector<FormulaElement> argumentList;

    public Vector<FormulaElement> getArglist() {
        return argumentList;
    }

    public FunctionElement() {
        this.argumentList = new Vector<FormulaElement>();
    }

    public void addarg(FormulaElement argument) {
        this.argumentList.add(argument);
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void setVariableValue(String varName, double value) {
        this.argumentList.stream().forEach((argumentList1) -> {
            argumentList1.setVariableValue(varName, value);
        });
    }

    @Override
    public boolean isFullyGrounded() {
        for (FormulaElement argumentList1 : argumentList) {
            if (!argumentList1.isFullyGrounded()) {
                return false;
            }
        }
        return true;
    }
}
