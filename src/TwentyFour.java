import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.mkirsch42.rpn.RPNOperation;
import com.mkirsch42.rpn.tree.RPNNode;
import com.mkirsch42.rpn.tree.RPNNumeralNode;
import com.mkirsch42.rpn.tree.RPNOperationNode;

// A class to solve the game "24" using brute-force
public class TwentyFour
{

	// Repeat solutions caused by duplicate numbers are useless, so use a set
	public static HashSet<String> solutions = new HashSet<String>();
	
	/**
	 * Loops through permutations of nodes recursively and evaluates 
	 * the output of the sequence
	 * @param stack The possible numbers to add to the tree
	 * @param root The tree of RPN inputs
	 */
	public static void func(ArrayList<Double> stack, RPNNode root)
	{
		// Loop though each possible number
		for(Double d : stack)
		{
			// Add node
			root.addChild(new RPNNumeralNode(d));
			// Calculate remaining numbers and call recursively
			ArrayList<Double> stack2 = (ArrayList<Double>)stack.clone();
			stack2.remove(d);
			func(stack2,root);
			// Remove node to try next number/operation
			root.pop();
		}
		// Operations must have 2 numbers in the RPN stack
		if(root.stackSize()>=2)
		for(RPNOperation o : RPNOperation.values())
		{
			// Add node
			root.addChild(new RPNOperationNode(o));
			// The tree is complete if all numbers are added and the
			// resulting RPN stack is size 1
			if(stack.size()==0 && root.stackSize()==1)
			{
				// Use epsilon because double
				if(Math.abs(root.execute().doubleValue()-24)<0.000001)
				{
					solutions.add(root.toString());
				}
			}
			else
			{
				// Keep adding to tree if the tree isn't complete yet
				func(stack,root);
			}
			// Remove node to try next operation
			root.pop();
		}
	}
	
	public static void main(String[] args)
	{
		// The 4 numbers to be used
		ArrayList<Double> stack = new ArrayList<Double>();
		// Input numbers
		Scanner in = new Scanner(System.in);
		for(int i=0; i<4; i++)
		{
			stack.add(Double.parseDouble(in.nextLine()));
		}
		// Loop through possible first numbers
		// (First 2 nodes must be numbers, can probably be done with func
		// but fuck that I'm lazy)
		for(Number n : stack)
		{
			// Compile list of remaining numbers
			ArrayList<Double> stack2 = ((ArrayList<Double>)stack.clone());
			stack2.remove(n);
			// Create root node
			RPNNode root = new RPNNumeralNode(n);
			
			// Loop through possible second numbers
			for(Number n2 : stack2)
			{
				// Compile list of remaining numbers
				ArrayList<Double> stack3 = ((ArrayList<Double>)stack2.clone());
				stack3.remove(n2);
				// Add number to tree
				root.addChild(new RPNNumeralNode(n2));
				// Can now call func since operators are valid after 2 numbers.
				func(stack3, root);
				// Must remove last node before replacing it with next number
				root.pop();
			}
		}
		
		// Print all solutions
		for(String str : solutions)
		{
			System.out.println(str);
		}
	}
	
}
