package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public interface Layout {
    String format(String datetime, ReportLevel reportLevel, String message);
}
