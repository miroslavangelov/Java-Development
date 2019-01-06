package app.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilImpl implements FileUtil {
    @Override
    public String getFileContent(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    @Override
    public void writeFile(String filePath, String json) throws IOException {
        File file = new File(filePath);

        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }
}
