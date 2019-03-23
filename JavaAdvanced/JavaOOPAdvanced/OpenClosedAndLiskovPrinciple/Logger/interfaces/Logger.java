package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public interface Logger {
    void logMessage(String datetime, ReportLevel reportLevel, String message);
}
