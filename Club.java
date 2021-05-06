//  Description: The class Club represents a Club.

public class Club {
	private String clubName;
	private int numberOfMembers;
	private String university;

	// Constructor to initialize all member variables
	public Club() {
		clubName = "?";
		university = "?";
		numberOfMembers = 0;
	}

	// Accessor method for club name
	public String getClubName() {
		return clubName;
	}

	// Accessor method for university
	public String getUniversity() {
		return university;
	}

	// Accessor method for number of members
	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	// mutator/modifider method for club name
	public void setClubName(String someClubName) {
		clubName = someClubName;
	}

	// mutator/modifider method for university
	public void setUniversity(String someUniversity) {
		university = someUniversity;
	}

	// mutator/modifider method for number of members
	public void setNumberOfMembers(int someNumber) {
		numberOfMembers = someNumber;
	}

	// toString() method returns a string containg its name, number of members, and
	// university
	public String toString() {
		String result = "\nClub Name:\t\t" + clubName + "\nNumber Of Members:\t" + numberOfMembers + "\nUniversity:\t\t"
				+ university + "\n\n";
		return result;
	}
}
