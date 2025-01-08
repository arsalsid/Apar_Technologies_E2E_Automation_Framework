package api.get;

import api.base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.JSONUtils;

import java.io.IOException;

public class GetBooking  {

    public void getBookingById(String bookingId, int expectedStatusCode) throws IOException {

        Response response= BaseAPI.setupRequest()
                                  .get("/booking/" + bookingId);

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,"Status code mismatch!");
        // Assert the Content-Type header contains application/json
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                "Expected Content-Type to be application/json but received: " + response.getHeader("Content-Type"));


        if (expectedStatusCode == 200) {
            JSONUtils.saveResponseToFile(response, "Booking_" + bookingId + "_details.json");
        }
        // Handle various error scenarios (400, 404, 500, etc.)
        if (expectedStatusCode == 400) {
            System.out.println("Bad Request: " + response.getBody().asString());
        } else if (expectedStatusCode == 404) {
            System.out.println("Booking Not Found: " + response.getBody().asString());
        } else if (expectedStatusCode == 500) {
            System.out.println("Internal Server Error: " + response.getBody().asString());
        } else if (expectedStatusCode == 422) {
            System.out.println("Unprocessable Entity. The server understood the request but couldn't process it. " + response.getBody().asString());
        }

        response.then().log().all();
    }

}
