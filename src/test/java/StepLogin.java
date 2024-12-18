import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.apache.groovy.json.internal.Chr;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.sql.Driver;

public class StepLogin {

    WebDriver driver;

//    "Before" for all run before scenarios. It will open new chrome for all scenarios
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.safaridriver().setup();

//        ChromeOptions options = new ChromeOptions();
////        To describe size of browsers
//        options.addArguments("--window-size=1920,1980");
//        options.addArguments("--window-size=1920,1980");

//        If options is not commented then ChromeDriver needs param as "options"
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("baseUrl is login page {string}")
    public void endpointIsLoginPage(String baseUrl) {
        driver.get(baseUrl);
    }


    @When("valid username is {string} and password is {string}")
    public void validUsernameIsAndPasswordIs(String username, String password) {
        WebElement usernameElement = driver.findElement(By.id("user-name"));
        usernameElement.clear();
        usernameElement.click();
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.id("password"));
//        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    @And("click to the login button")
    public void clickToTheLoginButton() {
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Then("{string} page should display")
    public void homePageShouldDisplay(String homeUrl) {
        String homePageUrl = driver.getCurrentUrl();
        Assert.assertEquals(homePageUrl, homeUrl);
    }

    @And("user adds {string} to the cart")
    public void userAddsToTheCart(String productName) {
        String productNameFormated = productName.toLowerCase().replace(" ", "-");
        WebElement addBackPack = driver.findElement(By.id("add-to-cart-" + productNameFormated));
        addBackPack.click();
    }

    @And("navigate to the cart page")
    public void navigateToTheCartPage() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @And("{string} product should be selected in cart")
    public void productShouldBeSelectedInCart(String productName) {
        WebElement findProduct =  driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        Assert.assertEquals(productName, findProduct.getText());
    }
}
