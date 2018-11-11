package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.layouts;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public class SimpleLayout implements Layout {
    @Override
    public String format(String datetime, ReportLevel reportLevel, String message) {
        return String.format("%s - %s - %s", datetime, reportLevel.name(), message);
    }
}
