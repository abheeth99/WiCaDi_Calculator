/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scientific_Calculate;

/**
 *
 * @author abheeth kotelawala
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
 
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
 
//extends JComponent, so it's a custom element
public class GraphComponent extends JComponent{
 
	//unit: how many pixels make 1 unit
	//invRes: resolution
	int unit = 50, invRes=10;
	String equation = "y=x";
	//for errors
	boolean err=false;
	
	public GraphComponent() {
		//Initialise as any other component
		super();
		//Set size - otherwise it will remain 0x0 pixels
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		super.setPreferredSize(new Dimension((int)(screenSize.getHeight()*0.6), (int)(screenSize.getHeight()*0.6)));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		/*Cleared areas don't become transparent,
		 *but they are filled with the background color*/
		g2d.setBackground(Color.white);
		//Get this component's dimensions
		int width = super.getWidth();
		int height = super.getHeight();
		//Clear the whole area
		g2d.clearRect(0, 0, width, height);
		//Paint the axes
		//Set line width to 2 pixels
		g2d.setStroke(new BasicStroke(2));
		//Set line color
		g2d.setColor(Color.blue);
		//Draw the y axis
		g2d.drawLine(width/2, 0, width/2, height);
		//Draw the x axis
		g2d.drawLine(0, height/2, width, height/2);
		//Draw the background grid
		//Set line stroke to dashed
		g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[] {10, 2}, 0));
		//Vertical lines: from the center to the right
		for(int i=width/2+unit; i<width; i+=unit)
			g2d.drawLine(i, 0, i, height);
		//Vertical lines: from the center to the left
		for(int i=width/2-unit; i>0; i-=unit)
			g2d.drawLine(i, 0, i, height);
		//Horizontal lines: from the center to the bottom
		for(int i=height/2+unit; i<height; i+=unit)
			g2d.drawLine(0, i, width, i);
		//Horizontal lines: from the center to the top
		for(int i=height/2-unit; i>0; i-=unit)
			g2d.drawLine(0, i, width, i);
		//Write unit numbers on the axes
		for(int i=width/2, i2=0; i<width; i+=unit, i2++)
			g2d.drawString(i2+",0", i+5, height/2-5);
		for(int i=width/2-unit, i2=-1; i>0; i-=unit, i2--)
			g2d.drawString(i2+",0", i+5, height/2-5);
		for(int i=height/2+unit, i2=-1; i<height; i+=unit, i2--)
			g2d.drawString("0,"+i2, width/2+5, i-5);
		for(int i=height/2-unit, i2=1; i>0; i-=unit, i2++)
			g2d.drawString("0,"+i2, width/2+5, i-5);
		//Draw the function
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.red);
		DrawEquation(equation, g2d, width, height);
	}
	
	private void DrawEquation(String s, Graphics2D g2d, int width, int height) {
		//Divide the two sides of the equation
		String[] sides = s.split("=");
		//If y was on the left side
		if(sides[0].startsWith("y")) {
			//Create a variable to store temporary a previous point
			Point prev = null;
			//Set the resolution
			float res = 1.0f/invRes;
			/*Now we have to convert the Swing's coordinate system to the Cartesian one*/
			//Get the center of the graph
			/*mw and mh are also the lengths of half of each axis*/
			int mw = width/2;
			int mh = height/2;
			//Get the number of units in half horizontal axis
			double mr = (double)mw/unit;
			err=false;
			/*a is the graph's x variable
			  loop from the lowest visible variable and
			  the greatest visible variable
			  increase by resolution*/
			for(double a=-mr; a<=mr; a+=res) {
				double b = stringToOperation(a, sides[1]);
				if(err)
					break;
				/*Convert back to Swing's coordinate system
				 a and b are relative to the unit's value and
				 to the center of the component
				 So first we convert them from units to pixels and
				 then we set them relative to the window's 0*/
				int a2 = (int)(a*unit + mw);
				int b2 = (int)(b*unit + mh);
				/*if we have two points (the previous one and the current one)
				  we can draw a line*/
				if(prev != null)
					g2d.drawLine(prev.x, prev.y, a2, height-b2);
				prev = new Point(a2, height-b2);
			}
		}
		//If x was on the left side
		//Almost the same thing but relative to the y axis
		else if(sides[0].startsWith("x")) {
			Point prev = null;
			float res = 1.0f/invRes;
			int mw = width/2;
			int mh = height/2;
			double mr = (double)mh/unit;
			err=false;
			for(double b=-mr; b<=mr; b+=res) {
				double a = stringToOperation(b, sides[1]);
				if(err)
					break;
				int a2 = (int)(a*unit + mw);
				int b2 = (int)(b*unit + mh);
				if(prev != null)
					g2d.drawLine(prev.x, prev.y, a2, height-b2);
				prev = new Point(a2, height-b2);
			}
		}
	}
	
	private double stringToOperation(double a, String eq) {
		double b=0;
		eq = eq.replace("x", a+"");
		eq = eq.replace("y", a+"");
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    try {
			b = ((Number) engine.eval(eq)).doubleValue();
		} catch (ScriptException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			err = true;
		}
		return b;
	}
	
}

