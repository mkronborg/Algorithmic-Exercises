//
// @author Maximilian Kronborg
// @date 04/05/18
// @version 1.1
// Reads a file of characters and compresses them
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

/**
 * Function to output list of words to file
 * @param names
 *          char **array to print
 * @param outputFile
 *          name of file to output to
 */
void toFile(char* text, char* outputFile)
{
    FILE *file;
    file = fopen(outputFile, "w"); // Opens file
    if (file == NULL)
    {
        printf("Error opening file"); // Prints error code
        exit(1);
    }

    fprintf(file, text);
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
char *resize(char *arr, int newSize, int oldSize)
{
    char *temp = malloc((newSize) * sizeof(char)); // Allocates new memory
    for (int i = 0; i < newSize && i < oldSize; i++)
    {
        temp[i] = arr[i];
    }
    temp[newSize] = '\0';
    free(arr);
    return temp;
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
 * Function to read text from a file
 * @param f
 *          File to read from
 * @return
 *          char * containing character
 */
char *readText(char* f)
{
    FILE *file;
    file = fopen(f, "r"); // Opens file
    if (file == NULL)
    {
        printf("Error opening file");
        exit(1);
    }
    int ch;
    ch = getc(file);
    char c;
    int size = 30001;
    char *text = malloc((size)*sizeof(char)); // Allocates space for characters
    text[size-1] = '\0'; // Terminating character

    int charPointer = 0; // Points to location in char* to store next char

    while (ch != EOF) // Reads until end of file
    {
        c = (char)ch;
        if (charPointer == size)
        {
            text = resize(text, (size-1)*2, size-1); // Doubles size of memory allocated if necessary
            size = size * 2 - 1;
        }
        text[charPointer++] = c;
        ch = getc(file);
    }
    text = resize(text, charPointer, size-1); // Ensures correct array size and adds terminating character.
    return text;
}

/**
 * Function to add a given char* to another char*  at a given index
 * @param str
 *          char* to add to char*
 * @param addTo
 *          char* to be added to
 * @param place
 *          Index to add char* at
 */
void add(char *str, char *addTo, int place)
{
    int len = length(str);
    for (int i = 0; i < len; i++)
    {
        addTo[place + i] = str[i];
    }
}

/**
 * Function to compress a text by replacing repeated characters by a number specifying number of repetitions
 * @param text
 *          Text to compress
 * @return
 *          Compressed text
 */
char *compress(char *text)
{

    int count = 1; // Count of how many successive chars of same type have been read
    int pointer = 0;  // Pointer to location in compressed string
    char temp = text[0]; // Starting char is first char in characters
    int len = length(text);


    char str [10]; // Array to store chars in
    char *compressed = malloc(len * sizeof(char)); // Allocates space for compressed string
    for (int i = 1; i < length(text); i++)
    {
        if (text[i] == temp) // If another of the same char is read, then count is incremented
        {
            count++;
        }
        else if (count > 1 ) // If a different char is read and count is greater than one, a number is needed
        {
            sprintf(str, "%d", count); // Adds the string representation of count to str
            compressed[pointer++] = temp; // adds char read to compressed
            add(str, compressed, pointer++); // Adds number of occurrences to compressed
            temp = text[i]; // Temp is now new char dissimilar to last one
            count = 1; // Count is reset
        }
        else
        {
            compressed[pointer++] = temp; // adds char to compressed
        }
        temp = text[i];
    }
    compressed = resize(compressed, pointer, len);
    free(text);
    return compressed;

}

/**
 * main function, takes user arguments and runs compression algorithm
 * @param argc
 *          Number of arguments supplied
 * @param argv
 *          char ** containing arguments
 * @return
 *          Int 0 to indicate success
 */
int main(int argc, char *argv[]){
    // argv 1 - name of file to read in a list of names
    // argv 2 - name of file to write out the sorted list of names
    char *inputFile, *outputFile;
    if(argc == 2){
        inputFile = argv[1];
        outputFile = "compressed.txt";
    }
    else if(argc == 3){
        inputFile = argv[1];
        outputFile = argv[2];
    }
    else if(argc <= 1)
    {
        printf("Not enough arguments supplied");
        exit(1);
    }
    char *text;
    text = readText(inputFile); // Reads text

    clock_t begin = clock();

    text = compress(text);

    clock_t end = clock();
    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;

    printf("Time taken to run: %f", time_spent);

    toFile(text, outputFile);

    return 0; //Successful exit
}