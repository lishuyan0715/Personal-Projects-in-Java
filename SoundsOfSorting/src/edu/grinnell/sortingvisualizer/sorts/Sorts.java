package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;

import edu.grinnell.sortingvisualizer.events.CompareEvent;
import edu.grinnell.sortingvisualizer.events.CopyEvent;
import edu.grinnell.sortingvisualizer.events.SortEvent;
import edu.grinnell.sortingvisualizer.events.SwapEvent;

public class Sorts {

	/**
	 * This method swaps two elements in an array
	 * @param a
	 * @param b
	 * @param lst
	 */
	public static <T extends Comparable<T>> void swap(int a, int b, ArrayList<T> lst){
		T temp = lst.get(a);
		lst.set(a, lst.get(b));
		lst.set(b, temp); 
	}
	
	/**
	 * THis method performs selection sort on an array
	 * @param lst
	 * @return a SortEvent object
	 */
	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> selectionSort(ArrayList<T> lst){
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < lst.size(); i++) {
			int loptr = i;
			for(int j = i + 1; j < lst.size(); j++) {
				SortEvent<T> com = new CompareEvent<T>(j, loptr);
				sortEventList.add(com);
				if(lst.get(j).compareTo(lst.get(loptr)) < 0) {
					loptr = j;
				}
			}
			SortEvent<T> swap = new SwapEvent<T>(i, loptr);
			sortEventList.add(swap);
			swap(i, loptr, lst);
		}
		return sortEventList;
	}

	/**
	 * THis method performs insertion sort on an array
	 * @param lst
	 * @return a SortEvent object
	 */
	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> insertionSort(ArrayList<T> lst){
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < lst.size(); i++){
			for(int j = i ; j > 0; j--){
				SortEvent<T> comEvent = new CompareEvent<T>(j - 1, j);
				sortEventList.add(comEvent);
				
				if (lst.get(j - 1).compareTo(lst.get(j)) > 0) {
					SortEvent<T> swapEvent = new SwapEvent<T>(j - 1, j);
					sortEventList.add(swapEvent);
					swap(j - 1, j, lst);
				}
				}
			}
		return sortEventList;
	}

	/**
	 * This is a method that performs comb sort
	 * @param arr, a non-empty array
	 * @return a SortEvent object
	 */
	public static <T extends Comparable<T>> ArrayList<SortEvent<T>> combSort(ArrayList<T> lst) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for (int i= 1; i < lst.size(); i++) {
			for (int j = i; j > 0; j--) {
				SortEvent<T> comEvent = new CompareEvent<T>(j-1, i);
				sortEventList.add(comEvent);
				if((lst.get(j-1)).compareTo(lst.get(j)) >0) {
					SortEvent<T> swapEvent = new SwapEvent<T>(j, j-1);
					sortEventList.add(swapEvent);
					swap(j, j-1, lst);
				}
			}
		}
		return sortEventList;
	}

	/**
	 * This method performs the basic sorting activity of merge sort
	 * @param lst
	 * @param lo
	 * @param mid
	 * @param hi
	 * @param sortEventList
	 */
	public static <T extends Comparable<T>> void merge(ArrayList<T> lst, int lo, int mid, int hi, ArrayList<SortEvent<T>> sortEventList) {
		ArrayList<T> temp = new ArrayList<T>();
		int ptrlo = lo;
		int ptrmid = mid;
		for (int i = lo; i < hi; i++) {
			if (ptrlo == mid) {
				temp.add(lst.get(ptrmid));
				ptrmid++;
			}
			else if (ptrmid == hi) {
				temp.add(lst.get(ptrlo));
				ptrlo++;
			}
			else {
				SortEvent<T> comEvent = new CompareEvent<T> (ptrlo, ptrmid);
				sortEventList.add(comEvent);
				if(lst.get(ptrlo).compareTo(lst.get(ptrmid)) < 0) {
					temp.add(lst.get(ptrlo));
					ptrlo++;
				}
				else {
					temp.add(lst.get(ptrmid));
					ptrmid++;
				}
			}
		}
		
		int j = 0;
		for(int i = lo; i < hi; i++) {
			SortEvent<T> copy = new CopyEvent<T>(i, temp.get(j));
			sortEventList.add(copy);
			lst.set(i, temp.get(j));
			j++;
		}
	}

	/**
	 * This method recursively sorts the entire array using merge sort
	 * @param lst
	 * @param lo
	 * @param hi
	 * @param sortEventList
	 */
	public static <T extends Comparable<T>> void mergeSortHelper(ArrayList<T> lst, int lo, int hi, ArrayList<SortEvent<T>> sortEventList) {
		if (hi - lo > 1) {
			mergeSortHelper(lst, lo, (lo +hi)/2, sortEventList); 
			mergeSortHelper(lst, (lo+hi)/2, hi, sortEventList);
			merge(lst,lo,(lo+hi)/2,hi, sortEventList);
		}
	}
