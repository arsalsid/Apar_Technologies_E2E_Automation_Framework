package api.post;

import api.base.BaseAPI;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.JSONUtils;
import utils.extentReports.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CreateBooking {

    //Method to handle positive test case with valid data
    public String createNewBooking() throws IOException {

        String requestBodyPath = "src/test/resources/request_JSON/CreateBookingRequest.json";

        RequestSpecification requestSpecification = BaseAPI.setupRequest();
        Response response = requestSpecification
                .body(JSONUtils.readRequestFile(requestBodyPath))
                .post("/booking");

        response.then().log().all();

        // Print the raw response for debugging
        System.out.println("Raw Response: " + response.asString());
        String jsonResponse;

        // Validate if the response is actually JSON and handle it accordingly
        if (response.contentType() == null || !response.contentType().contains("application/json")) {
            String rawResponse = response.asString();

            // Attempt to convert the plain text response to JSON
            try {
                // Here you can create a JSON structure from the raw response if necessary
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("message", rawResponse); // You may want to structure this differently based on the actual response
                jsonResponse = JSONUtils.convertMapToJSONString(responseMap);

                System.out.println("Converted JSON Response: " + jsonResponse);

            } catch (Exception e) {
                throw new IllegalStateException("Failed to convert plain text response to JSON: " + e.getMessage());
            }
            throw new IllegalStateException("Expected JSON response but received: " + response.contentType());
        }
        else {
            jsonResponse = response.asString();
        }

        if (response.getStatusCode() != 200) {
            throw new IllegalStateException("Expected status code 200 but received: " + response.getStatusCode());
        }

        String bookingId = response.jsonPath().getString("bookingid");
        JSONUtils.saveResponseToFile(response, "Booking_" + bookingId + ".json");


        return bookingId;
    }

    public String createInvalidBooking() throws IOException {

        String requestBodyPath = "src/test/resources/request_JSON/CreateBookingRequest.json";
        Response response = BaseAPI.setupRequest()
                .body(new File(requestBodyPath))
                .post("/booking");

        if (response.getStatusCode() == 200){
            throw new AssertionError("Negative test case should not return 200 status code!");
        }

        // Save response even if the request fail (for debugging)
        JSONUtils.saveResponseToFile(response, "Negative_Booking_Response.json");
        return requestBodyPath;
    }
}
