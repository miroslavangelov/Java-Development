package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public interface Appender {
    void append(String datetime, ReportLevel reportLevel, String message);

    void setReportLevel(ReportLevel reportLevel);
}
