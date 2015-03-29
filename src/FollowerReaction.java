package comp34120.ex2;

public class FollowerReaction
{
	private double theta0;
	private double theta1;

	// ArrayList to hold the records of the days (will contain leader & follower prices).
	private ArrayList<Record> records;

	
	// Constructor.
	public FollowerReaction(ArrayList<Record> records)
	{
		this.records = records;
	} // FollowerReaction


	// Method that estimate theta0 and theta1 using linear regression
	// and updates the variables.
	public doLinearRegression(int numberOfDays)
	{
		// ... all the mathematics here.

		// theta0 = newValue;
		// theta1 = newValue;
	} // doLinearRegression

} // class FollowerReaction