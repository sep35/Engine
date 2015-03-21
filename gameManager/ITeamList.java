package gameManager;

import java.util.Iterator;

public interface ITeamList {

	void addTeam(Team aTeam);

	Team getActiveTeam(boolean getNext);

	Iterator<Team> iterator();

}