public class Variables {
    public double coefficient, degree, X;
    public int Typeofterm;

    public Variables(double coefficient, double degree, double x, int typeofterm) {
        this.coefficient = coefficient;
        this.degree = degree;
        X = x;
        Typeofterm = typeofterm;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public int getTypeofterm() {
        return Typeofterm;
    }

    public void setTypeofterm(int typeofterm) {
        Typeofterm = typeofterm;
    }
}