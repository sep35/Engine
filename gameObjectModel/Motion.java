package gameObjectModel;

public class Motion {

	private boolean hasHorizontalMotion; 
	private boolean hasVerticalMotion; 
	private boolean hasDiagonalMotion; 
	private boolean canJump; 
	private int moveDistance; 
	
	public Motion( boolean hori, boolean vert, boolean diag, boolean jump, int distance ){
		hasHorizontalMotion = hori; 
		hasVerticalMotion = vert; 
		hasDiagonalMotion = diag; 
		canJump = jump; 
		moveDistance = distance; 
	}
	
	public boolean hasHorizontalMotion(){
		return hasHorizontalMotion; 
	}
	
	public boolean hasVerticalMotion(){
		return hasVerticalMotion; 
	}
	
	
	public boolean hasDiagonalMotion(){
		return hasDiagonalMotion; 
	}
	
	public boolean canJump(){
		return canJump; 
	}
	
	public int moveDistance(){
		return moveDistance; 
	}
}
