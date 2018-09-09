package edu.grinnell.sortingvisualizer.events;


import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T> implements SortEvent<T>{
    
    public List<Integer> affectedIndices;
    
    public CompareEvent(int x, int y) {
    	affectedIndices = new ArrayList<Integer>();
        affectedIndices.add(x);
        affectedIndices.add(y);
    }
    /**
     * there is no apply method for compare event
     */
    public void apply(ArrayList<T> lst) {
    }

    /**
     * this method returns a list containing all of the indices that this event affects
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
        return false;
    }

}
    

