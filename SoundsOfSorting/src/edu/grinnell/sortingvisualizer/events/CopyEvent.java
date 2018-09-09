package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T> implements SortEvent<T>{

    public int index;
    public  T value;
    public ArrayList<T> recordedValue;
    
    public CopyEvent(int x, T value) {
        this.index = x;
        this.value = value;
    }
    
        
    /**
     * This method applies this sort event to the given list.
     */
    public void apply(ArrayList<T> lst) {
        lst.set(index, value);
    }

    /**
     * This method returns a list containing all of the indices that this event affects
	 * @return List, a list of integers
     */
    public List<Integer> getAffectedIndices() {
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(index);
        return lst;
    }

    /**
     * This method returns true if this event should be emphasized by the visualizer/audibilizer.
     * @return boolean
     */
    public boolean isEmphasized() {
        return true;
    }
}
    
    
