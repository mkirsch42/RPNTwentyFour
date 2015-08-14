package com.mkirsch42.rpn.tree;

import com.mkirsch42.rpn.RPN;

// Allows for a modular representation of RPN inputs
public abstract class RPNNode
{
	// The child node
	protected RPNNode child;
	
	/**
	 * Adds a child to the bottom of the tree
	 * @param c The child node to add
	 */
	public void addChild(RPNNode c)
	{
		if(child!=null)
			child.addChild(c);
		else
			child = c;
	}
	
	/**
	 * Executes the inputs from this node to the bottom of the tree
	 * @return The X value of the RPN stack after executing all inputs
	 */
	public Number execute()
	{
		RPN rpn = new RPN();
		execute(rpn);
		return rpn.getX();
	}
	
	/**
	 * Determines whether this node has a child
	 * @return true if this node has a child, false if it does not
	 */
	public boolean hasChild()
	{
		return child != null;
	}
	
	/**
	 * Removes the bottom-most node from the tree
	 */
	public void pop()
	{
		if(child.hasChild())
		{
			child.pop();
		}
		else
		{
			child = null;
		}
	}
	
	/**
	 * Calculates the size of the RPN stack when the tree is executed
	 * @return The size of the RPN stack after the tree is executed
	 */
	public abstract int stackSize();
	
	/**
	 * Inputs this node into the RPN object then recursively calls the next node.
	 * Should be called from execute().
	 * @param rpn The RPN object the tree is executing on/
	 */
	protected abstract void execute(RPN rpn);
}
