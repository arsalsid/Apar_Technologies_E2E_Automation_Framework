package tests;

import api.get.GetBooking;
import api.post.BookingRequest;
import api.post.CreateBooking;
import baseTest.PropertyReader;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.extentReports.ExtentManager;
import utils.extentReports.ExtentTestManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class E2EAPIRunner {

    @Test(dataProvider = "positiveBookingDataProvider", description = "TC-001, Create a new valid booking and GET ID of Details", priority = 1)
    public void createValidDataAndGetIdWithDetails(String requestBodyPath, int expectedStatusCode) throws IOException, InterruptedException {

        ExtentTest test = ExtentTestManager.startTest("Create New Booking API Test", "This test will create a new booking");

        try {
            test.log(Status.INFO, "Starting the API request for creating a new booking");


            // Create new booking using te POST method
            CreateBooking createBooking = new CreateBooking();
            String bookingId = createBooking.createNewBooking(requestBodyPath, expectedStatusCode);

            Assert.assertNotNull(bookingId, "Booking ID should not be null for valid data");

            // Get booking details using the GET method
            GetBooking getBooking = new GetBooking();
            getBooking.getBookingById(bookingId,expectedStatusCode);

            test.log(Status.FAIL, "Valid booking created successfully with ID: " + expectedStatusCode);

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
            throw e;
        } finally {
            ExtentManager.createExtentReports().flush();
        }
    }
    @Test(dataProvider = "negativeBookingDataProvider", description = "TC-002,Create a new invalid booking and GET ID of Details", priority = 2)
    public void createInvalidBookingAndGetIDWithDetails(String requestBodyPath, int expectedStatusCode) throws IOException {

        ExtentTest test = ExtentTestManager.startTest("Create Invalid Booking API Test", "This test will attempt to create an invalid booking");

        try {
            test.log(Status.INFO, "Starting the API request for creating an invalid booking");

            CreateBooking createBooking = new CreateBooking();
            String bookingId = createBooking.createNewBooking(requestBodyPath, expectedStatusCode);

            Assert.assertNull(bookingId, "Booking ID should be null for invalid data");

            test.log(Status.PASS, "Handled invalid booking creation with expected status code: " + expectedStatusCode);
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to: "+e.getMessage());
            throw e;
        } finally {
            ExtentManager.createExtentReports().flush();
        }

    }
    @Test(dataProvider = "emptyBookingDataProvider", description = "TC-003, Create a booking with Empty data and GET ID of Details", priority = 3)
    public void createBookingWithEmptyDataAndGetIDWithDetails(String requestBodyPath, int expectedStatusCode) throws IOException {

        ExtentTest test = ExtentTestManager.startTest("Create Booking with Empty Data API Test","This test will attempt to create a booking with empty data");

        try {
            test.log(Status.INFO, "Starting the API request for creating a booking with empty data");

            CreateBooking createBooking = new CreateBooking();
            String bookingId = createBooking.createNewBooking(requestBodyPath, expectedStatusCode);

            Assert.assertNull(bookingId, "Booking ID should be null for empty data");

            test.log(Status.PASS, "Handled booking creation with empty data and expected status code: " + expectedStatusCode);
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to: "+e.getMessage());
            throw e;
        } finally {
            ExtentManager.createExtentReports().flush();
        }

    }

    @DataProvider(name = "positiveBookingDataProvider")
    public Object[][] positiveBookingData() {
        return new Object[][] {
                {"src/test/resources/request_JSON/CreateBookingRequest.json", 200}
        };
    }

    @DataProvider(name = "negativeBookingDataProvider")
    public Object[][] negativeBookingData() {
        return new Object[][] {
                {"src/test/resources/request_JSON/CreateBookingNegativeRequest.json", 400}
        };
    }

    @DataProvider(name = "emptyBookingDataProvider")
    public Object[][] emptyBookingData() {
        return new Object[][] {
                {"src/test/resources/request_JSON/CreateBookingEmptyRequest.json", 422}
        };
    }

}
