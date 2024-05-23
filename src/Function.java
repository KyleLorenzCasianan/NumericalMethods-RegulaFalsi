import java.text.DecimalFormat;

public class Function {
    double x0,x1,fx0,fx1,x2,fx2, Ea;


    int numberofTerms;
    String[] terms = new String[100];
    Variables[] variables = new Variables[100];
    private DecimalFormat df = new DecimalFormat("#.####");

    public double SubstituteX(double coefficient, double degree, double x, int type){
        switch (type){
            case 1:
                return coefficient * (Math.pow(x, degree));
            case 2:
                return coefficient * Math.sin(Math.pow(x, degree));
            case 3:
                return coefficient * (Math.pow(Math.cos(x), degree));
            case 4:
                return coefficient * (Math.pow(Math.tan(x), degree));
            case 5:
                return coefficient * (Math.exp(x));
            case 6:
                return  coefficient * (Math.sqrt(x));
        }
        return -1;
    }
    public double FindFx(Variables variables[], double X){
        double Fx = 0;
        for(int i = 0; i < numberofTerms; i++){
                variables[i].setX(X);

            Fx += SubstituteX(variables[i].getCoefficient(),variables[i].getDegree(), variables[i].getX(), variables[i].getTypeofterm());
        }
        return Fx;
    }
    public void Stringplacer(String string){
        for(int i = 0; i <= numberofTerms; i++){
            if(terms[i] == null){
                terms[i] = string;
                return;
            }
        }
    }
    public void Variableplacer(double coefficient, double degree, double x, int k){
        for(int i = 0; i <= numberofTerms; i++){
            if(variables[i] == null){
                Variables temporary = new Variables(coefficient,degree,x, k);
                variables[i] = temporary;
                return;
            }
        }
    }
    public void GetStringTerm(double coefficient, double degree, double x, int k){
        int choice = k;
        String string;
        String coefficientstring = Double.toString(coefficient);
        String degreestring = Double.toString(degree);

        switch (choice){
            case 1:
                string = coefficientstring+"x^"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);
                break;
            case 2:
                string = coefficientstring+"Sin x^"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);
                break;

            case 3:
                string = coefficientstring+"Cos x^"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);
                break;

            case 4:
                string = coefficientstring+"Tan x^"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);
                break;

            case 5:
                string = coefficientstring+"e^x"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);

                break;

            case 6:
                string = coefficientstring+"sqrt(x)^"+degreestring;
                Stringplacer(string);
                Variableplacer(coefficient, degree, x, k);

                break;

        }

//        return (coefficient * (Math.pow(x,degree)));
    }
    public void TableDisplay(int lowerboundary, int higherboundary){
        int range = higherboundary - (lowerboundary);
        int valuedisplay = lowerboundary;
        Variables[] temporaryvariable = variables;
        for(int i = 0; i <= range; i++){
            System.out.print("|   " + valuedisplay + "   ");
            valuedisplay++;
        }

        System.out.println();

        for(int i = 0; i <= range; i++){

            double fx = FindFx(temporaryvariable, lowerboundary);
            System.out.print(df.format(fx) + "  ");
            lowerboundary++;

        }
    }

    public double TerminatingCondition(double Newx2, double Oldx2){
        return Math.abs(Newx2 - Oldx2);
    }

    public double NewRegulaFalsiX(double x0, double fx0, double x1, double fx1){
        return x0 - ( fx0 * ((x1-x0) / (fx1 -fx0)) );
    }

    public double RegulaFalsiIteration(double x0, double x1, int iteration){
        this.x0 = x0;
        this.x1 = x1;
        fx0 = this.FindFx(variables, x0);
        fx1 = this.FindFx(variables, x1);
        x2 = this.NewRegulaFalsiX(x0, fx0, x1, fx1);
        fx2 = this.FindFx(variables, x2);

        if( (fx0 < 0 && fx1 < 0) || (fx0 > 0 && fx1 > 0)){
            System.out.println("The inputted values will not work");
            return 0;
        }

        if (iteration == 1){
            System.out.println();
            System.out.println("| Iteration |" + "    x0   |" + "    x1   |"+ "       x2      |" + "     f(x0)     |" + "     f(x1)     |" + "     f(x2)     |" + "   Ea   |");
        }

        System.out.println("     " + iteration + "        " + df.format(x0) + "              "+ df.format(x1) + "        " + df.format(x2) + "        " + df.format(fx0) + "                  " + df.format(fx1) + "        " + df.format(fx2));

        if( (fx2 < 0 && fx0 < 0) || (fx2 > 0 && fx0 > 0)){
            iteration++;
            return this.RegulaFalsiIteration(x2, x1, x2, iteration);
        } else {
            iteration++;
            return this.RegulaFalsiIteration(x0, x2, x2 ,iteration);
        }
    }

    public double RegulaFalsiIteration(double x0, double x1, double Oldx2, int iteration){
        this.x0 = x0;
        this.x1 = x1;
        fx0 = this.FindFx(variables, x0);
        fx1 = this.FindFx(variables, x1);
        x2 = this.NewRegulaFalsiX(x0, fx0, x1, fx1);
        fx2 = this.FindFx(variables, x2);
        Ea = TerminatingCondition(x2, Oldx2);

        System.out.println("     " + iteration + "        " + df.format(x0) + "        "+ df.format(x1) + "        " + df.format(x2) + "        " + df.format(fx0) + "               " + df.format(fx1) + "        " + df.format(fx2) + "        " + df.format(Ea));

        if(Ea <= 0.0001){
            return x2;
        } else {
            if( (fx2 < 0 && fx0 < 0) || (fx2 > 0 && fx0 > 0)){
                iteration++;
                return this.RegulaFalsiIteration(x2, x1, x2, iteration);
            } else {
                iteration++;
                return this.RegulaFalsiIteration(x0, x2, x2 ,iteration);
            }
        }
    }
}
