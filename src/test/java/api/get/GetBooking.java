package api.get;

import api.base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.JSONUtils;

import java.io.IOException;

public class GetBooking  {

    public void getBookingById(String bookingId) throws IOException {

        Response response= BaseAPI.setupRequest()
                                  .get("/booking/" + bookingId);

        Assert.assertEquals(response.getStatusCode(), 200, "Booking not found!");
        JSONUtils.saveResponseToFile(response, "Booking_" + bookingId + "_details.json");
        response.then().log().all();
    }

    public void getInvalidBooking(String invalidId) throws IOException {

        Response response = BaseAPI.setupRequest()
                                   .get("/booking/" + invalidId);
        Assert.assertEquals(response.getStatusCode(),404, "Invalid booking ID check failed!");
        response.then().log().all();
    }
}
