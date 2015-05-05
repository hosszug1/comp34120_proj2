public class Matrix 
{
    // Returns a random n-by-m matrix.
    public static double[][] random(int n, int m)
    {
        double[][] result = new double[m][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[i][j] = Math.random();
        return result;
    } // random

    // Returns an n-by-n identity matrix.
    public static double[][] identity(int n)
    {
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++)
            result[i][i] = 1;
        return result;
    } // identity

    // Returns the transpose of the supplied matrix.
    public static double[][] transpose(double[][] aMatrix)
    {
        int n = aMatrix.length;
        int m = aMatrix[0].length;
        double[][] result = new double[m][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[j][i] = aMatrix[i][j];
        return result;
    } // transpose

    // Returns the addition of two matrices.
    public static double[][] add(double[][] matrixA, double[][] matrixB)
    {
        int n = matrixA.length;
        int m = matrixA[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[i][j] = matrixA[i][j] + matrixB[i][j];
        return result;
    } // add

    // Returns the addition of two linear matrices.
    public static double[] add(double[] matrixA, double[] matrixB)
    {
        int n = matrixA.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++)
                result[i] = matrixA[i] + matrixB[i];
        return result;
    } // add

    // Returns the subtraction of two matrices.
    public static double[][] subtract(double[][] matrixA, double[][] matrixB)
    {
        int n = matrixA.length;
        int m = matrixA[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[i][j] = matrixA[i][j] - matrixB[i][j];
        return result;
    } // subtract

    // Returns the subtraction of two linear matrices.
    public static double[] subtract(double[] matrixA, double[] matrixB)
    {
        int n = matrixA.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++)
                result[i] = matrixA[i] - matrixB[i];
        return result;
    } // subtract

    // Returns the matrix-multiplication of two matrices.
    public static double[][] multiply(double[][] matrixA, double[][] matrixB)
    {
        int nA = matrixA.length;
        int mA = matrixA[0].length;
        int nB = matrixB.length;
        int mB = matrixB[0].length;
        if (mA != nB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] result = new double[nA][mB];
        for (int i = 0; i < nA; i++)
            for (int j = 0; j < mB; j++)
                for (int k = 0; k < mA; k++)
                    result[i][j] += (matrixA[i][k] * matrixB[k][j]);
        return result;
    } // multiply

    // Returns the matrix-multiplication of a matrix with a vector (linear matrix).
    public static double[] multiply(double[][] aMatrix, double[] aVector)
    {
        int n = aMatrix.length;
        int m = aMatrix[0].length;
        if (aVector.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] result = new double[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result[i] += (aMatrix[i][j] * aVector[j]);
        return result;
    } // multiply

    // Returns the matrix-multiplication of a vector (linear matrix) with a matrix.
    public static double[] multiply(double[] aVector, double[][] aMatrix)
    {
        int n = aMatrix.length;
        int m = aMatrix[0].length;
        if (aVector.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] result = new double[m];
        for (int j = 0; j < m; j++)
            for (int i = 0; i < n; i++)
                result[j] += (aMatrix[i][j] * aVector[i]);
        return result;
    } // multiply

    // Returns the scalar-multiplication of a matrix with a scalar.
    public static double[][] multiply(double[][] aMatrix, double aScalar)
    {
        int n = aMatrix.length;
        int m = aMatrix[0].length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                aMatrix[i][j] = (aMatrix[i][j] * aScalar);
        return aMatrix;
    } // multiply

    // Returns the scalar-multiplication of a vector (linear matrix) with a scalar.
    public static double[] multiply(double[] aVector, double aScalar)
    {
        int n = aVector.length;
        for (int i = 0; i < n; i++)
                aVector[i] = (aVector[i] * aScalar);
        return aVector;
    } // multiply

} // class Matrix