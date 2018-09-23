//
// @author Maximilian Kronborg
// @date 04/05/18
// @version 1.1
// Acts as a Stack of Strings, and demonstrates their use
//

#include <stdio.h>
#include <stdlib.h>

/**
 * Struct simulating an element in a stack
 * Holds a char * to the String value stored in the struct
 * Holds a pointer to the next element in the stack
 * Holds the integer value this element has in the stack
 */
struct element
{
    char *str;
    struct element *next;
    int count;
};

/**
 * Function to push a new value to the stack
 * @param ptr
 *          pointer to the pointer that points to the top of the stack, doing this saves passing several arguments back in return statements
 * @param s
 *          char * to String to be pushed to stack
 */
void push(struct element **ptr, char *s)
{
    struct element *newElement = malloc(sizeof(struct element)); // Allocate new space for struct
    (*newElement).str = s; // Set value of struct
    (*newElement).count = (**ptr).count + 1;
    (*newElement).next = *ptr; // Set pointer to next struct to be pointer to the  current top of stack
    *ptr = newElement;
}

/**
 * Function to pop an element from the stack
 * @param ptr
 *          Pointer to pointer that points to the address of element at the top of the stack
 * @return
 *          String value of top of stack
 */
char *pop(struct element **ptr) // Passes address of pointer used in function using stack
{
    if ((**ptr).count == 0)
    {
        printf("Stack underflow\n");
        exit(1);
    }
    char *str = (**ptr).str;
    struct element *temp = (**ptr).next; // Temporary pointer to a stack is set equal to the next element of the struct that the address at *ptr points to
    free(*ptr); // Frees space held by previous top of stack
    *ptr = temp; // sets pointer to point to new top of stack
    return str;
}

/**
 * Function to display value at top of stack without removing it
 * @param ptr
 *          Pointer to a pointer that points to the element
 * @return
 *          String at top of stack
 */
char *peek(struct element **ptr)
{
    return (**ptr).str;
}

/**
 * Creates a new stack
 * @return
 *      Pointer to first element, functioning as terminating element with count 0;
 */
struct element *newStack()
{
    struct element *top = malloc(sizeof(struct element));
    (*top).next = 0;
    (*top).count = 0;
    (*top).str = 0;
}

/**
 * Main method, creates stack and demonstrates functionality
 * @param argc
 *          Number of arguments passed, unused
 * @param argv
 *          Arguments passed, unused
 * @return
 *          Int 0 to indicate success
 */
int main(int argc, char *argv[])
{
    struct element *top = newStack(); // Creates stack
    char *toPush = "World"; // creates char*
    push(&top, toPush); // Pushes
    printf("Pushed: %s", toPush); // Informs user
    toPush = "Hello"; // Changes char* value
    push(&top, toPush); // Pushes new value
    printf("Pushed: %s", toPush); // Informs user
    printf("Peek at top: %s\n", pop(&top)); // Peeks
    printf("Pop element: %s\n", pop(&top)); // Pops
    printf("Pop element: %s\n", pop(&top)); //Pops

    return 0; //Successful exit
}