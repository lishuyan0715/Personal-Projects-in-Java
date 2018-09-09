package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T> implements SortEvent<T>{

    public List<Integer> affectedIndices;
    
    public SwapEvent(int x, int y) {
    	affectedIndices = new ArrayList<Integer>();
        affectedIndices.add(x);
        affectedIndices.add(y);
    }
    
        
    /**
     * This method applies this sort event to the given list.
     */
	public void apply(ArrayList<T> lst) {
        T temp = lst.get(affectedIndices.get(0));
        lst.set(affectedIndices.get(0), 
        		lst.get(affectedIndices.get(1)));
        lst.set(affectedIndices.get(1), temp);
    }
     /**
     * This method returns a list containing all of the indices that this event affects
     * @return List, a list of integers
     */
	
    public List<Integer> getAffectedIndices() {
        return affectedIndices;
    }
    /**
     * This method returns true if this event should be emphasized by the visualizer/audibilizer.
     * @return boolean
     */
    public boolean isEmphasized() {
        return true;
    }
    
}
