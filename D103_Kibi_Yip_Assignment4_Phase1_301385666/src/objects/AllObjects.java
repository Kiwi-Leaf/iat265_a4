package objects;

import java.awt.Graphics2D;
import java.awt.Image;

import processing.core.PVector;

public abstract class AllObjects {
	protected PVector pos;
	protected float scale;
	protected int worldState;
	protected int state;
	protected boolean taskCompleted;
	protected int playerItem=-1;
	protected int targetItem;
	protected Image activeImage;
	
	// ------------worldState-------------
	protected final int TITLE = 0;
	protected final int MORNING = 1;
	protected final int AFTERNOON = 2;
	protected final int EVENING = 3;
	protected final int END = 4;
	
	//------------item Number-------------
	protected final int DOOR=1;
	protected final int SIGN=1;
	protected final int ROSE=2;
	protected final int FLAX=3;
	protected final int WATERING_CAN=4;
	protected final int END_SIGN=1;

	//-----------------------------------
	public AllObjects(float x, float y) {
		pos= new PVector(x,y);
		scale=1;
	}
	//-----------------------------------
	
	public abstract void draw(Graphics2D g);
	//-----------------------------------
	protected abstract void stateScript(int state);
	//-----------------------------------
	public boolean getCritTask(boolean task) {
		return task;
	}
	//-----------------------------------
	public int getState() {
		return state;
	}
	//-----------------------------------
	public void update(int worldState) {
		
	}
	public boolean getTaskCompleted() {
		return taskCompleted;
	}
	public void setTaskCompleted(boolean x) {
		taskCompleted=x;
	}
	public PVector getPos() {
		return pos;
	}
	public float getWidth() {
		return activeImage.getWidth(null);
	}
	
}
