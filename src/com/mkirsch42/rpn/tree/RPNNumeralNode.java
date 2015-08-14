package com.mkirsch42.rpn.tree;

import com.mkirsch42.rpn.RPN;

// Inputs a number into the RPN stack
public class RPNNumeralNode extends RPNNode
{
	// The number to input into the RPN stack
	Number n;
	
	/**
	 * Creates a node with a given number
	 * @param num The number to input into the RPN stack
	 */
	public RPNNumeralNode(Number num)
	{
		n = num;
	}

	/**
	 * Pushes the provided number into the RPN stack and recursively calls the child's execute function if there is a child node.
	 */
	@Override
	protected void execute(RPN rpn)
	{
		rpn.push(n);
		if(child!=null)
			child.execute(rpn);
	}

	/**
	 * Calculates the stack size of this node and it's child.
	 */
	@Override
	public int stackSize()
	{
		if(child!=null)
		{
			return child.stackSize()+1;
		}
		return 1;
	}
	
	/**
	 * Compiles a string representing the inputs into the RPN object, in order.
	 */
	public String toString()
	{
		return n.toString() + " " + (child!=null?child.toString():"");
	}
}
