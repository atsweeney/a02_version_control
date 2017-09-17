package autocomplete;

import java.util.Comparator;

/**
* BinarySearchDeluxe
* @author Adam Sweeney
* Date: 10/08/15
* Assignment: A03 Autocomplete
*/

public class BinarySearchDeluxe {
	// Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a == null || key == null || comparator == null) {
			throw new NullPointerException();
		}
		
		int locationOfKey = binarySearch(a, key, comparator);
		
		//e.g. not found
		if(locationOfKey == -1) { 
			return -1;
		} else {
			while(locationOfKey > 0 && comparator.compare(a[locationOfKey], key) == 0) {
				if(comparator.compare(a[locationOfKey - 1], key) == 0) {
					locationOfKey--;
				} else {
					break;
				}
			}
			return locationOfKey;
		}	
	}
	
	// Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a == null || key == null || comparator == null) {
			throw new NullPointerException();
		}

		int locationOfKey = binarySearch(a, key, comparator);
		
		//e.g. not found
		if(locationOfKey == -1) { 
			return -1;
		} else {
			while(locationOfKey < a.length && comparator.compare(a[locationOfKey], key) == 0) {
				if(comparator.compare(a[locationOfKey + 1], key) == 0) {
					locationOfKey++;
				} else {
					break;
				}
			}
			return locationOfKey;
		}
		
	}
	
	//Binary Search to find key in array "a" using comparator
	private static <Key> int binarySearch(Key[] a, Key key, Comparator<Key> comparator) {
        int start = 0;
        int end = a.length - 1;
        
        while (start <= end) {
            int middle = start + (end - start) / 2;
            int comparison = comparator.compare(key, a[middle]);
            		
            if(comparison < 0) {
            	end = middle - 1;
            } else if(comparison > 0) {
            	start = middle + 1;
            } else {
            	return middle;
            }
        }
        return -1;
    }
}
