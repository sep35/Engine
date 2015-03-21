package gameManager;

import java.util.Iterator;

public class TeamList implements Iterable<Team>, ITeamList {
	
	private Node Head; 
	private Node currActive; 
	private Node Tail; 
	private int TeamCount; 
	
	/*public TeamList(){
		this.Head = null;
		this.currActive = null;
		this.Tail = null;
		this.TeamCount = 1;
	}*/
	private Team getIndex(int index){
		Node temp = Head; 
		while( temp.index != index){
			temp = temp.next; 
		}
		return temp.myTeam; 
	}
	protected boolean isEmpty(){
		return Head==null; 
	}
	
	protected Team getHeadTeam(){
		return Head.myTeam; 
	}
	
	/* (non-Javadoc)
	 * @see gameManager.ITeamList#addTeam(gameManager.Team)
	 */
	public void addTeam(Team aTeam){
		if(Head== null){
			Head = new Node(aTeam, null, null, TeamCount); 
			Tail = Head;
			currActive = Head; 
			TeamCount++; 
		}else{
			Node temp = new Node(aTeam, null, null, TeamCount); 
			Tail.next = temp; 
			Tail = temp; 
			Tail.next = Head; 
			TeamCount ++; 
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see gameManager.ITeamList#getActiveTeam(boolean)
	 */
	public Team getActiveTeam(boolean getNext){ 
		if(Head== null ) return null; 
		else if(TeamCount == 1)  {
			currActive = Head; 
			return currActive.myTeam; 
			} 
		else if(getNext){ 
			currActive = currActive.next; 
			return currActive.myTeam;
		} 
		else return currActive.myTeam; 
	}
	
	
	/* (non-Javadoc)
	 * @see gameManager.ITeamList#iterator()
	 */
	@Override
	public Iterator<Team> iterator() {
		return new myIterator(); 
	}
	
	
	private class Node{ 
		Node next; 
		Team myTeam;
		int index; 
		public Node(Team myT, Node p, Node n, int ind){
			myTeam = myT; 
			next = n;
			index = ind; 
		}

	}

	
	public class myIterator implements Iterator<Team>{

		int itercounter = -1;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (itercounter < TeamCount-1);
		}

		@Override
		public Team next() {
			// TODO Auto-generated method stub
			itercounter++; 
			return  getIndex(itercounter);
		}
		
	}
	
	
//	public static void main(String[] args){
//		
//		WinningCondition tester = new WinningCondition(3, "and", "and"); 
//		TeamList temp = new TeamList(); 
//		Team test1 = new Team("Kervin", tester); 
//		Team test2 = new Team("Stervern", tester); 
//		Team test3 = new Team("Erikar", tester); 
//		Team test4 = new Team("Tarnarkar", tester); 
//		Team test5 = new Team("Dirmerjir", tester);
//		temp.addTeam(test1);
//		temp.addTeam(test2);
//		temp.addTeam(test3);
//		temp.addTeam(test4);
//		temp.addTeam(test5);
//		for(int i = 0; i < 30; i++){
//		System.out.println( temp.getActiveTeam(true));
//		}
//		 
//		for( Team t : temp){
//			 
//			System.out.println(t); 
// 		}
//		
//		System.out.println("blue"); 
//	} 
	
	
}
