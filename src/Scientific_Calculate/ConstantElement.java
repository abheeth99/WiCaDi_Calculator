
package Scientific_Calculate;


public class ConstantElement extends FormulaElement {
    private double value;

    public double getValue() {
        return value;
    }

    public ConstantElement(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
    @Override
    public double evaluate() {
        return this.getValue();

    }
}
