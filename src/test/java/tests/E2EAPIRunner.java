package tests;

import api.get.GetBooking;
import api.post.CreateBooking;
import baseTest.DriverFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.Utilities;
import utils.extentReports.ExtentManager;
import utils.extentReports.ExtentTestManager;

import java.io.IOException;
import java.lang.reflect.Method;
import static utils.extentReports.ExtentTestManager.startTest;

public class E2EAPIRunner {

    private WebDriver driver = null;

    @Test(description = "Create a new valid Data and GET ID of Details", priority = 1)
    public void createValidDataAndGetIdWithDetails(Method method) throws IOException, InterruptedException {

        ExtentTest test = ExtentTestManager.startTest("Create New Booking API Test", "This test will create a new booking");

        try {
            test.log(Status.INFO, "Starting the API request for creating a new booking");
            // Create new booking using te POST method
            CreateBooking createBooking = new CreateBooking();
            String bookingId = createBooking.createNewBooking();

            // Get booking details using the GET method
            GetBooking getBooking = new GetBooking();
            getBooking.getBookingById(bookingId);
            test.log(Status.PASS,"Booking created successfully with ID:" + bookingId);

        }catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());
            throw e;
        } finally {
            ExtentManager.createExtentReports().flush();
        }
    }

    @Test(description = "Create Invalid Data and GET ID of Details", priority = 2)
    public void createInvalidDataAndGetIdWithDetails (Method method) throws IOException, InterruptedException {
        ExtentTest test = ExtentTestManager.startTest("Create New Booking API Test", "This test will create a new booking");

        test.log(Status.INFO, "Starting the API request for creating a new booking");
        CreateBooking createBooking = new CreateBooking();
        String invalidBookingId = createBooking.createInvalidBooking();
        test.log(Status.PASS,"Booking created successfully with invalid ID:" + invalidBookingId);

        GetBooking getBooking = new GetBooking();
        getBooking.getInvalidBooking(invalidBookingId);
        test.log(Status.FAIL,"Booking created successfully with invalid ID:" + invalidBookingId);
    }

}