/**
 * This method performs merge sort on an array
 * @param lst
 * @return a SortEvent object
 */
	public static <T extends Comparable<T>> ArrayList<SortEvent<T>> mergeSort(ArrayList<T> lst) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		mergeSortHelper(lst, 0, lst.size(), sortEventList);
		return sortEventList;
	}

/**
 * This method performs bubble sort on an array
 * @param lst
 * @return a SortEvent object
 */
	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> bubbleSort(ArrayList<T> lst) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < lst.size(); i++ ) {
			for(int j = 1; j < lst.size() - i; j++) {
				SortEvent<T> comEvent = new CompareEvent<T>(j - 1, j);
				sortEventList.add(comEvent);
				if (lst.get(j - 1).compareTo(lst.get(j)) > 0) {
					SortEvent<T> swap = new SwapEvent<T>(j - 1, j);
					sortEventList.add(swap);
					swap(j, j - 1, lst);            
				} 
			}
		} 
		return sortEventList;
	} 

	/**
	 * This method performs the basic sorting activity of quick sort
	 * @param lst
	 * @param lo
	 * @param hi
	 * @param sortEventList
	 * @return
	 */
	public static <T extends Comparable<T>> int partition(ArrayList<T> lst, int lo, int hi, ArrayList<SortEvent<T>> sortEventList)
	{
		T pivot = lst.get(hi); 
		int loptr = lo - 1;

		for (int hiptr = lo; hiptr < hi;hiptr++) {
			SortEvent<T> comEvent = new CompareEvent<T>(hiptr, hi);
			sortEventList.add(comEvent);
			if ((lst.get(hiptr)).compareTo(pivot) < 0) {
				loptr++;
				SortEvent<T> swapEvent = new SwapEvent<T>(loptr, hiptr);
				sortEventList.add(swapEvent);
				swap(loptr, hiptr, lst);  
			}  
		}
		SortEvent<T> swapEvent = new SwapEvent<T>(loptr+1, hi);
		sortEventList.add(swapEvent);
		swap(loptr+1, hi, lst);

		return loptr+1;
	}
	
	/**
	 * This method recursively sorts the entire array using quick sort
	 * @param lst
	 * @param lo
	 * @param hi
	 * @param sortEventList
	 */
	public static <T extends Comparable<T>> void quickSortHelper(ArrayList<T> lst, int lo, int hi, ArrayList<SortEvent<T>> sortEventList)
	{
		if (lo < hi)
		{
			int pi = partition(lst, lo, hi, sortEventList);
			quickSortHelper(lst, lo, pi-1, sortEventList);
			quickSortHelper(lst, pi+1, hi, sortEventList);
		}
	}
/**
 * This method performs quick sort on an array
 * @param lst
 * @return a SortEvent object
 */
	public static <T extends Comparable<T>> ArrayList<SortEvent<T>> quickSort(ArrayList<T> lst) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		quickSortHelper(lst, 0, lst.size() - 1, sortEventList);
		return sortEventList;
	}
	
	/**
	 * This method apply events to the list in-order
	 * @param l
	 * @param events
	 */
	public static <T extends Comparable<T>> void eventSort(ArrayList<T> l, ArrayList<SortEvent<T>> events){
		for(int i = 0; i < events.size(); i++) {
			events.get(i).apply(l);
		}
}
	
}
