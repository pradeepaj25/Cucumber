# Assignment 

API server:  https://any-api.com/nytimes_com/books_api/docs/API_Description
CI platform: GitHub
BDD: Cucumber
Framework: Java Serenity

## Getting started
1. Install Java, latest version

2. Install maven
Do #1 and #2 incase you want to generate HTML reports of the automation 

3. Install eclipse

4. Install cucumber plugin from eclipse

5. Get the code
    git clone https://github.com/pradeepaj25/Cucumber.git
    cd newyorktimes-cucumber
Or simply [download a zip]file.

6. To execute the usecases : 
    Go to command prompt and open the project folder: newyorktimes-cucumber
    run mvn clean verify
    This will generate the HTML reports under target> site> serenity > index.html
      
7. To write new tests 
    go to src> test> resources>features>status>fetch_al_bboks_with_publisheddate.feature and write any new scenarios
    go to src> test> java> starter.stepdefinitions> ApplicationStatusStepDefinitions.java and add step definitions
    
      
       
   


### The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:

src
  + main
  + test
    + java                                Test runners and supporting code
    + resources
      + features                          Feature files 
          + status
              fetch_al_bboks_with_publisheddate.feature 
                      

```

## A simple GET scenarios
The project comes with five simple scenarios.

The first scenario exercises the `https://api.nytimes.com/svc/books/v3/lists/best-sellers/history.json` endpoint


```Gherkin
  Scenario: Fetch all the books published by a author
    Given the application is running
    When I fetch the lists of books
    Then the API should return list of books written by author
```

The glue code for this scenario illustrates the layered approach we find works well for both web and non-web acceptance tests.
The glue code is responsible for orchestrating calls to a layer of more business-focused classes, which perform the actual REST calls.

```java
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
```

The actual REST calls are performed using RestAssured in the action classes, like `ApplicationStatus` here. 



## Living documentation

You can generate full Serenity reports by running `mvn clean verify`. 
This includes both the living documentation from the feature files:

![](newyorktimes-cucumber/target/site/serenity/index.html)


CI Integration with jenkins

install jenkins on your hardware, follow https://plugins.jenkins.io/git/ for integration.

