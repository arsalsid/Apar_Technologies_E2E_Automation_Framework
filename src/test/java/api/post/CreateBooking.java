package api.post;
import api.base.BaseAPI;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.JSONUtils;
import java.io.IOException;


public class CreateBooking implements BookingRequest {

    @Override
    public String createNewBooking(String requestBodyPath, int expectedStatusCode) throws IOException {

        RequestSpecification requestSpecification = BaseAPI.setupRequest();
        Response response = requestSpecification
                .body(JSONUtils.readRequestFile(requestBodyPath))
                .post("/booking");

        response.then().log().all();

        // Assert the Content-Type header contains application/json
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                "Expected Content-Type to be application/json but received: " + response.getHeader("Content-Type"));

        // validate the status code
        if (response.getStatusCode() != expectedStatusCode) {
            throw new IllegalStateException("Excepted Status Code " + expectedStatusCode +
                    " but received : " + response.getStatusCode());
        }

        if (expectedStatusCode == 200 || expectedStatusCode == 201) {
            String bookingId = response.jsonPath().getString("bookingid");
            JSONUtils.saveResponseToFile(response, "Booking_" + bookingId + ".json");
            return bookingId;

        } else if (expectedStatusCode == 400) {
            System.out.println("Bad Request. The server could not understand the request. " + response.getBody().asString());
        } else if (expectedStatusCode == 422) {
            System.out.println("Unprocessable Entity. The server understood the request but couldn't process it." + response.getBody().asString());
        } else if (expectedStatusCode == 404) {
            System.out.println("Booking Not Found: " + response.getBody().asString());
        } else if (expectedStatusCode == 500) {
            System.out.println("Internal Server Error. There was an issue on the server side." + response.getBody().asString());
        }

        // Handle other unexpected status codes
        System.out.println("Unexpected Status Code: " + response.getStatusCode());
        return null;
    }

}


