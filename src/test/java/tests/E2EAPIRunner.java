package tests;

import api.get.GetBooking;
import api.post.BookingRequest;
import api.post.CreateBooking;
import baseTest.PropertyReader;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.extentReports.ExtentManager;
import utils.extentReports.ExtentTestManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class E2EAPIRunner {

    @Test(dataProvider = "bookingDataProvider", description = "Create a new valid or invalid booking and GET ID of Details", priority = 1)
    public void createValidAndInvalidDataAndGetIdWithDetails(String requestBodyPath, int expectedStatusCode) throws IOException, InterruptedException {

        ExtentTest test = ExtentTestManager.startTest("Create New Booking API Test", "This test will create a new booking");

        try {
            test.log(Status.INFO, "Starting the API request for creating a new booking");


            // Create new booking using te POST method
            CreateBooking createBooking = new CreateBooking();
            String bookingId = createBooking.createNewBooking(requestBodyPath, expectedStatusCode);

            if (bookingId != null) {
                // Get booking details using the GET method
                GetBooking getBooking = new GetBooking();
                getBooking.getBookingById(bookingId,expectedStatusCode);
                test.log(Status.PASS, "Booking created successfully with ID:" + bookingId);
            } else {
                // Handle failure cases (400, 500 errors)
                test.log(Status.FAIL, "Booking creation failed with status code: " + expectedStatusCode);
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
            throw e;
        } finally {
            ExtentManager.createExtentReports().flush();
        }
    }

    @DataProvider(name = "bookingDataProvider")
    public Object[][] bookingData() {
        return new Object[][]{

                // valid test case with 200 status code
                {
                        "src/test/resources/request_JSON/CreateBookingRequest.json",  // requestBodyPath
                        200  // expectedStatusCode
                },

                //Invalid test case with 400 status code e.g.(missing required fields and incorrect data)
                {
                        "src/test/resources/request_JSON/CreateBookingNegativeRequest.json",  // requestBodyPath
                        400  // expectedStatusCode
                },

                {
                        "src/test/resources/request_JSON/CreateBookingEmptyRequest.json",  // requestBodyPath
                        422  // expectedStatusCode
                },

        };
    }

}
