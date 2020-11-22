package chararcters;

import java.util.ArrayList;

import objects.AllObjects;

public abstract class Characters extends AllObjects {
	
	
	protected ArrayList<String> dialogueScript = new ArrayList<String>();
	protected int worldState; 
	// ====States=====
	
	
	// ----------------------------------------

	public Characters(float x, float y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}

	// ---------------------------------------
	public abstract String dialogue();

	// --------------------------------
	public void setDialogue() {
		dialogueScript = new ArrayList<String>();
	}
}
