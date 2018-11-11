package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.engines;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.factories.AppenderFactory;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.factories.LayoutFactory;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.*;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.enums.ReportLevel;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.loggers.MessageLogger;

public class Engine {
    private static final String TERMINATE_COMMAND = "END";

    private Writer writer;
    private Reader reader;
    private Logger logger;

    public Engine(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() {
        this.addAppenders();

        String currentLine = this.reader.readLine();
        while (!TERMINATE_COMMAND.equals(currentLine)) {
            String[] data = currentLine.split("\\|");
            ReportLevel reportLevel = ReportLevel.valueOf(data[0]);
            String datetime = data[1];
            String message = data[2];

            logger.logMessage(datetime, reportLevel, message);
            currentLine = this.reader.readLine();
        }

        this.writer.write(this.logger.toString());
    }

    private void addAppenders() {
        int appendersCount = Integer.parseInt(reader.readLine());
        Appender[] appenders = new Appender[appendersCount];

        for (int i = 0; i < appendersCount; i++) {
            String[] appenderData = reader.readLine().split(" ");
            Layout layout = LayoutFactory.create(appenderData[1]);
            Appender appender = AppenderFactory.create(appenderData[0], layout);

            if (appenderData.length > 2) {
                ReportLevel reportLevel = ReportLevel.valueOf(appenderData[2]);
                if (appender != null) {
                    appender.setReportLevel(reportLevel);
                }
            }
            appenders[i] = appender;
        }
        this.logger = new MessageLogger(appenders);
    }
}
