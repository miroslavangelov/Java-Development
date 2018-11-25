package JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.strategies;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.DependencyInversion.interfaces.Strategy;

public class MultiplicationStrategy implements Strategy {
    @Override
    public int calculate(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }
}
