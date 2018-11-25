package JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.interfaces.Strategy;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies.AdditionStrategy;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies.DivisionStrategy;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies.MultiplicationStrategy;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies.SubtractionStrategy;

public class PrimitiveCalculator {
    private Strategy strategy;

    public PrimitiveCalculator(){
        this.strategy = new AdditionStrategy();
    }

    public void changeStrategy(char operator){
        switch (operator){
            case '+': this.strategy = new AdditionStrategy();
                break;
            case '-': this.strategy = new SubtractionStrategy();
                break;
            case '*': this.strategy = new MultiplicationStrategy();
                break;
            case '/': this.strategy = new DivisionStrategy();
                break;
        }
    }

    public int performCalculation(int firstOperand,int secondOperand){
       return  this.strategy.calculate(firstOperand, secondOperand);
    }
}
