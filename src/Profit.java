package comp34120.ex2;
public class Profit 
{
	private double a, b;

	public Payoff(double a, double b) 
  	{
      	this.a = a;
      	this.b = b;
    } // Payoff

  	public double followerEstimate(double leaderPrice) 
  	{
    	return this.a + this.b * leaderPrice;
  	} // followerEstimate

	public float globalMaximum() 
	{
		return ((2.7 + 0.3 * this.a) / (2.0 - 0.6 * this.b));
	} // globalMaximum
} // class Profit
