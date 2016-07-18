package client_server;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is a helper class for the classes {@link SampleServer} and {@link HttpGetRequestTests}. It capsulates some
 * sample attributes as constants used by both testing classes. Moreover it defines a method to read in a JSON file as
 * string.
 *
 * @author shuber
 */
public class ServerMockHelper {
  protected static final String GET_ORDER_POSITION = "/orderposition";

  protected static final String GET_ORDER_POSITIONS = "/orderpositions";

  protected static final String GET_ALL_CUSTOMER_DATA = "/allcustomerdates";

  protected static final String GET_CUSTOMER_ADDRESS = "/customeraddress";

  protected static final String ROLE = "waiter";

  protected static final String RESOURCE_PATH = "src/test/resources/";

  /**
   * This method reads the content of a JSON file and save it
   *
   * @param fileName
   * @return
   */
  protected static String getJSONFromFile(String fileName) {

    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());
    return fileContent;
  }
}
