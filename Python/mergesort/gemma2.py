
import random

def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left_half = merge_sort(arr[:mid])
    right_half = merge_sort(arr[mid:])
    
    return merge(left_half, right_half)

def merge(left, right):
    merged = []
    i = j = 0
    
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1
    
    merged.extend(left[i:])
    merged.extend(right[j:])
    
    return merged

import sys

def main():
    input_path = "inputs.txt" if len(sys.argv) < 2 else sys.argv[1]

    try:
        with open(input_path, 'r') as f:
            arr = [int(line.strip()) for line in f if line.strip()]
    except FileNotFoundError:
        return

    sorted_arr = merge_sort(arr)
    

if __name__ == "__main__":
    main()
