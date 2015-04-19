public class LinearRegression {

  public int numberOfDays, numberOfIterations;
  public double learningRate;
  public double[][] priceLeader;
  public double[] priceFollower;
  public double[] theta;
  
  public LinearRegression(int numberOfDays, int numberOfIterations, double learningRate, double[] priceLeader, double[] priceFollower) {
    this.numberOfDays = numberOfDays;
    this.numberOfIterations = numberOfIterations;
    this.learningRate = learningRate;
    
    //prepare theta
    this.theta = new double[2];
    this.theta[0] = 0; this.theta[1] = 0;

    //prepare price matrices
    this.priceLeader = new double[numberOfDays][2];
    // this.priceFollower = new double[numberOfDays];

    // this.priceLeader = priceLeader;
    this.priceFollower = priceFollower;

    for (int i = 0; i < numberOfDays; i++) {
      this.priceLeader[i][0] = 1.0;
      this.priceLeader[i][1] = priceLeader[i];
    }

  }

  public double[] doRegression() {
    // getPriceData();

    // double[] j = new double[2];

    //this is the initial cost
    //at this point theta is (0,0) and the hypothesis will be 0
    //the error will be maximum at this point
    // j = computeCost(this.priceLeader, this.priceFollower, this.theta);
    this.theta = gradientDescent(this.priceLeader, this.priceFollower, this.theta, this.learningRate, this.numberOfIterations);

    // System.out.println("theta0 : " + this.theta[0] + " theta1 : " + this.theta[1]);

    return this.theta;

  }

  //hardcoded at the moment
  //TODO change function to get data from the platform
  public void getPriceData() {
    /*
    for (i=this.numberOfDays; i>0; i--) {
      platform.get( today - i )
    }
     */
  
    double[][] helper = {
      {1.0, 1.784},
      {1.0, 1.779},
      {1.0, 1.881},
      {1.0, 1.837},
      {1.0, 1.879},
      {1.0, 1.856},
      {1.0, 1.769},
      {1.0, 1.735},
      {1.0, 1.806},
      {1.0, 1.890},
      {1.0, 1.867},
      {1.0, 1.836},
      {1.0, 1.795},
      {1.0, 1.715},
      {1.0, 1.725},
      {1.0, 1.797},
      {1.0, 1.813},
      {1.0, 1.886},
      {1.0, 1.818},
      {1.0, 1.759},
      {1.0, 1.722},
      {1.0, 1.829},
      {1.0, 1.862},
      {1.0, 1.733},
      {1.0, 1.840},
      {1.0, 1.781},
      {1.0, 1.828},
      {1.0, 1.859},
      {1.0, 1.746},
      {1.0, 1.864},
      {1.0, 1.884},
      {1.0, 1.794},
      {1.0, 1.757},
      {1.0, 1.732},
      {1.0, 1.786},
      {1.0, 1.803},
      {1.0, 1.883},
      {1.0, 1.743},
      {1.0, 1.722},
      {1.0, 1.809},
      {1.0, 1.716},
      {1.0, 1.819},
      {1.0, 1.745},
      {1.0, 1.708},
      {1.0, 1.804},
      {1.0, 1.763},
      {1.0, 1.710},
      {1.0, 1.884},
      {1.0, 1.773},
      {1.0, 1.863},
      {1.0, 1.854},
      {1.0, 1.897},
      {1.0, 1.754},
      {1.0, 1.879},
      {1.0, 1.897},
      {1.0, 1.832},
      {1.0, 1.769},
      {1.0, 1.734},
      {1.0, 1.731},
      {1.0, 1.751},
      {1.0, 1.720},
      {1.0, 1.762},
      {1.0, 1.739},
      {1.0, 1.769},
      {1.0, 1.898},
      {1.0, 1.829},
      {1.0, 1.764},
      {1.0, 1.810},
      {1.0, 1.716},
      {1.0, 1.871},
      {1.0, 1.872},
      {1.0, 1.899},
      {1.0, 1.809},
      {1.0, 1.809},
      {1.0, 1.739},
      {1.0, 1.832},
      {1.0, 1.859},
      {1.0, 1.817},
      {1.0, 1.869},
      {1.0, 1.709},
      {1.0, 1.832},
      {1.0, 1.778},
      {1.0, 1.743},
      {1.0, 1.848},
      {1.0, 1.753},
      {1.0, 1.735},
      {1.0, 1.855},
      {1.0, 1.893},
      {1.0, 1.767},
      {1.0, 1.852},
      {1.0, 1.827},
      {1.0, 1.736},
      {1.0, 1.715},
      {1.0, 1.873},
      {1.0, 1.736},
      {1.0, 1.703},
      {1.0, 1.823},
      {1.0, 1.885},
      {1.0, 1.866},
      {1.0, 1.776}
    };
    
    this.priceLeader = helper;
    
    double[] helper2 = {
      1.746,
      1.750,
      1.766,
      1.813,
      1.770,
      1.742,
      1.799,
      1.748,
      1.782,
      1.787,
      1.795,
      1.783,
      1.739,
      1.726,
      1.756,
      1.781,
      1.819,
      1.730,
      1.763,
      1.818,
      1.730,
      1.779,
      1.745,
      1.770,
      1.745,
      1.744,
      1.742,
      1.777,
      1.777,
      1.815,
      1.828,
      1.771,
      1.732,
      1.771,
      1.762,
      1.796,
      1.802,
      1.735,
      1.764,
      1.765,
      1.756,
      1.788,
      1.754,
      1.772,
      1.727,
      1.811,
      1.766,
      1.760,
      1.810,
      1.804,
      1.787,
      1.811,
      1.813,
      1.794,
      1.786,
      1.779,
      1.746,
      1.797,
      1.722,
      1.766,
      1.736,
      1.722,
      1.764,
      1.751,
      1.767,
      1.806,
      1.776,
      1.779,
      1.711,
      1.773,
      1.771,
      1.779,
      1.794,
      1.749,
      1.762,
      1.775,
      1.811,
      1.772,
      1.780,
      1.735,
      1.817,
      1.730,
      1.772,
      1.774,
      1.819,
      1.795,
      1.792,
      1.810,
      1.710,
      1.745,
      1.790,
      1.779,
      1.721,
      1.798,
      1.721,
      1.733,
      1.806,
      1.842,
      1.816,
      1.758
    };

    this.priceFollower = helper2;
  }


  public double computeCost(double[][] x, double[] y, double[] theta) {
    int m = y.length;

    double result = 0;

    double[] hypothesis = new double[this.numberOfDays];
    double[] squaredErrors = new double[this.numberOfDays];

    //estimate the follower's price using theta0 and theta1
    hypothesis = MatrixT.multiply(x, theta);
    squaredErrors = MatrixT.subtract(hypothesis, y);
    //matrix multiplication between x and theta
    for (int i = 0; i<this.numberOfDays; i++) {
      // hypothesis[i] = x[i][0] * theta[0] + x[i][1] * theta[1];

      //compute the squaredError between the hypothesis and the real price given by the follower
      squaredErrors[i] = Math.pow(squaredErrors[i], 2);
    }

    //sum all the squared errors
    double sumSquaredErrors = 0.0;
    for (double error : squaredErrors) {
      sumSquaredErrors += error;
    }

    result = (1 / (2 * m)) * sumSquaredErrors;
    return result;
  }


  /* MIEZUL */
  public double[] gradientDescent(double[][] x, double[] y, double[] theta, double learningRate, int numberOfIterations) {
    double[] newThetas = new double[2];
    newThetas = theta;

    int m = y.length;
    double[] hypothesis = new double[this.numberOfDays];
    double[] diffHY = new double[this.numberOfDays];
    double[] xTransXdiffHY = new double[this.numberOfDays];
    double[][] xTranspose = MatrixT.transpose(x);

    for (int i=0; i<numberOfIterations; i++) {
  
      hypothesis = MatrixT.multiply(x, theta);
      diffHY = MatrixT.subtract(hypothesis, y);
      xTransXdiffHY = MatrixT.multiply(xTranspose, diffHY);
      newThetas = MatrixT.subtract(newThetas, MatrixT.multiply(xTransXdiffHY, learningRate * (1.0 / m)));

    }

    return newThetas;
  }

}