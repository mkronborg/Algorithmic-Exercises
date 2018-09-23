/**
 * @author Maximilian Kronborg - mk2022
 * @date 03.05.18
 * @version 1.2
 */
public class Q8Main{

    /**
     * Converts a DynamicArray object to a String array
     * @param convert
     * 			DynamicArray to convert
     * @return
     * 			an array containing the same data as the DynamicArray
     */
    public static String[] toArray(DynamicArray<String> convert)
    {
        String [] arr = new String[convert.length()]; // Creates array of same length

        for (int i = 0; i < convert.length(); i++)
        {
            arr[i] = convert.get(i);
        }

        return arr;
    }

    public void redactText(String input, String toRedact, String output){
        ReadFile rf = new ReadFile();
        rf.readWords(input);

        String [] t = rf.getWords();
        DynamicArray<String> text = new DynamicArray<String>(t);

        rf.readRedact(toRedact);
        String [] l = rf.getWords();
        DynamicArray<String> words = new DynamicArray<String>(l);

        Redacter r = new Redacter(text, words);
        DynamicArray<String> redacted = r.redactText();

        rf.outputWords(toArray(redacted), output);
    }

    public static void main(String[] args){
        // args 0 - name of file to read in text
        String inputFileName = null;
        // args 1 - name of file to read in list of words to redact
        String toRedact = null;
        // args 2 - name of file to write out the redacted text
        String outputFileName = null;

        if(args.length == 2){
            inputFileName = args[0];
            toRedact = args[1];
            outputFileName = "RedactedText.txt";
        }
        else if(args.length == 3){
            inputFileName = args[0];
            toRedact = args[1];
            outputFileName = args[2];
        }
        else if (args.length == 0)
        {
            System.err.println("Insufficient arguments supplied, please try again");
            System.exit(0);
        }
        // call method to redact words from input text
        new Q8Main().redactText(inputFileName,toRedact,outputFileName);
    }
}