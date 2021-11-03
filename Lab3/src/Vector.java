public class Vector {
    private final double coefficientI;
    private final double coefficientJ;
    private final double coefficientK;

    public Vector(double coefficientI, double coefficientJ, double coefficientK)
    {
        this.coefficientI = coefficientI;
        this.coefficientJ = coefficientJ;
        this.coefficientK = coefficientK;
    }

    public double getCoefficientI() {
        return coefficientI;
    }

    public double getCoefficientJ() {
        return coefficientJ;
    }

    public double getCoefficientK() {
        return coefficientK;
    }

    @Override
    public String toString()
    {
        return " = " + this.coefficientI + "i + " + this.coefficientJ + "j + " + this.coefficientK + "k" ;
    }
}