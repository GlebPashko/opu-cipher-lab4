import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public void write(String fileName, String content) {
        File file = new File(fileName);
        if (file.exists() && file.length() == 0) {
            file.delete();
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
