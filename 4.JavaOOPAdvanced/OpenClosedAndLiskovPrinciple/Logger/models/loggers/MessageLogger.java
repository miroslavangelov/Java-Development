package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.loggers;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Appender;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Logger;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;

public class MessageLogger implements Logger {
    private Appender[] appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = appenders;
    }

    @Override
    public void logMessage(String datetime, ReportLevel reportLevel, String message) {
        for (Appender appender: this.appenders) {
            appender.append(datetime, reportLevel, message);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Logger info").append(System.lineSeparator());

        for (Appender appender: this.appenders) {
            result.append(appender.toString()).append(System.lineSeparator());
        }

        return result.toString();
    }
}
