package com.mkirsch42.rpn;

// Represents an operation with a character and a function that inputs two numbers and returns an output.
public enum RPNOperation
{
	PLUS('+', new RPNRunnable(){
		public Number execute(Number x, Number y)
		{
			return new Double(y.doubleValue()+x.doubleValue());
		}
	}),
	MINUS('-', new RPNRunnable(){
		public Number execute(Number x, Number y)
		{
			return new Double(y.doubleValue()-x.doubleValue());
		}
	}),
	MULTIPLY('*', new RPNRunnable(){
		public Number execute(Number x, Number y)
		{
			return new Double(y.doubleValue()*x.doubleValue());
		}
	}),
	DIVIDE('/', new RPNRunnable(){
		public Number execute(Number x, Number y)
		{
			return new Double(y.doubleValue()/x.doubleValue());
		}
	})
	;
	
	// Allows for anonymous classes for simpler definitions of operations.
	public interface RPNRunnable
	{
		public Number execute(Number x, Number y);
	}
	
	// A human-readable representation of this operation
	public final char c;
	// An anonymous class that performs the operation
	private RPNRunnable execute;
	
	RPNOperation(char symbol, RPNRunnable op)
	{
		c = symbol;
		execute = op;
	}
	
	/**
	 * Performs an operation on two numbers
	 * @param x The number in the RPN stack index 0
	 * @param y The number in the RPN stack index 1
	 * @return The result of performing the operation on x and y
	 */
	public Number run(Number x, Number y)
	{
		return execute.execute(x,y);
	}
	
	/**
	 * Gets the human-readable representation of the operation
	 * @return The human-readable representation of the operation
	 */
	public char symbol()
	{
		return c;
	}
	
	/**
	 * Equivalent to symbol(), but returns a one-character String object
	 */
	public String toString()
	{
		return c+"";
	}
	
	/**
	 * Finds an RPNOperation by a given symbol
	 * @param symbol The character representation of the desired operation
	 * @return The operation represented by symbol
	 */
	public static RPNOperation op(char symbol)
	{
		for(RPNOperation op : values())
		{
			if(op.c==symbol)
			{
				return op;
			}
		}
		throw new IllegalArgumentException();
	}

}
