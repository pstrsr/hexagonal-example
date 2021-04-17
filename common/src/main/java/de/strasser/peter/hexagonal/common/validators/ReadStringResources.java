package de.strasser.peter.hexagonal.common.validators;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadStringResources {

  public static String readStringFromResource(String filename) throws IOException {
    final File file = ResourceUtils.getFile("classpath:" + filename);
    return Files.readString(file.toPath());
  }
}
