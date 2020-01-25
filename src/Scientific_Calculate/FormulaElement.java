
package Scientific_Calculate;

import java.util.*;


public abstract class FormulaElement {
//Assignment 3
    static String csvText = "" ;
    
    @Override
    public String toString() {
        return "";
    }

    public FormulaElement parseFormula(String text) {

        StringTokenizer tokenizer = new StringTokenizer(text, "+-*/^√()!πe% \t", true);

        Vector<Object> vec = new Vector<>();
        while (tokenizer.hasMoreTokens()){
        String temp= tokenizer.nextToken();
        //omitting whitespace
        if(temp==null || temp.trim().isEmpty() == true){
           continue;
        }
        //further splitting tokens like 2x4y and 7cos 
        if(temp.length()>1){
            String[] parts = temp.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            for (String part : parts) {
                //splitting even further to seperate cos and sin from variables
                if(part.length()>1 && part.matches("^(cos||sin|Sin|Cos|tan|Tan|COS|SIN|TAN|mod|Mod|MOD|ln|Ln|LN)$")== false && part.matches("^[a-zA-Z]+$")){
                  //separating cos from strings like xyzcos to store as x,y,z,cos
                  if(part.matches("[a-zA-z]+(cos|Cos)$")){
                    String[] moreParts = part.split("(cos|Cos)$"); 
                    char[] individualChars = moreParts[0].toCharArray();
                    for (char c : individualChars){
                      String s = Character.toString(c);
                      vec.add(s);
                    }
                    vec.add("Cos");
                  }
                  //separating sin from strings like xyzsin to store as x,y,z,sin
                  else if(part.matches("[a-zA-z]+(sin|Sin)$")){
                    String[] moreParts = part.split("(sin|Sin)$"); 
                    char[] individualChars = moreParts[0].toCharArray();
                    for (char c : individualChars){
                      String s = Character.toString(c);
                      vec.add(s);
                    }
                    vec.add("Sin");
                  }
                  //seperating strings containing multiple variables like xyz into x,y,z
                  else{
                    char[] individualChars = part.toCharArray();
                    for (char c : individualChars){
                      String s = Character.toString(c);
                      vec.add(s);
                    }
                  }
                  
                  
                }
                else{
                vec.add(part);
                }
        }
         }
        else{
          vec.add(temp);
        }
              
        }
        //CSV generator
        for(Object o:vec){
          csvText= csvText + o.toString()+",";
        }
        System.out.println(vec);

        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof String) {
                String s = (String) vec.get(i);
                if (s.equals("(")) {
                    vec.remove(i);
                    s = (String) vec.get(i);
                    int count = 1;
                    String out = "";

                    while (!s.equals(")") || count != 1) {
                        switch (s) {
                            case "(":
                                count++;
                                break;
                            case ")":
                                count--;
                                break;
                        }
                        out = out + s;
                        vec.remove(i);
                        s = (String) vec.get(i);
                    }

                    if (i > 0 && vec.get(i - 1) instanceof String) {
                        String p = (String) vec.get(i - 1);
                        if (p.toLowerCase().equals("cos") || p.toLowerCase().equals("sin") || p.toLowerCase().equals("tan")|| p.equals("ln")) {
                            vec.add(i, "(");
                            vec.add(i + 1, parseFormula(out));
                        } else {
                            vec.remove(i);
                            vec.add(i, parseFormula(out));
                        }
                    } else {
                        vec.remove(i);
                        vec.add(i, parseFormula(out));
                    }
                }
            }
        }
     
        
        
        //First pass
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i) instanceof String) {
                String s = (String) vec.get(i);
                if (Character.isDigit(s.charAt(0))) {
                    vec.remove(i);
                    vec.add(i, new ConstantElement(Double.valueOf(s)));
                } else if (Character.isLetter(s.charAt(0)) && !s.toLowerCase().equals("cos") && !s.toLowerCase().equals("sin") && !s.toLowerCase().equals("tan") && !s.toLowerCase().equals("ln") && !s.toLowerCase().equals("mod")) {
                    vec.remove(i);
                    vec.add(i, new VariableElement(s));
                }
            }
        }
        
        for (int i = 1; i < vec.size(); i++) {
            if (vec.get(i).toString().equals("!")) {
                FactFunctionElement fac = new FactFunctionElement();

                fac.addarg((FormulaElement) vec.get(i - 1));

                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, fac);
            }
        }
        
        for (int i = 0; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("√")) {
                SqrRootFunctionElement sqr = new SqrRootFunctionElement();

                sqr.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i);
                vec.remove(i);

                vec.add(i, sqr);
            }
        }
        
        for (int i = 0; i < vec.size() - 3; i++) {
            if (vec.get(i).toString().toLowerCase().equals("cos")) {
                CosineFunctionElement cos = new CosineFunctionElement();

                cos.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i);
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, cos);
            }
            if (vec.get(i).toString().toLowerCase().equals("sin")) {
                SineFunctionElement sin = new SineFunctionElement();

                sin.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, sin);
            }
            if (vec.get(i).toString().toLowerCase().equals("tan")) {
                TanFunctionElement tan = new TanFunctionElement();

                tan.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, tan);
            }
        }
        
        for (int i = 0; i < vec.size() - 3; i++) {
            if (vec.get(i).toString().toLowerCase().equals("ln")) {
                LogFunctionElement ln = new LogFunctionElement();

                ln.addarg((FormulaElement) vec.get(i + 2));

                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, ln);
            }
        }
        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("mod")) {
                ModFunctionElement mod = new ModFunctionElement();
                mod.addarg((FormulaElement) vec.get(i - 1));
                mod.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, mod);
                i--;
            }
        }
        
        for (int i = 1; i < vec.size(); i++) {
            if (vec.get(i).toString().equals("%")) {
                PercentageFunctionElement per = new PercentageFunctionElement();

                per.addarg((FormulaElement) vec.get(i - 1));

                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, per);
            }
        }
        
        for (int i = 0; i < vec.size() - 1; i++) {
            if ((vec.get(i) instanceof VariableElement || vec.get(i) instanceof ConstantElement || vec.get(i) instanceof FunctionElement)
                    && (vec.get(i + 1) instanceof VariableElement || vec.get(i + 1) instanceof ConstantElement || vec.get(i + 1) instanceof FunctionElement)) {

                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, mul);
                i--;
            }
        }



        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("^")) {
                PowerFunctionElement power = new PowerFunctionElement();

                power.addarg((FormulaElement) vec.get(i - 1));
                power.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, power);
                
            }
        }
        

        for (int i = 0; i < vec.size() - 1; i++) {
            if ((vec.get(i) instanceof VariableElement || vec.get(i) instanceof ConstantElement)
                    && (vec.get(i + 1) instanceof VariableElement || vec.get(i + 1) instanceof ConstantElement)) {

                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i); 
                vec.remove(i); 

                vec.add(i, mul);
                i--;
            } else if (vec.get(i).toString().equals("/")) {
                DivideFunctionElement div = new DivideFunctionElement();
                div.addarg((FormulaElement) vec.get(i - 1));
                div.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, div);
                i--;
            }else if (vec.get(i).toString().equals("*")) {
                MultipleFunctionElement mul = new MultipleFunctionElement();
                mul.addarg((FormulaElement) vec.get(i - 1));
                mul.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, mul);
                i--;
            }
        }

        for (int i = 1; i < vec.size() - 1; i++) {
            if (vec.get(i).toString().equals("+")) {
                PlusFunctionElement plu = new PlusFunctionElement();
                plu.addarg((FormulaElement) vec.get(i - 1));
                plu.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, plu);
                i--;
            } else if (vec.get(i).toString().equals("-")) {
                MinusFunctionElement min = new MinusFunctionElement();
                min.addarg((FormulaElement) vec.get(i - 1));
                min.addarg((FormulaElement) vec.get(i + 1));

                vec.remove(i - 1);
                vec.remove(i - 1);
                vec.remove(i - 1);

                vec.add(i - 1, min);
                i--;
            }
        }

        return (FormulaElement) vec.get(0);
    }

    public void setVariableValue(String varName, double value) {
    }
    
    public boolean isFullyGrounded() {
        return true;
    }

    public double evaluate() {
        return 0;
    }
    public String getCSV(){
        return csvText;
}
   

}
