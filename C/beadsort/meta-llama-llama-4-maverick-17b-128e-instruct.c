
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Function to perform Beadsort
void beadsort(int* arr, int n) {
    int max = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }

    // Create a 2D array to represent the beads
    int** beads = (int**)malloc(n * sizeof(int*));
    for (int i = 0; i < n; i++) {
        beads[i] = (int*)malloc((max + 1) * sizeof(int));
        for (int j = 0; j <= max; j++) {
            if (j <= arr[i]) {
                beads[i][j] = 1;
            } else {
                beads[i][j] = 0;
            }
        }
    }

    // Count the beads
    for (int j = 0; j <= max; j++) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += beads[i][j];
        }
        // Update the array with the sorted values
        for (int i = n - 1; i >= 0; i--) {
            if (count > 0) {
                beads[i][j] = 1;
                count--;
            } else {
                beads[i][j] = 0;
            }
        }
    }

    // Update the original array with the sorted values
    for (int i = 0; i < n; i++) {
        int count = 0;
        for (int j = 0; j <= max; j++) {
            if (beads[i][j] == 1) {
                count++;
            }
        }
        arr[i] = count;
    }

    // Free the allocated memory
    for (int i = 0; i < n; i++) {
        free(beads[i]);
    }
    free(beads);
}

int main() {
    srand(time(NULL));

    FILE* f = fopen("../../inputs.txt", "r");

    int capacity = 1000;
    int* array = (int*)malloc(capacity * sizeof(int));
    int size = 0;

    while (fscanf(f, "%d", &array[size]) == 1) {
        size++;
        if (size >= capacity) {
            capacity *= 2;
            array = (int*)realloc(array, capacity * sizeof(int));
        }
    }

    fclose(f);

    beadsort(array, size);


    free(array);
    return 0;
}