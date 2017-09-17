package autocomplete;

import java.util.Arrays;

/**
* Autocomplete
* @author Adam Sweeney
* Date: 10/08/15
* Assignment: A03 Autocomplete
*/

public class Autocomplete {
	private final Term[] terms;
	
	// Initializes the data structure from the given array of terms.
	public Autocomplete(Term[] terms) {
		if(terms == null) {
			throw new NullPointerException();
		}
		this.terms = terms;
		Arrays.sort(terms);
		
	}
	
	// Returns all terms that start with the given prefix, in descending order of weight.
	public Term[] allMatches(String prefix) {
		if(prefix == null) {
			throw new NullPointerException();
		}
		Term match = new Term(prefix, 0);
		int start = BinarySearchDeluxe.firstIndexOf(this.terms, match, Term.byPrefixOrder(prefix.length()));
		int end = BinarySearchDeluxe.lastIndexOf(this.terms, match, Term.byPrefixOrder(prefix.length()));
		
		int N = end - start + 1;
		Term[] matches = new Term[N];
		for(int i = 0; i < N; i++) {
			matches[i] = this.terms[start + i];
		}
		
		Arrays.sort(matches, Term.byReverseWeightOrder());
		
		return matches; 
		
	}
	
	// Returns the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix) {
		if(prefix == null) {
			throw new NullPointerException();
		}
		return allMatches(prefix).length;
	}
}
