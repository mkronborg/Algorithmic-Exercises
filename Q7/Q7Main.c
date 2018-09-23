//
// @author Maximilian Kronborg
// @date 04/05/18
// @version 1.1
// Reads a text file, and a list of words to redact from it, and then redacts it from the text
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <string.h>

/**
 * Function to convert an array to one that has the correct size.
 * Used to resize array containing chars from file
 * @param name
 *          char * to place in new array
 * @param size
 *          The size needed for new array
 * @return
 *          new array containing supplied data
 */
char *toArray(char *name, int size)
{
    char *done = malloc((size + 1) * sizeof(char)); // Allocates new memory
    for (int i = 0; i < size; i++)
    {
        done[i] = name[i];
    }
    done[size] = '\0';
    return done;
}

/**
  * Method to determine if a given char is a letter
  * @param ch
  *         Char to find type of
  * @return
  *         Boolean if char is a letter
  */
bool alpha(char ch)
{
    int c = (int)ch;
    if  ((c < 91 && c > 64) || (c < 123 && c > 96)) // Checks ascii value
    {
        return true;
    }
    return false;
}

/**
 * Function to output sorted names to file
 * @param names
 *          char **array to print
 * @param outputFile
 *          name of file to output to
 */
void toFile(char** names, char* outputFile)
{
    FILE *file;
    file = fopen(outputFile, "w"); // Opens file
    if (file == NULL)
    {
        printf("Error opening file"); // Prints error code
        exit(1);
    }
    for(int i = 0; names[i] != '\0'; i++) // Prints for formatting
    {
        fprintf(file, "%s", names[i]);
    }
    fclose(file);
}

/**
 * Function to convert an array to one that has the correct size.
 * Used to resize array containing chars from file
 * @param name
 *          char * to place in new array
 * @param size
 *          The size needed for new array
 * @return
 *          new array containing supplied data
 */
char **resize(char ** arr, int newSize, int oldSize)
{
    char **temp = malloc((newSize+1) * sizeof(char *)); // Allocates new memory
    for (int i = 0; i < newSize && i < oldSize; i++)
    {
        temp[i] = arr[i];
    }
    free(arr);
    temp[newSize] = '\0';
    return temp;
}

/**
 * Function to read words from a file
 * @param f
 *          name of file to read from
 * @return
 *          list of words
 */
char **readRedact(char* f)
{
    FILE *file;
    file = fopen(f, "r");
    if (file == NULL)
    {
        printf("Error opening file");
        exit(1);
    }
    int ch;
    ch = getc(file);
    char c;
    int size = 9;
    char **redact = (char**)malloc((size+1)*sizeof(char*)); // Allocates space for words
    redact[size] = '\0'; // Terminating character
    char *word = malloc(20 * sizeof(char)); // Used to store chars read

    int charPointer = 0; // Points to location reached in word
    int wordPointer = 0; // Points to location reached in redact

    while (ch != EOF) {
        c = (char)ch;
        if (alpha(c))
        {
            word[charPointer++] = c; // Adds char
        }
        else if (charPointer  != 0) // If no word has been built so far, nothing is done
        {
            if (wordPointer == size)
                redact = resize(redact, size*2, size);
            redact[wordPointer++] = toArray(word, charPointer); // Completes word and adds to redact
            charPointer = 0;
        }
        ch = getc(file);
    }
    free(word); // Frees space used to store characters temporarily
    return resize(redact, wordPointer, size+1);
}

/**
 * Function to read text from a file
 * @param f
 *          name of file to read from
 * @return
 *          list of text
 */
