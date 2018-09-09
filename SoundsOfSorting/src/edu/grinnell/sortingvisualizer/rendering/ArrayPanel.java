package edu.grinnell.sortingvisualizer.rendering;

import edu.grinnell.sortingvisualizer.audio.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    private int width;
    private int height;
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
     * @param notes the indices to render
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
    	this.width = width;
    	this.height = height;
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * This class sets the canvas and specifies the color of each bar
     * @param Grphics g
     */
    @Override
    public void paintComponent(Graphics g) {
    		g.setColor(Color.WHITE);
    		g.fillRect(0, 0, width, height);
    		int barWidth = this.width / notes.getNotes().size();
    		int barHeight = this.height / notes.getNotes().size();
    		for(int i = 0; i < notes.getNotes().size(); i++) {
    			if(notes.isHighlighted(i)) {
    				g.setColor(Color.BLUE);
    			} else {
    				g.setColor(new Color(50, 
    									200 * notes.getNotes().get(i) / notes.getNotes().size(), 
    									 notes.getNotes().get(i) / notes.getNotes().size()));
    			}
    			g.fillRect(i * barWidth, (notes.getNotes().size() - notes.getNotes().get(i) - 1) * barHeight, barWidth, (notes.getNotes().get(i) + 1) * barHeight);
    		}		
    }
}