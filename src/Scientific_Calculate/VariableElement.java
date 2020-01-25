
package Scientific_Calculate;


public class VariableElement extends FormulaElement {
    private String variableName;
    private double vValue;
    private boolean grouned = false;

    public void setNumericValue(double numericValue) {
        this.vValue = numericValue;
        this.grouned = true;
    }

    public double getNumericValue() {
        return vValue;
    }

    public VariableElement(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        return this.getVariableName();
    }

    @Override
    public void setVariableValue(String varName, double value) {
        if (this.getVariableName().equals(varName)) {
            this.setNumericValue(value);
        }
    }

    @Override
    public boolean isFullyGrounded() {
        return grouned;
    }
    
    @Override
    public double evaluate() {
        return this.getNumericValue();

    }
}
