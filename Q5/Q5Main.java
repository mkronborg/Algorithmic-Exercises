/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q5Main{

	/**
	 * Main method, create key-value-pair, and demonstrates functionality
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		String[] sentence= {"Hi,", "my", "name", "is", "Max"};
		Integer number = 4;
		Integer four = 4;
		KeyValuePair<Integer, String> kvp = new KeyValuePair<Integer, String>(number, sentence[number]); // Places the number 4 and sentence[4]("Max") as a pair in the KeyValuePair object
		kvp.add(number, sentence[number]);
		kvp.add(2, sentence[2]);
		System.out.println(2 + " is " + kvp.find(2)); // Finds the correct corresponding object using a different int value
		System.out.println(number + " is " + kvp.find(four)); // Finds the correct corresponding object using a different Integer object with same int value

		kvp.remove(2); // Calls remove function with key
		System.out.println("Trying to find with key " + 2 + ": " + kvp.find(2));

		DynamicArray<String> arr1 = new DynamicArray<String>(1); // Creates dynamicArray and adds an element
		arr1.add("Word");

		String word = "String to find";


		KeyValuePair<DynamicArray<String>, String> kvp2 = new KeyValuePair<DynamicArray<String>, String>(arr1, word); // Places the number 4 and sentence[4]("Max") as a pair in the KeyValuePair object
		kvp2.add(arr1, word);

		System.out.println("Key to find string gives: " + kvp2.find(arr1));

		kvp2.remove();
		System.out.println("Key to find string gives: " + kvp2.find(arr1));
		kvp2.remove(); // Attempts to remove another element


	}
}