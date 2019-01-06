package app.utils;

import java.io.IOException;

public interface FileUtil {
    String getFileContent(String filePath) throws IOException;

    void writeFile(String filePath, String json) throws IOException;
}
