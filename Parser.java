import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {
  private File file;
  public synchronized void setFile(File f) {
    file = f;
  }
  public synchronized File getFile() {
    return file;
  }
  /*
    value is for WithoutUnicode 
  */
  public String getContent(int value) throws IOException {
    FileInputStream i = new FileInputStream(file);
    String output = "";
    int data;
    if (value == 1) {
      while ((data = i.read()) > 0) 
        if (data < 0x80)
        output += (char) data;
    } else {
    while ((data = i.read()) > 0) {
        output += (char) data;
      }
    }
    return output;
  }
  
  public void saveContent(String content) throws IOException {
    FileOutputStream o = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i++) {
      o.write(content.charAt(i));
    }
  }
}
