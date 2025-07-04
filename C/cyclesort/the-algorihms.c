// Sorting of array list using cycle sort
#include <stdio.h>
#include <stdlib.h>

// Displays the array, passed to this method
void display(int *arr, int n)
{
    int i;
    for (i = 0; i < n; i++)
    {
        printf("%d ", arr[i]);
    }

    printf("\n");
}

// Swap function to swap two values
void swap(int *first, int *second)
{
    int temp = *first;
    *first = *second;
    *second = temp;
}

// Function sort the array using Cycle sort
void cycleSort(int *arr, int n)
{
    // count number of memory writes
    int writes = 0;

    // traverse array elements and put it to on
    // the right place
    for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++)
    {
        // initialize item as starting point
        int item = arr[cycle_start];

        // Find position where we put the item. We basically
        // count all smaller elements on right side of item.
        int pos = cycle_start;
        for (int i = cycle_start + 1; i < n; i++)
            if (arr[i] < item)
                pos++;

        // If item is already in correct position
        if (pos == cycle_start)
            continue;

        // ignore all duplicate elements
        while (item == arr[pos]) pos += 1;

        // put the item to it's right position
        if (pos != cycle_start)
        {
            swap(&item, &arr[pos]);
            writes++;
        }

        // Rotate rest of the cycle
        while (pos != cycle_start)
        {
            pos = cycle_start;

            // Find position where we put the element
            for (int i = cycle_start + 1; i < n; i++)
                if (arr[i] < item)
                    pos += 1;

            // ignore all duplicate elements
            while (item == arr[pos]) pos += 1;

            // put the item to it's right position
            if (item != arr[pos])
            {
                swap(&item, &arr[pos]);
                writes++;
            }
        }
    }
}

int main()
{
    FILE *file = fopen("../../inputs.txt", "r");
    if (!file)
    {
        return 1;
    }

    int capacity = 100;
    int *arr = malloc(capacity * sizeof(int));
    int n = 0;

    // Lê números até acabar o arquivo
    while (fscanf(file, "%d", &arr[n]) == 1)
    {
        n++;
        if (n == capacity)
        {
            capacity *= 2;
            arr = realloc(arr, capacity * sizeof(int));
            if (!arr)
            {
                fclose(file);
                return 1;
            }
        }
    }
    fclose(file);

    cycleSort(arr, n);
    free(arr);
    return 0;
}
