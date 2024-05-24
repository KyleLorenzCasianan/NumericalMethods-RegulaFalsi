import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double coefficient;
        double degree;
        double result;
        DecimalFormat df = new DecimalFormat("#.####");


        Function function = new Function();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of terms you would like to add: ");
        int NumberOfTerms = input.nextInt();
        function.numberofTerms = NumberOfTerms;

        for(int i = 0; i <= NumberOfTerms; i++){
            if(i == NumberOfTerms){
                for(int j = 0; j < NumberOfTerms; j++){
                    System.out.print(function.terms[j]);
                    if(function.variables[j].getCoefficient() < 0){
                        if( (j+1) == NumberOfTerms){
                            break;
                        }
                        else{
                            System.out.print(" + ");
                        }
                    }
                    else{
                        System.out.print(" ");
                    }
                }
                System.out.println();
                break;
            }

            System.out.println("Enter a number that you would like your term to be ( 1 - X; 2 - sin x; 3 - cos x; 4 - tan x; 5 - e^x; 6 - sqrt(x))");
            System.out.print("Choice: ");
            int choice = input.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();
                    function.GetStringTerm(coefficient,degree,1,1);
                    break;

                case 2:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();
                    function.GetStringTerm(coefficient,degree,1,2);
                    break;

                case 3:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();
                    function.GetStringTerm(coefficient,degree,1,3);
                    break;

                case 4:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();;
                    function.GetStringTerm(coefficient,degree,1,4);
                    break;

                case 5:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();
                    function.GetStringTerm(coefficient,degree,1,5);
                    break;

                case 6:
                    System.out.println("Enter coefficient and degree");
                    System.out.print("Coefficient: ");
                    coefficient = input.nextDouble();
                    System.out.print("Degree: ");
                    degree = input.nextDouble();;
                    function.GetStringTerm(coefficient,degree,1,6);
                    break;
            }
        }
        System.out.print("Enter lower range of table: ");
        int lowrange = input.nextInt();
        System.out.print("Enter higher range of table: ");
        int highrange = input.nextInt();
        function.TableDisplay(lowrange, highrange);

        System.out.println();

        System.out.print("Enter x0: ");
        double x0 = input.nextDouble();
        System.out.print("Enter x1: ");
        double x1 = input.nextDouble();

        if( ((function.FindFx(function.variables, x0) < 0 && function.FindFx(function.variables, x1) < 0) || (function.FindFx(function.variables, x0) > 0 && function.FindFx(function.variables, x1) > 0)) || (x0 + 1) != x1){
            boolean checker = false;
            while(!checker){
                System.out.println("The inputted values will not work");
                System.out.print("Enter x0: ");
                x0 = input.nextDouble();
                System.out.print("Enter x1: ");
                x1 = input.nextDouble();

                if( !((function.FindFx(function.variables, x0) < 0 && function.FindFx(function.variables, x1) < 0) || (function.FindFx(function.variables, x0) > 0 && function.FindFx(function.variables, x1) > 0)) ){
                    if( (x0 + 1) == x1){
                        checker = true;
                    }
                }
            }

        }


        result = (function.RegulaFalsiIteration(x0,x1,1));
        System.out.print("The estimated root is x2 = " + df.format(result));

    }
}
