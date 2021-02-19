package starter.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static starter.status.AppStatus.RUNNING;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.WebServiceEndPoints;
import starter.status.ApplicationStatus;


public class ApplicationStatusStepDefinitions {

	String invalid_date = "qwe";
	String Publishdate_url =WebServiceEndPoints.PUBLISHDATE.getUrl();
	String list_books_url=WebServiceEndPoints.LISTBOOKS.getUrl();
	String author = "Sophia Amoru";
	String valid_date = "2013-05-22";
	String future_date = "2023-05-22";

	@Steps
	ApplicationStatus theApplication;



	@Given("the application is running") 
	public void the_application_is_running()
	{
		assertThat(theApplication.currentStatus(WebServiceEndPoints.NYURL.getUrl())).isEqualTo(RUNNING); }

	@When("I fetch the lists of books") 
	public void fetch_books_status() { 
		assertThat(theApplication.readStatusMessage(list_books_url)).isEqualTo(200); }

	@Then("the API should return list of books written by author") 
	public void the_API_should_return() {
		int num_records=SerenityRest.get(list_books_url+author).body().jsonPath().get("num_results"); 
		assertTrue(num_records>0); 
	}



	@When("Invalid date is given")
	public void invalid_Published_date_status() {
		assertThat(theApplication.readStatusMessage(Publishdate_url+invalid_date)).isEqualTo(400);
	}

	@Then("the API should return ERROR")
	public void the_API_should_return_error() {

		RestAssured.get(Publishdate_url+valid_date).then().assertThat().toString().contains("Invalid Date Format");
	}

	@When("Published date is given")
	public void valid_Published_date_status() {
		assertThat(theApplication.readStatusMessage(Publishdate_url+valid_date)).isEqualTo(200);
	}

	@Then("the API should return all the best-seller list for a published date")
	public void the_API_should_return_list() {
		int num_records=SerenityRest.get(Publishdate_url+valid_date).body().jsonPath().get("num_results"); 
		assertTrue(num_records>0);

	}

	@When("Published date is blank")
	public void blank_Published_date_status() {
		assertThat(theApplication.readStatusMessage(Publishdate_url)).isEqualTo(200);
	}

	@Then("the API should return the current weeks best-sellers lists")
	public void the_API_should_return_all_books() {
		int num_records=SerenityRest.get(Publishdate_url).body().jsonPath().get("num_results"); 
		assertTrue(num_records>0);

	}

	@When("Published date is future date")
	public void future_Published_date_status() {

		assertThat(theApplication.readStatusMessage(Publishdate_url+future_date)).isEqualTo(200);
	}

	@Then("the API should return blank list")
	public void the_API_should_return_null() {

		SerenityRest.get(Publishdate_url+future_date).body().jsonPath().prettyPrint();
		int num_rec=SerenityRest.get(Publishdate_url+future_date).body().jsonPath().get("num_results");
		assertTrue(num_rec==0);
		
	}        
}
