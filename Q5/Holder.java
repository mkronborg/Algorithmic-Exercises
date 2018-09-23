/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Holder <T1>
{
	private T1 element; // Element stored in Holder object

	/**
	 * Constructor for Holder object
	 * @param element
	 * 			Element to store
	 */
	public Holder(T1 element)
	{
		this.element = element;
	}

	/**
	 * Accessor method for Holder object
	 * @return
	 * 		Element held in object
	 */
	public T1 getElement()
	{
		return element;
	}
}
