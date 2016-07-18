package client_server;

import static client_server.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static client_server.ServerMockHelper.GET_CUSTOMER_ADDRESS;
import static client_server.ServerMockHelper.GET_ORDER_POSITION;
import static client_server.ServerMockHelper.GET_ORDER_POSITIONS;
import static client_server.ServerMockHelper.RESOURCE_PATH;
import static client_server.ServerMockHelper.getJSONFromFile;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.junit.JUnit4CitrusTest;

/**
 * This class stubs a HTTP server waiting on port 8081 for several GET requests using the Citrus Framework. Note that
 * these tests needs to be started in a different ide instance than the HTTP client.
 *
 * @author shuber
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleServer extends JUnit4CitrusTest {

  protected static long TIME_OUT = 600000000;

  /**
   * This method stubs a HTTP server waiting for a GET request. If this request is received, the JSON file
   * "orderPositionPayload.json" is responded.
   *
   * @param designer
   */
  @Test
  @CitrusTest
  public void deliverOrderPosition(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ORDER_POSITION).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile("src/test/resources/orderPositionPayload.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  /**
   * This method stubs a HTTP server waiting for a GET request. If this request is received, the JSON file
   * "orderPositionsPayload.json" is responded.
   *
   * @param designer
   */
  @SuppressWarnings("javadoc")
  @Test
  @CitrusTest
  public void deliverOrderPositions(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ORDER_POSITIONS).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile("src/test/resources/orderPositionsPayload.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  /**
   * This method stubs a HTTP server waiting for a GET request. If this request is received, the JSON file
   * "customer.json" is responded.
   *
   * @param designer
   */
  @SuppressWarnings("javadoc")
  @Test
  @CitrusTest
  public void deliverAllCustomerDates(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ALL_CUSTOMER_DATA).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");
  }

  /**
   * This method stubs a HTTP server waiting for a GET request. If this request is received, the address part of the
   * JSON file "customer.json" is responded.
   *
   * @param designer
   */
  @SuppressWarnings({ "null", "javadoc" })
  @Test
  @CitrusTest
  public void deliverCustomerAddress(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_CUSTOMER_ADDRESS).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    JSONObject jsonObject = null;
    try {
      jsonObject = new JSONObject(fileContent);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    JSONObject address = null;
    try {
      address = jsonObject.getJSONObject("address");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(address.toString()).version("HTTP/1.1")
        .contentType("application/json");
  }

}
