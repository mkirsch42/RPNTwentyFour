package com.mkirsch42.rpn;

import java.util.ArrayList;

// Represents an RPN calculator
public class RPN
{

	// The stack of numbers to be operated on (X is index 0)
	ArrayList<Number> stack;
	
	/**
	 * Constructs an RPN object with an empty stack
	 */
	public RPN()
	{
		stack = new ArrayList<Number>();
	}

	/**
	 * Pushes a number to the stack in index 0 and moves the rest of the stack up
	 * @param x The number to push to the stack
	 */
	public void push(Number x)
	{
		stack.add(0, x);
	}
	
	/**
	 * Performs a given operation on the first two numbers in the stack.
	 * @param op The character representing the desired operation. Must represent one RPNOperation enum object.
	 */
	public void op(char op)
	{
		stack.add(0, RPNOperation.op(op).run(stack.remove(0), stack.remove(0)));
	}
	
	/**
	 * Performs a given operation on the first two numbers in the stack.
	 * @param op The RPNOperation object to be applied to the first two numbers on the stack.
	 */
	public void op(RPNOperation op)
	{
		stack.add(0, op.run(stack.remove(0), stack.remove(0)));
	}
	
	/**
	 * Constructs a String representation of the stack in LIFO order, each number separated by commas.
	 */
	public String toString()
	{
		String str = "";
		for(Number n : stack)
		{
			str = str + n.toString() + ",";
		}
		return str;
	}
	
	/**
	 * Gets the X value of the calculator (stack index 0)
	 * @return The value of X
	 */
	public Number getX()
	{
		return stack.get(0);
	}
}
