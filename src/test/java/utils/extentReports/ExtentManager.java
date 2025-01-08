package utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + "//extent-report.html");
        reporter.config().setDocumentTitle("NextGeni End To End Test Report");
        reporter.config().setReportName("Amazon Test Extent Report");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("dd.MM.yyyy, HH:mm:ss");
        extentReports.setSystemInfo("Author", "Muhammad Arsalan");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.attachReporter(reporter);

        return extentReports;
    }
}