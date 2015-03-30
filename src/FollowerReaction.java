import comp34120.ex2.PlayerImpl;
import comp34120.ex2.PlayerType;
import comp34120.ex2.Record;
import java.util.ArrayList;

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
		theta0 = 1.0;
		theta1 = 1.0;
	} // FollowerReaction


	// Method that estimate theta0 and theta1 using linear regression
	// and updates the variables.
	public void doLinearRegression()
	{
		// ... all the mathematics here.

		// theta0 = newValue;
		// theta1 = newValue;
	} // doLinearRegression


	// Method that returns the estimate of the follower.
	public double getFollowerReaction(double price)
	{
		return theta0 + theta1 * price;
	} // getFollowerReaction


	// Method that retuns best strategy for leader based on follower reaction.
	public double findBestStrategy()
	{
		return (2.7 + 0.3 * theta0) / (2.0 - 0.6 * theta1);
	} // findBestStrategy()

} // class FollowerReaction