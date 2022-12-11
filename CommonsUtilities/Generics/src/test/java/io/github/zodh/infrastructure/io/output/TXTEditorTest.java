package io.github.zodh.infrastructure.io.output;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.zodh.infrastructure.io.input.TXTReader;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TXTEditorTest {

  @Test
  @DisplayName("Should write 'testing...' in a .txt file")
  void givenTestingStringWhenCallPostContentThenWriteInTheFile() {
    final String expected = "testing...";
    var file = new File("src/test/resources/teste-escrito.txt");
    new TXTEditor("testing...").postContentTo(file);
    var result = new TXTReader(file).getContent();
    assertThat(result).isEqualTo(expected);
  }

  @Test
  @DisplayName("Should write 'testing...' in a .txt file attaching content")
  void givenTestingStringWhenCallPostContentAttachingContentThenWriteInTheFile() {
    final String expected = "testing...";
    var file = new File("src/test/resources/teste-escrito.txt");
    new TXTEditor().attachContent("testing...").postContentTo(file);
    var result = new TXTReader(file).getContent();
    assertThat(result).isEqualTo(expected);
  }

}
