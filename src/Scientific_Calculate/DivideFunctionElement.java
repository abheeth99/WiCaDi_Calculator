
package Scientific_Calculate;


public class DivideFunctionElement extends FunctionElement{
     @Override
    public String toString() {
        return this.getArglist().get(0).toString() + " / " + getArglist().get(1).toString();
    }

//    @Override
//    public void setVariableValue(String varName, double value) {
//        if (this.getArglist().size() > 0) {
//            this.getArglist().get(0).setVariableValue(varName, value);
//        }
//    }

    @Override
    public double evaluate() {
        System.out.println(this.getArglist().get(1).evaluate());
        return this.getArglist().get(0).evaluate() / getArglist().get(1).evaluate();

    }
}
