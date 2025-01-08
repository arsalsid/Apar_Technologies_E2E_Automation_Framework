package api.post;

import java.io.IOException;

public interface BookingRequest {
    //Method to handle positive test case with valid data

    String createNewBooking(String requestBodyPath, int expectedStatusCode)
            throws IOException;
}