char **readText(char* f)
{
    FILE *file;
    file = fopen(f, "r");
    if (file == NULL)
    {
        printf("Error opening file");
        exit(1);
    }
    int ch;
    ch = getc(file);
    char c;
    int size = 792;
    char **text = (char**)malloc((size)*sizeof(char*)); // Allocates space for text

    char *word = malloc(20 * sizeof(char)); // Holder for read chars

    int charPointer = 0; // Points to location reached in word
    int wordPointer = 0; // Points to location reached in text

    while (ch != EOF)  // Reads until end of file
    {
        c = (char)ch;
        if (alpha(c)) // Checks if char is a letter
        {
            word[charPointer++] = c;
        }
        else
        {

            if (wordPointer >= size-1)// Resizes array if necessary
            {
                text = resize(text, size*2, size);
                size *= 2;
            }
            if (charPointer != 0) // Of word is not empty then it is added to text
            {
                text[wordPointer++] = toArray(word, charPointer);
            }
            word[0] = c;
            text[wordPointer++] = toArray(word, 1); // Creates word of singular char and adds to text
            charPointer = 0;
        }
        ch = getc(file);
    }
    free(word); // Frees space used to store chars
    return resize(text, wordPointer-1, size); // Resizes array to fit required space
}

/**
 * Function to find the length of a given char []
 * @param str
 *          char[] to find length of
 * @return
 *          Length of char[]
 */
int length(char *str)
{
    int i = 0;
    char *c;
    c = str;
    while(*c != '\0') // Looks for terminating character
    {
        c++;
        i++;
    }
    return i;
}

/**
 * Function to check if two strings are identical
 * @param nameOne
 *          First string
 * @param nameTwo
 *          Second string
 * @return
 *          Boolean value to indicate if strings are identical
 */
bool equals(char *nameOne, char *nameTwo)
{
    int i = 0;
    int firstLength = length(nameOne);
    int secondLength = length(nameTwo);
    if (secondLength != firstLength)
        return false;
    for (int i = 0; i < firstLength; i++)
    {
        if(nameOne[i] != nameTwo[i])
            return false;
    }
    return true;
}

/**
 * Function to find length of a char**
 * @param names
 *          char ** to find length of
 * @return
 *          length of char **
 */
int wordsLength(char ** words)
{
    int i = 0;
    char **c;
    c = words;
    while(*c != 0) // Reads until terminating character
    {
        c++;
        i++;
    }
    return i;
}

/**
 * Function to check if a char* is in a given char**
 */
bool find(char *word, char **words)
{
    int length = wordsLength(words);
    for(int i = 0; i < length; i++)
    {
        if (equals(word, words[i])) // Check if words are identical
            return true;
    }
    return false;
}

/**
 * Function to return a char *of only '*' characters
 * @param word
 *          word to redact
 */
void redactWord(char *word)
{
    int len = length(word);
    for (int i = 0; i < len; i++)
    {
        word[i] = '*';
    }
}

/**
 * Function to select words to redact, and replace with redacted strings
 * @param text
 *          Text to redact
 * @param redact
 *          Redacted text
 */
void redactText(char **text, char **redact)
{
    int length = wordsLength(text);
    for (int i = 0; i < length; i++)
    {
        if (find(text[i], redact)) // If word is in list to redact it gets redacted
            redactWord(text[i]);
    }
}

/**
 * Main function, gets user arguments and runs redact function, then outputs result
 * @param argc
 *          Number of arguments passed
 * @param argv
 *          char** containing arguments
 * @return
 *          int 0 to indicate success
 */
int main(int argc, char *argv[]){
    // argv 1 - name of file to read in a list of names
    // argv 2 - name of file to write out the sorted list of names
   /** char *inputFile, *toRedact, *outputFile;
    if(argc == 3){
        inputFile = argv[1];
        toRedact = argv[2];
        outputFile = "outputRedact.txt";
    }
    else if(argc == 4){
        inputFile = argv[1];
        toRedact = argv[2];
        outputFile = argv[3];
    }
    else if(argc <= 1)
    {
        printf("Not enough arguments supplied");
        exit(1);
    }
    char **redact;
    redact = readRedact(toRedact); // Reads words to redact

    char **text;
    text = readText(inputFile); // Reads text

    clock_t begin = clock();

    redactText(text, redact);

    clock_t end = clock();
    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;

    printf("Time taken to run: %f", time_spent);

    toFile(text, outputFile);

    return 0; //Successful exit**/

   char z = "x";

    char* a = &z;
    a = (char*)malloc(2 * sizeof(char));
    strcpy(a, "CM10228");
    printer(a);
    //free(a);

}

void printer(char *h)
{
    printf("%s", h);
}