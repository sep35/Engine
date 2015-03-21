package boardObjectModels;
public class DestinationSearch {
	
	private int xDimension; 
	private int yDimension; 
	private Board myBoard;  
	
	public DestinationSearch(ObservableBoardHolder myBoardHolder){
		myBoard = myBoardHolder.getBoard(); 
		xDimension = myBoard.getPatchList().size(); 
		yDimension = myBoard.getPatchList().get(0).size(); 
		
	}
	
	public DestinationSearch(Board Board){
		myBoard = Board; 
		xDimension = myBoard.getPatchList().size(); 
		yDimension = myBoard.getPatchList().get(0).size(); 
		
	}
	public boolean searchWithoutJump(int xorig, int yorig,int xdest, int ydest, int dist ){
		//distance = dist;  
		if( xorig >= xDimension || xorig <0 || yorig >= yDimension || yorig < 0) return false; 
		if( !myBoard.isPatchEmpty(xorig, yorig)) return  false; // set the originating patch to be empty or face 
		//the consequences
		if(dist == 0 && !(xorig == xdest && yorig == ydest)) return  false;
		if( xorig == xdest && yorig == ydest) return true; 
		//System.out.println( xorig+ " "+ yorig +" "+ xdest +" " +ydest+" "+ dist); 
		return  searchWithoutJump(xorig -1, yorig, xdest,ydest, dist -1)||// recurse left
				searchWithoutJump( xorig +1, yorig, xdest, ydest, dist -1)||// recurse right
				searchWithoutJump( xorig, yorig-1, xdest, ydest, dist -1)||// recurse down
				searchWithoutJump( xorig, yorig+1, xdest, ydest, dist -1)||// recurse up
				searchWithoutJump( xorig+1, yorig+1, xdest, ydest, dist -1)||//recurse upper right diag 
				searchWithoutJump( xorig-1, yorig+1, xdest, ydest, dist -1)||//recurse  upper left diag
				searchWithoutJump( xorig-1, yorig-1, xdest, ydest, dist -1)||//recurse  lower left diag
				searchWithoutJump( xorig+1, yorig-1, xdest, ydest, dist -1); //recurse  lower right diag
	}
	
	public boolean searchWithJump(int xorig, int yorig,int xdest, int ydest, int dist ){
		if( xorig >= xDimension || xorig <0 || yorig >= yDimension || yorig < 0) return false; 
		if(dist == 0 && !(xorig == xdest && yorig == ydest)) return  false;
		if( xorig == xdest && yorig == ydest) return true; 
		//System.out.println( xorig+ " "+ yorig +" "+ xdest +" " +ydest+" "+ dist); 
		return  searchWithoutJump(xorig -1, yorig, xdest,ydest, dist -1)||// recurse left
				searchWithoutJump( xorig +1, yorig, xdest, ydest, dist -1)||// recurse right
				searchWithoutJump( xorig, yorig-1, xdest, ydest, dist -1)||// recurse down
				searchWithoutJump( xorig, yorig+1, xdest, ydest, dist -1)||// recurse up
				searchWithoutJump( xorig+1, yorig+1, xdest, ydest, dist -1)||//recurse upper right diag 
				searchWithoutJump( xorig-1, yorig+1, xdest, ydest, dist -1)||//recurse  upper left diag
				searchWithoutJump( xorig-1, yorig-1, xdest, ydest, dist -1)||//recurse  lower left diag
				searchWithoutJump( xorig+1, yorig-1, xdest, ydest, dist -1); //recurse  lower right diag
	}
	
//	public static void main(String[] args){
//		List<Integer> temp = new ArrayList<>(); 
//		List<Integer> setter = new ArrayList<>(); 
//		temp.add(3); 
//		temp.add(3); 
//		setter.add(1); 
//		setter.add(1); 
//		GameObject blue = new GameObject(null, null, null, null); 
//		Board toSearch = new Board(temp);
//		
//		//toSearch.setGameObject(blue, setter); 
//		FourWaySearch searcher = new FourWaySearch(toSearch); 
//		
//		System.out.println( searcher.search(0, 0, 2, 2, 2)); 
//		
//	}	
	

}
