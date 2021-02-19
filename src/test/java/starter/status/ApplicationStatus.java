package starter.status;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.WebServiceEndPoints;

public class ApplicationStatus {

    @Step("Get current status of invalid date")
    public AppStatus currentStatus(String url) {
        int statusCode = RestAssured.get(url).statusCode();
        return (statusCode == 200) ? AppStatus.RUNNING : AppStatus.DOWN;
    }

    
    @Step("Get current error status message")
    public int readStatusMessage(String url) {
    	int statusCode = RestAssured.get(url).statusCode();
    	return statusCode;
    }
}
