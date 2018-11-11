package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.layouts;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public class XmlLayout implements Layout {
    private static String LOG_TAG = "<log>";
    private static String CLOSING_LOG_TAG = "</log>";
    private static String DATE_TAG = "<date>";
    private static String CLOSING_DATE_TAG = "</date>";
    private static String LEVEL_TAG = "<level>";
    private static String CLOSING_LEVEL_TAG = "</level>";
    private static String MESSAGE_TAG = "<message>";
    private static String CLOSING_MESSAGE_TAG = "</message>";

    @Override
    public String format(String datetime, ReportLevel reportLevel, String message) {
        StringBuilder result = new StringBuilder();

        result.append(LOG_TAG).append(System.lineSeparator())
            .append("\t").append(DATE_TAG).append(datetime).append(CLOSING_DATE_TAG).append(System.lineSeparator())
            .append("\t").append(LEVEL_TAG).append(reportLevel.name()).append(CLOSING_LEVEL_TAG).append(System.lineSeparator())
            .append("\t").append(MESSAGE_TAG).append(message).append(CLOSING_MESSAGE_TAG).append(System.lineSeparator())
            .append(CLOSING_LOG_TAG);

        return result.toString();
    }
}
