package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.files;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.File;

public class LogFile implements File {
    private StringBuilder sb;

    public LogFile() {
        this.sb = new StringBuilder();
    }

    @Override
    public void write(String log) {
        this.sb.append(log).append(System.lineSeparator());
    }

    @Override
    public int getSize() {
        int size = 0;
        char[] alphabetChars = this.sb.toString().replaceAll("[^A-Za-z]+", "").toCharArray();

        for (char alphabetChar: alphabetChars) {
            size += (int) alphabetChar;
        }

        return size;
    }
}
