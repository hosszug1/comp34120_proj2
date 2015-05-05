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

	// Update existing records array with a new set.
	public void updateRecords(ArrayList<Record> records)
	{
		this.records = records;
	} // updateRecords

	// Method that estimates theta0 and theta1 using linear regression
	// and updates the variables.
	public void doLinearRegression()
	{
		int numberOfDays = this.records.size();
		double[] priceLeader = new double[numberOfDays];
		double[] priceFollower = new double[numberOfDays];
		int i = 0;
		for (Record record : this.records)
		{
			priceLeader[i] = record.m_leaderPrice;
			priceFollower[i] = record.m_followerPrice;
		} // for

		LinearRegression lr = new LinearRegression(numberOfDays, 1000, 0.01, priceLeader, priceFollower);
      	double[] thetas = new double[2];
      	thetas = lr.doRegression();

		this.theta0 = thetas[0];
		this.theta1 = thetas[1];
	} // doLinearRegression

	// Method that returns the estimate of the follower.
	public double getFollowerReaction(double price)
	{
		return theta0 + theta1 * price;
	} // getFollowerReaction

	// Method that retuns best strategy for leader based on follower reaction.
	public double findBestStrategy()
	{
		// Formula derived from the payoff (profit) formula using the demand model.
		// (uL-1)(2-uL+0.3uF)
		// The formula is derived and set equal to zero, and uL (best strategy)
		// is deducted from it.
		return (3 + 0.3 * theta0 - 0.3 * theta1) / (2.0 - 0.6 * theta1);
	} // findBestStrategy

} // class FollowerReaction