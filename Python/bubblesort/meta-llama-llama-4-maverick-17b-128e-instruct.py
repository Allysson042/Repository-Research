import random

def bubble_sort(arr):
    """
    Optimized implementation of Bubble sort algorithm.

    Args:
        arr (list): List of elements to be sorted.

    Returns:
        list: Sorted list of elements.
    """
    n = len(arr)

    for i in range(n):
        # Create a flag that will allow the function to terminate early if there's nothing left to sort
        swapped = False

        # Start looking at each item of the list one by one, comparing it with its adjacent value
        for j in range(n - i - 1):
            # If we find an element that is greater than its adjacent element
            if arr[j] > arr[j + 1]:
                # Swap them
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                # Set the flag to True so we'll loop again
                swapped = True

        # If there were no swaps during the last iteration, the list is already sorted, and we can terminate
        if not swapped:
            break

    return arr


def generate_random_list(size):
    """
    Generate a list of 'size' random integers.

    Args:
        size (int): Number of random integers to generate.

    Returns:
        list: List of 'size' random integers.
    """
    return [random.randint(0, 1000) for _ in range(size)]


import sys      

def main():
    input_path = "inputs.txt" if len(sys.argv) < 2 else sys.argv[1]

    try:
        with open(input_path, 'r') as f:
            arr = [int(line.strip()) for line in f if line.strip()]
    except FileNotFoundError:
        return

    sorted_arr = bubble_sort(arr)

    print(sorted_arr)

if __name__ == "__main__":
    main()
