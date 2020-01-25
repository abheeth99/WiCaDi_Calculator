
package Scientific_Calculate;


public class MultipleFunctionElement extends FunctionElement{
 @Override
    public String toString() {
        FormulaElement argument_01 = this.getArglist().get(0);
        FormulaElement argument_02 = this.getArglist().get(1);

        if (argument_01 instanceof ConstantElement) {
            ConstantElement a = (ConstantElement) argument_01;
            if (argument_02 instanceof ConstantElement) {
                ConstantElement b = (ConstantElement) argument_02;
                return a.getValue() +"*"+ b.getValue();
            } else if (argument_02 instanceof VariableElement) {
                return a.toString() + argument_02.toString();
            } else if (argument_02 instanceof FunctionElement) {
                if (argument_02 instanceof PlusFunctionElement || argument_02 instanceof MinusFunctionElement) {
                    return "(" + a.toString() + ")" + "(" + argument_02.toString() + ")";
                } else {
                    return a.toString() + argument_02.toString();
                }
            }
        } else if (argument_01 instanceof VariableElement) {
            VariableElement a = (VariableElement) argument_01;
            if (argument_02 instanceof ConstantElement) {
                ConstantElement b = (ConstantElement) argument_02;
                return b.toString() + a.toString();
            } else if (argument_02 instanceof VariableElement) {
                return a.toString() + argument_02.toString();
            } else if (argument_02 instanceof FunctionElement) {
                if (argument_02 instanceof PlusFunctionElement || argument_02 instanceof MinusFunctionElement) {
                    return "(" + a.toString() + ")" + "(" + argument_02.toString() + ")";
                } else {
                    return a.toString() + argument_02.toString();
                }
            }
        } else if (argument_01 instanceof FunctionElement) {
            String string_a = argument_01.toString();
            if (argument_01 instanceof PlusFunctionElement || argument_01 instanceof MinusFunctionElement) {
                string_a = "(" + string_a + ")";
            }

            if (argument_02 instanceof ConstantElement) {
                ConstantElement b = (ConstantElement) argument_02;
                return b.toString() + string_a;
            } else if (argument_02 instanceof VariableElement) {
                return string_a + argument_02.toString();
            } else if (argument_02 instanceof FunctionElement) {
                if (argument_02 instanceof PlusFunctionElement || argument_02 instanceof MinusFunctionElement) {
                    return string_a + "(" + argument_02.toString() + ")";
                } else {
                    return string_a + "(" + argument_02.toString() + ")";
                }
            }
        }
        return "";

    }
    @Override
    public double evaluate() {
        System.out.println(this.getArglist().get(1).evaluate());
        return this.getArglist().get(0).evaluate() * getArglist().get(1).evaluate();

    }
}
