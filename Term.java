package autocomplete;

import java.util.Comparator;

/**
* Term
* @author Adam Sweeney
* Date: 10/08/15
* Assignment: A03 Autocomplete
*/

public class Term implements Comparable<Term> {

	private final String query;
	private final long weight;
	
	// Initializes a term with the given query string and weight.
	public Term(String query, long weight) {
		if(query == null) {
			throw new NullPointerException();
		} 
		if(weight < 0) {
			throw new IllegalArgumentException();
		}
		this.query = query;
		this.weight = weight;
	}
	
	// Compares the two terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder() {
		
		final class byReverseWeightOrderComparator implements Comparator<Term> {
			@Override
			public int compare(Term v, Term w) {
				if(v.weight < w.weight) {
					return 1;
				} else if(v.weight > w.weight) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		final Comparator<Term> BY_REVERSE_WEIGHT_ORDER = new byReverseWeightOrderComparator();
		return BY_REVERSE_WEIGHT_ORDER;
	}
	
	// Compares the two terms in lexicographic order but using only the first r characters of each query.
	public static Comparator<Term> byPrefixOrder(int r) {
		
		final class byPrefixOrderComparator implements Comparator<Term> {
			private final int r; 
			
			public byPrefixOrderComparator(int r) {
				if(r < 0) {
					throw new IllegalArgumentException();
				}
				this.r = r;
			}
			
			@Override
			public int compare(Term v, Term w) {
				if(v.query.length() >= r && w.query.length() >= r) {
					return v.query.substring(0, r).compareTo(w.query.substring(0, r));
				}else if(v.query.length() < r && w.query.length() < r) { 
	                return v.query.compareTo(w.query);
	            }else if(v.query.length() < r) { 
	                return v.query.compareTo(w.query.substring(0, r));
	            }else { 
	                return v.query.substring(0, r).compareTo(w.query);
	            }
			}
		}
		
		final Comparator<Term> BY_PREFIX_ORDER = new byPrefixOrderComparator(r);
		return BY_PREFIX_ORDER;
	}
	
	// Compares the two terms in lexicographic order by query.
	@Override
	public int compareTo(Term that) {
		return this.query.compareTo(that.query);
	}
	
	// Returns a string representation of this term in the following format:
	// the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		return String.format(weight + "\t" + query);
	}
}
