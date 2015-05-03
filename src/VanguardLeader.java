import comp34120.ex2.PlayerImpl;
import comp34120.ex2.PlayerType;
import comp34120.ex2.Record;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Leader bot for the Stackelberg game by team Vanguard.
 * @author Gabor
 */
final class VanguardLeader
	extends PlayerImpl
{
	/**
	 * In the constructor, you need to call the constructor
	 * of PlayerImpl in the first line, so that you don't need to
	 * care about how to connect to the platform. You may want to throw
	 * the two exceptions declared in the prototype, or you may handle it
	 * by using "try {} catch {}". It's all up to you.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	VanguardLeader()
		throws RemoteException, NotBoundException
	{
		/* The first parameter *MUST* be PlayerType.LEADER, you can change
		 * the second parameter, the name of the leader, such as "My Leader" */
		super(PlayerType.LEADER, "Vanguard Leader");
	} // VanguardLeader

	@Override
	public void goodbye()
		throws RemoteException
	{
		super.goodbye();
	} // goodbye

	/**
	 * You may want to delete this method if you don't want to do any
	 * initialization
	 * @param p_steps Indicates how many steps will the simulation perform
	 * @throws RemoteException If implemented, the RemoteException *MUST* be
	 * thrown by this method
	 */
	@Override
	public void startSimulation(int p_steps)
		throws RemoteException
	{
		super.startSimulation(p_steps);
	} // startSimulation

	/**
	 * You may want to delete this method if you don't want to do any
	 * finalization
	 * @throws RemoteException If implemented, the RemoteException *MUST* be
	 * thrown by this method
	 */
	@Override
	public void endSimulation()
		throws RemoteException
	{
		super.endSimulation();
		// Print the total profit gained by the Leader.
		System.out.println("Total profit: " + getTotalProfit());
	} // endSimulation

	/**
	 * To inform this instance to proceed to a new simulation day
	 * @param p_date The date of the new day
	 * @throws RemoteException This exception *MUST* be thrown by this method
	 */
	@Override
	public void proceedNewDay(int p_date)
		throws RemoteException
	{
		// Check for new price.
		Record l_newRecord = m_platformStub.query(m_type, p_date);
		
		// Calculate the new price.
		float l_newPrice = getPrice(p_date);
		
		// Submit the new price, and end the phase.
		m_platformStub.publishPrice(m_type, l_newPrice);
	} // proceedNewDay

	// Get the price for the new day.
	private float getPrice(int p_date) throws RemoteException
	{
		// Get reaction function of the follower by doing regression on the history.
		// ----------------------------------------------------------------------------
		// Regression parameter - represents the number of days to look at.
		int numOfDays = 50;
		//100, 50
		FollowerReaction newReaction = new FollowerReaction(getRecords(p_date, numOfDays));

		// Linear regression.
		newReaction.doLinearRegression();

		// Get the price from the strategy/price space that has global maximum profit.
		// ----------------------------------------------------------------------------
		float price = (float) newReaction.findBestStrategy();

		return price;
	} // getPrice

	// Method that returns the total profit gained over the 30 days of simulation.
	private double getTotalProfit()
		throws RemoteException
	{
		double totalProfit = 0.0;

		// Loop through the 30 days and calculate profit.
		for (int i = 101; i <= 130; i++)
		{
			Record record = m_platformStub.query(m_type, i);
			// Update total profit gained so far.
			totalProfit += (record.m_leaderPrice - 1) * (2 - record.m_leaderPrice + 0.3 * record.m_followerPrice);
		} // for

		return totalProfit;
	} // getTotalProfit

	// Method to get all the records up this current date.
	private ArrayList<Record> getRecords(int currentDay, int totalDays)
		throws RemoteException
	{
		ArrayList<Record> records = new ArrayList();

		for (int i = currentDay - totalDays; i < currentDay; i++)
		{
			records.add(m_platformStub.query(m_type, i));
		} // for
		
		return records;
	} // getAllRecords

	public static void main(final String[] p_args)
		throws RemoteException, NotBoundException
	{
		new VanguardLeader();
	} // main

} // class VanguardLeader