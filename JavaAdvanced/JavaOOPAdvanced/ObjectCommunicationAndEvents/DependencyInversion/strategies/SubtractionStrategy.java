package JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.interfaces.Strategy;

public class SubtractionStrategy implements Strategy {
    public int calculate(int firstOperand, int secondOperand){
        return firstOperand - secondOperand;
    }
}
