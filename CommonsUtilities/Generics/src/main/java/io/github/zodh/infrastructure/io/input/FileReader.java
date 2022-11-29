package io.github.zodh.infrastructure.io.input;

import io.github.zodh.infrastructure.io.FileExtension;
import java.io.File;

public abstract class FileReader implements Reader {

  protected File file;

  protected FileReader (File file) {
    if (file != null && !file.canRead()) {
      throw new RuntimeException("The input file cannot be read. Make sure you have the necessary permissions to read the file.");
    }
    this.file = file;
  }

  protected FileReader() {}

  public Object getContent(final File file) {
    if (file == null) {
      throw new NullPointerException("File is null");
    }
    return read(file);
  }

  public Object getContent() {
    if (this.file == null) {
      throw new NullPointerException("File was not loaded in Reader constructor");
    }
    return getContent(this.file);
  }

  protected FileExtension getFileExtension(final File file) {
    if (file == null) {
      throw new NullPointerException("Is not possible to check extension of a null file");
    }
    var len = file.getAbsolutePath().length();
    var fourLen = file.getAbsolutePath().substring(len - 3, len);
    var fiveLen = file.getAbsolutePath().substring(len - 4, len);
    var result = (fourLen.charAt(0) == '.') ? fourLen : fiveLen;
    try {
      return FileExtension.fromValue(result);
    } catch (Exception exception) {
      throw new IllegalArgumentException(String.format("Unhandled file extension: %s or %s", fourLen, fiveLen));
    }
  }

  protected String getFileName(final File file) {
    return "File name: " + file.getName();
  }

  public abstract Boolean isValidFile(final File file);

  public Boolean isInvalidFile(final File file) {
    return !isValidFile(file);
  }
}
