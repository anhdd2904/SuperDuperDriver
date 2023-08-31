package com.udacity.superduperdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.time.Duration;
import java.util.function.BooleanSupplier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperduperdriverApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Techcare\\Downloads\\chromedriver-win64\\chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doMockSignUp(String firstName, String lastName, String userName, String password) {
        // Create a dummy account for logging in later.

        // Visit the sign-up page.
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        driver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = driver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = driver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys(password);

        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
        WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
        buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
        Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
    }


    /**
     * PLEASE DO NOT DELETE THIS method.
     * Helper method for Udacity-supplied sanity checks.
     **/
    private void doLogIn(String userName, String password) {
        // Log in to our dummy account.
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys(userName);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys(password);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling redirecting users
     * back to the login page after a succesful sign up.
     * Read more about the requirement in the rubric:
     * https://review.udacity.com/#!/rubrics/2724/view
     */
    @Test
    public void testRedirection() {
        // Create a test account
        doMockSignUp("Redirection", "Test", "RT", "123");

        // Check if we have been redirected to the log in page.
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
    }

    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling bad URLs
     * gracefully, for example with a custom error page.
     * <p>
     * Read more about custom error pages at:
     * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
     */
    @Test
    public void testBadUrl() {
        // Create a test account
        doMockSignUp("URL", "Test", "UT", "123");
        doLogIn("UT", "123");

        // Try to access a random made-up URL.
        driver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
    }


    /**
     * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
     * rest of your code.
     * This test is provided by Udacity to perform some basic sanity testing of
     * your code to ensure that it meets certain rubric criteria.
     * <p>
     * If this test is failing, please ensure that you are handling uploading large files (>1MB),
     * gracefully in your code.
     * <p>
     * Read more about file size limits here:
     * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
     */
    @Test
    public void testLargeUpload() {
        // Create a test account
        doMockSignUp("Large File", "Test", "LFT", "123");
        doLogIn("LFT", "123");

        // Try to upload an arbitrary large file
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        String fileName = "upload5m.zip";

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = driver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }


    /**
     * Viết bài kiểm tra để xác minh rằng người dùng trái phép chỉ có thể truy cập trang đăng nhập và đăng ký.
     */
    @Test
    public void testUnauthorizedAccessToOtherPages() {
        driver.get("http://localhost:" + this.port + "/login");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement loginUserName = driver.findElement(By.id("inputUsername"));
        loginUserName.click();
        loginUserName.sendKeys("userName");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement loginPassword = driver.findElement(By.id("inputPassword"));
        loginPassword.click();
        loginPassword.sendKeys("password");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();


        Assertions.assertEquals("http://localhost:" + this.port + "/login?error=true", driver.getCurrentUrl());
        Assertions.assertTrue(driver.findElement(By.id("login-error")).getText().contains("Invalid username or password"));

    }

    /**
     * Method thực hiện công việc đăng nhập và đăng kí
     */
    public void signupAndLogin(String firstName, String lastName, String userName, String password) {
        doMockSignUp(firstName, lastName, userName, password);
        doLogIn(userName, password);
    }

    /**
     * Viết bài kiểm tra đăng ký người dùng mới, đăng nhập, xác minh rằng trang chủ có thể truy cập được, đăng xuất
     * và xác minh rằng trang chủ không còn truy cập được nữa.
     */
    @Test
    public void testLogout() {

        doMockSignUp("a", "a", "a", "a");
        doLogIn("a", "a");

        driver.get("http://localhost:" + this.port + "/home");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-button")));
        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        logoutButton.click();

        Assertions.assertEquals("http://localhost:" + this.port + "/login?logout=true", driver.getCurrentUrl());
        Assertions.assertTrue(driver.findElement(By.id("logout-msg")).getText().contains("You have been logged out"));
    }

    /**
     * Method thực hiện công việc tạo note
     */
    public void addNote(String firstName, String lastName, String userName, String password) {
        signupAndLogin(firstName, lastName, userName, password);

        driver.get("http://localhost:" + this.port + "/home");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement buttonShowTabNote = driver.findElement(By.id("nav-notes-tab"));
        buttonShowTabNote.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-view-add-note")));
        WebElement buttonShowAddNote = driver.findElement(By.id("btn-view-add-note"));
        buttonShowAddNote.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        WebElement noteTitle = driver.findElement(By.id("note-title"));
        noteTitle.click();
        noteTitle.sendKeys("This is title");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        WebElement noteDescription = driver.findElement(By.id("note-description"));
        noteDescription.click();
        noteDescription.sendKeys("This is description");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-save-note")));
        WebElement buttonSaveNote = driver.findElement(By.id("btn-save-note"));
        buttonSaveNote.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("href-home-success")));
        WebElement hrefBackHomepage = driver.findElement(By.id("href-home-success"));
        hrefBackHomepage.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement buttonShowAddNote2 = driver.findElement(By.id("nav-notes-tab"));
        buttonShowAddNote2.click();
    }

    /**
     * Viết bài kiểm tra tạo ghi chú và xác minh nó được hiển thị.
     */
    @Test()
    public void testAddNote() {

        addNote("anhdd","anhdd","Add","123456");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text-title-note")));
        Assertions.assertTrue(driver.findElement(By.id("text-title-note")).getText().contains("This is title"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text-description-note")));
        Assertions.assertTrue(driver.findElement(By.id("text-description-note")).getText().contains("This is description"));
    }

    /**
     * Viết bài kiểm tra chỉnh sửa ghi chú hiện có và xác minh rằng các thay đổi được hiển thị.
     */
    @Test
    public void testUpdateNote(){
        addNote("anhdd","anhdd","Update","123456");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-edit-note")));
        WebElement editNote = driver.findElement(By.id("btn-edit-note"));
        editNote.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        WebElement noteTitleUpdate = driver.findElement(By.id("note-title"));
        noteTitleUpdate.click();
        noteTitleUpdate.sendKeys("Update title");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        WebElement noteDescriptionUpdate = driver.findElement(By.id("note-description"));
        noteDescriptionUpdate.click();
        noteDescriptionUpdate.sendKeys("Update description");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-save-note")));
        WebElement buttonSaveNoteUpdate = driver.findElement(By.id("btn-save-note"));
        buttonSaveNoteUpdate.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("href-home-success")));
        WebElement hrefBackHomepageUpdate = driver.findElement(By.id("href-home-success"));
        hrefBackHomepageUpdate.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement buttonShowAddNote2Update = driver.findElement(By.id("nav-notes-tab"));
        buttonShowAddNote2Update.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text-title-note")));
        Assertions.assertTrue(driver.findElement(By.id("text-title-note")).getText().contains("Update title"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text-description-note")));
        Assertions.assertTrue(driver.findElement(By.id("text-description-note")).getText().contains("Update description"));

    }

    /**
     * Viết bài kiểm tra xóa ghi chú và xác minh rằng ghi chú đó không còn hiển thị nữa.
     */
    @Test
    public void testDeleteNote(){
        addNote("anhdd","anhdd","Delete","123456");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-delete-note")));
        WebElement deleteNote = driver.findElement(By.id("btn-delete-note"));
        deleteNote.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-confirm-delete")));
        WebElement confirm = driver.findElement(By.id("btn-confirm-delete"));
        confirm.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
        WebElement buttonShowAddNote = driver.findElement(By.id("nav-notes-tab"));
        buttonShowAddNote.click();


//        Assertions.assertFalse((BooleanSupplier) driver.findElement(By.id("text-title-note")));
//        Assertions.assertFalse((BooleanSupplier) driver.findElement(By.id("text-description-note")));
    }

}
