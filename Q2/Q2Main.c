//
// @author Maximilian Kronborg
// @date 04/05/18
// @version 1.1
// Reads a list of names and bubblesorts them
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

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
    fprintf(file, "\""); // Prints for formatting
    fprintf(file, "%s", names[0]);
    for(int i = 1; names[i] != '\0'; i++)
    {
        fprintf(file, "\", \"");
        fprintf(file, "%s", names[i]);
    }
    fprintf(file, "\"");
    fclose(file);
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
 * Function to compare two names lexicographically
 * @param names
 *          char **array to look in
 * @param first
 *          Index of first word to look at
 * @param second
 *          Index of second word to look at
 * @return
 *
 */
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
    while(*c != 0) // Looks for terminating character
    {
        c++;
        i++;
    }
    return i;
}

bool compare(char **names, int first, int second)
{
    int i = 0;
    int firstLength = length(names[first]);
    int secondLength = length(names[second]);
    while (i < firstLength && i < secondLength)
    {
        if ((int)names[first][i] > (int)names[second][i]) // If at any point a char in the first letter has a higher lexicographic value then the words should be switched
        {
            return true;
        }
        else if ((int)names[first][i] < (int)names[second][i]) // If at any point a char in the second letter has a higher lexicographic value then the words should not be switched
        {
            return false;
        }
        i++;
    }
    if (secondLength > firstLength) // If at this point second is shorter than first then the two should be switched
        return true;
    return false;
}

/**
 * Function to swap the words at two indices
 * @param names
 *          the char ** to look in
 * @param first
 *          the index of the first word to swap
 * @param second
 *          the index of the second word to swap
 */
void swap(char** names, int first, int second)
{
    char* temp = names[first];
    names[first] = names[second];
    names[second] = temp;
}

/**
 * Function to find length of a char**
 * @param names
 *          char ** to find length of
 * @return
 *          length of char **
 */
int namesLength(char ** names)
{
    int i = 0;
    char **c;
    c = names;
    while(*c != '\0')
    {
        c++;
        i++;
    }
    return i;
}

/**
 * Function to bubblesort a list of names
 * @param names
 *          char ** to sort
 */
void bubbleSort(char** names)
{
    int len = namesLength(names);
    for (int a = len; a > 0; a--)
    {
        for (int i = 0; i < a-1; i++)
        {
            if (compare(names, i, i+1))
            {
                swap(names, i, i+1);
            }
        }
    }
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
char *toArray(char *name, int size)
{
    char *done = (char*)malloc((size+1)* sizeof(char)); //Allocates new memory
    for (int i = 0; i < size; i++)
    {
        done[i] = name[i];
    }
    done[size] = '\0';
    return done;
}

/**
 * Function to read a list of names from a file
 * @param f
 * @return
 */
char **readNames(char* f)
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
    char **names = (char**)malloc(5164*sizeof(char*)); // Allocates space for names
    names[5163] = '\0'; // Terminating character
    char *name = (char*)malloc(20 * sizeof(char)); // temporary array to store word being built in
    int charPointer = 0;
    int namePointer = 0;
    while (ch != EOF) // Reads until end of file
    {
        c = (char)ch;
        if (alpha(c)) // Checks if char is letter
        {
            name[charPointer++] = c; // Adds char
        }
        else if (charPointer  != 0) // If no word has been built so far, nothing is done
        {
            names[namePointer++] = toArray(name, charPointer); // Completes word and adds to names
            charPointer = 0;
        }
        ch = getc(file);
    }
    free(name); // Frees space used to store characters temporarily
    return names;
}

int main(int argc, char *argv[]) {
    // argv 1 - name of file to read in a list of names
    // argv 2 - name of file to write out the sorted list of names
    char *inputFile, *outputFile;
    if (argc == 2) {
        inputFile = argv[1]; // Default value
        outputFile = "outputNames.txt";
    } else if (argc == 3) {
        inputFile = argv[1];
        outputFile = argv[3];
    }
    char **names;
    names = readNames(inputFile);

    clock_t begin = clock();

    bubbleSort(names);

    clock_t end = clock();
    double time_spent = (double) (end - begin) / CLOCKS_PER_SEC;

    printf("Time taken to run: %f", time_spent);

    toFile(names, outputFile);
    return 0;
}