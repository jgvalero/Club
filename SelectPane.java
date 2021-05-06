//  Description: SelectPane displays a list of available clubs
//  from which a user can select and compute their total number of members.

//Import Packages
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class SelectPane extends BorderPane {
	// Create variables/interfaces
	private ArrayList<Club> clubList;
	private Pane checkBoxesPane;
	Text selectAClub;
	Label totalMembers;

	// constructor
	public SelectPane(ArrayList<Club> list) {
		// initialize instance variables
		this.clubList = list;
		Text selectAClub = new Text("Select a club");
		
		// create an empty pane where you can add check boxes later
		// ----
		checkBoxesPane = new VBox();

		// SelectPane is a BorderPane - add the components here
		// ----
		totalMembers = new Label("The total number of members for the selectect club(s): ");
		this.setTop(selectAClub);
		this.setLeft(checkBoxesPane);
		this.setBottom(totalMembers);

	} // end of constructor

	// This method uses the newly added parameter Club object
	// to create a CheckBox and add it to a pane created in the constructor
	// Such check box needs to be linked to its handler class
	public void updateClubList(Club newClub) {
		CheckBox temp = new CheckBox(newClub.toString());
		checkBoxesPane.getChildren().add(temp);
		SelectionHandler handler = new SelectionHandler();
		temp.setOnAction(handler);

	}

	// create a SelectionHandler class
	private class SelectionHandler implements EventHandler<ActionEvent> {
		// Override the abstact method handle()
		public void handle(ActionEvent event) {
			// When any radio button is selected or unselected
			// the total number of members of selected clubs should be updated
			// and displayed using a label.
			int members = 0;
			ObservableList<Node> list = checkBoxesPane.getChildren();
			for (int i = 0; i < clubList.size(); i++) {
				CheckBox box = (CheckBox) list.get(i);
				if (box.isSelected()) {
					members += ((Club) clubList.get(i)).getNumberOfMembers();
				}

			}
			String membersString = Integer.toString(members);
			totalMembers.setText("The total number of members for the selectect club(s): " + membersString);
		}
	} // end of SelectHandler class
} // end of SelectPane class
