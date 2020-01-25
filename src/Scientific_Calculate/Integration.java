
package Scientific_Calculate;



import java.io.*;
import java.util.*;  



class Main{
    
   
    
    public String differentiate(float coefficient, int power, boolean ispositive){
        return (((ispositive&&power>0)?("+"):(power<0&&!ispositive)?("+"):("-"))+
                ((coefficient*power<0) ? (coefficient/power*-1):(coefficient/(power+1))+
                "x^"+(power+1)+" "));
    }
   
    public Main(){
       try{
           BufferedReader in;
           in=new BufferedReader (new InputStreamReader (System.in));
           StringTokenizer st =new StringTokenizer(in.readLine());
           //int count = st.countTokens();
           boolean firsttoken = true;
           String output = "y'=";
           
           //System.out.println('0'+" "+'9');
           
           while(st.hasMoreTokens()){
               boolean ispositive;
               int power;
               int coefficient;
           
           
              String input = st.nextToken();
              int endofcoefficient=0;
           
           if (firsttoken){
                if(input.charAt(0)== '-'){
                    ispositive=false;
                }
                else{
                    ispositive=true;
                }
                
               
                
                //firsttoken=false;
                }else{
               if(input.charAt(0)=='+')
                   ispositive=true;
               else
                   ispositive=false;
               
           }
           for(int i=((ispositive && firsttoken)?(0):(1));i<input.length();i++) {
                    //System.out.println(input.charAt(i));
                    if(input.charAt(i)>'9'||input.charAt(i)<'0'){
                        endofcoefficient=i-1;
                        break;
                    }
                        
                    }
                if(endofcoefficient != -1){
                   // System.out.println(endofcoefficient);
                coefficient = Integer.parseInt(input.substring(((ispositive && firsttoken)?(0):(1)), endofcoefficient + 1));
                power=Integer.parseInt(input.substring(endofcoefficient + 3));
                }
                else{
                   coefficient=1;
                   power=Integer.parseInt(input.substring(endofcoefficient + 3));
                }
                
                //System.out.println(coefficient + "x^"+power);
                if(firsttoken)
                    firsttoken=false;
                output += differentiate(coefficient, power,ispositive);
           }
           System.out.println(output);
       }
        catch(IOException e){
            System.out.println("Io:general");
            
        }
    }
}

