/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Stack {

	private int[] stack; // Array containing ints
	private int pointer; // Pointer to first element

	/**
	 * Constructor for Stack objects
	 */
	public Stack()
	{
		stack = new int[10]; // Default size
		pointer = -1; // Because of use of ++ and --, pointer must start at -1
	}

	/**
	 * Constructor of Stack object using size specification
	 * @param size
	 * 			Size of Stack needed
	 */
	public Stack(int size)
	{
		stack = new int[size];
		pointer = -1;
	}

	/**
	 * Method to return size of Stack
	 * @return
	 * 			Size of stack
	 */
	public int getSize()
	{
		return pointer;
	}

	/**
	 * Method to expand size of array
	 */
	public void expand()
	{
		int[] newer = new int[2 * stack.length]; // Doubles array rather than adding only one extra space
		for(int i = 0; i < stack.length; i++)
		{
			newer[i] = stack[i];
		}
		stack = newer;
	}

	/**
	 * Method to pop an element from the stack
	 * @return
	 * 			The element at the top of the stack
	 */
	public int pop()
	{
		return stack[pointer--];
	}

	/**
	 * Method to push an element to the stack
	 * @param n
	 * 			Element to push
	 */
	public void push(int n)
	{
		
		if (pointer == stack.length-1)
			expand();
		stack[++pointer] = n;
	}
}
