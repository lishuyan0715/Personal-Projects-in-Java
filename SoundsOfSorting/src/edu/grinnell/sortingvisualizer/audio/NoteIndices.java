package edu.grinnell.sortingvisualizer.audio;
import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {

	private ArrayList<Integer> indices;
	private ArrayList<Boolean> highlightIndices;
	
    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
    	indices= new ArrayList<Integer>();
    	highlightIndices = new ArrayList<Boolean>();
    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {
    	indices.clear();
		for(int i = 0; i < n; i++) {
			indices.add(i);
		}
    	Random rand = new Random();
    	for (int i = 0; i < n - 1; i++) {
    		int j = rand.nextInt(n - i) + i;
    		int temp = indices.get(j);
    		indices.set(j, indices.get(i));
    		indices.set(i, temp);
    	}
        for(int i = 0; i < n; i++) {
        	highlightIndices.add(false);
}
    }
    
    /** @return the indices of this NoteIndices object */
    public ArrayList<Integer> getNotes() { 
        return indices;
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
    	 highlightIndices.set(index, true);
    }
    
    /** @return true if the given index is highlighted */
    public boolean isHighlighted(int index) {
    	return highlightIndices.get(index);
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
    	for(int i = 0; i < highlightIndices.size(); i++) {
        	highlightIndices.set(i, false);
}
    }
}
