package com.mkirsch42.rpn.tree;

import com.mkirsch42.rpn.RPN;
import com.mkirsch42.rpn.RPNOperation;

// Represents an operation to run on the RPN object.
public class RPNOperationNode extends RPNNode
{

	// The operation to run on the RPN object
	RPNOperation op;
	
	/**
	 * Constructs a node with a given operation.
	 * @param o The operation to run on the RPN object.
	 */
	public RPNOperationNode(RPNOperation o)
	{
		op = o;
	}
	
	/**
	 * Performs the operation on the RPN object then recursively calls its child's execute() function.
	 */
	@Override
	protected void execute(RPN rpn)
	{
		rpn.op(op);
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
			return child.stackSize()-1;
		}
		return -1;
	}

	/**
	 * Compiles a string representing the inputs into the RPN object, in order.
	 */
	public String toString()
	{
		return op.c + " " + (child!=null?child.toString():"");
	}
}
