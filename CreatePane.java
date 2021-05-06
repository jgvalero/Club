//  Description: CreatePane generates a pane where a user can enter
//  a club information and create a list of available clubs.

import java.util.ArrayList;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import
import javafx.geometry.Insets;

public class CreatePane extends HBox {
	ArrayList<Club> clubList;

	// The relationship between CreatePane and SelectPane is Aggregation
	// Create variables/interfaces
	private SelectPane selectPane;
	private Label title, numMembers, university;
	private TextField titleInput, numMembersInput, universityInput;
	private TextArea clubArea;
	private Button create;
	private Text status;

	// constructor
	public CreatePane(ArrayList<Club> list, SelectPane sePane) {
		this.clubList = list;
		this.selectPane = sePane;

		// initialize each instance variable (textfields, labels, textarea, button,
		// etc.)
		// and set up the layout
		// ----
		title = new Label("Title");
		numMembers = new Label("Number of Members");
		university = new Label("University");
		titleInput = new TextField();
		numMembersInput = new TextField();
		universityInput = new TextField();
		clubArea = new TextArea();
		clubArea.setText("No Club");
		clubArea.setEditable(false);
		create = new Button("Create a Club");
		status = new Text(10, 20, "");
		status.setFill(Color.RED);

		// create a GridPane hold those labels & text fields.
		// you can choose to use .setPadding() or setHgap(), setVgap()
		// to control the spacing and gap, etc.
		// ----
		GridPane clubInputPane = new GridPane();
		clubInputPane.add(title, 0, 0);
		clubInputPane.add(titleInput, 1, 0);
		clubInputPane.add(numMembers, 0, 1);
		clubInputPane.add(numMembersInput, 1, 1);
		clubInputPane.add(university, 0, 2);
		clubInputPane.add(universityInput, 1, 2);
		clubInputPane.setPadding(new Insets(5, 25, 25, 25));
		Pane textPane = new Pane();
		textPane.getChildren().add(status);

		// Create a sub pane to hold the button
		// ----
		BorderPane createClubButtonPane = new BorderPane();
		createClubButtonPane.setCenter(create);

		// Set up the layout for the left half of the CreatePane.
		// ----
		VBox clubCreationPane = new VBox();
		clubCreationPane.getChildren().addAll(textPane, clubInputPane, createClubButtonPane);

		// the right half of the CreatePane is simply a TextArea object
		// Note: a ScrollPane will be added to it automatically when there are no
		// enough space
		
		//Removed this part because it makes the clubArea text field smaller
		//Pane textAreaPane = new Pane();
		//textAreaPane.getChildren().add(clubArea);

		// Add the left half and right half to the CreatePane
		// Note: CreatePane extends from HBox
		// ----
		this.getChildren().addAll(clubCreationPane, clubArea);

		// register/link source object with event handler
		// ----
		ButtonHandler handler = new ButtonHandler();
		create.setOnAction(handler);

	} // end of constructor

	// Create a ButtonHandler class
	// ButtonHandler listens to see if the button "Create" is pushed or not,
	// When the event occurs, it get a club's Title, its number of members, and its
	// university
	// information from the relevant text fields, then create a new club and add it
	// inside
	// the clubList. Meanwhile it will display the club's information inside the
	// text area.
	// using the toString method of the Club class.
	// It also does error checking in case any of the textfields are empty,
	// or a non-numeric value was entered for its number of members
	private class ButtonHandler implements EventHandler<ActionEvent> {
		// Override the abstact method handle()
		public void handle(ActionEvent event) {
			// declare any necessary local variables here
			// ---
			int counter = 0;
			String getTitle = titleInput.getText();
			String getMembers = numMembersInput.getText();
			String getUniversity = universityInput.getText();
			String clubAreaText = clubArea.getText();
			
			if (clubAreaText.equalsIgnoreCase("No Club")) {
				clubArea.clear();
			}

			// when a text field is empty and the button is pushed
			if (getTitle.isEmpty() || getMembers.isEmpty() || getUniversity.isEmpty()) {
				status.setText("Please fill all fields");
			} else {
				// when a non-numeric value was entered for its number of members
				// and the button is pushed
				// you will need to use try & catch block to catch
				// the NumberFormatException
				try {

					int numberOfMembers = Integer.parseInt(getMembers);
					Club club = new Club();
					club.setClubName(getTitle);
					club.setNumberOfMembers(numberOfMembers);
					club.setUniversity(getUniversity);
					
					// When a club of an existing club name in the list
					// was attempted to be added, do not add it to the list
					// and display a message "Club not added - duplicate"
					for (int i = 0; i < clubList.size(); i++) {
						String clubTitle = clubList.get(i).getClubName();
						if (getTitle.equalsIgnoreCase(clubTitle)) {
							counter++;
						}
					}
					if (counter != 0) {
						status.setText("Club not added - duplicate");
					}
					else {
						status.setText("Club added");
						clubList.add(club);
						clubArea.appendText(club.toString());
						selectPane.updateClubList(club);
					}
					// at the end, don't forget to update the new arrayList
					// information on the SelectPanel

				} catch (NumberFormatException ex) {
					status.setText("Please enter an integer for number of members");
				}

			}

		} // end of handle() method
	} // end of ButtonHandler class

}
