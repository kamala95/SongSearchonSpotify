import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class SongSearchOnSpotify {
    public static void main(String[] args)  {
        WebDriver driver = null;

        try{
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // set implicit wait

           // Navigate to Spotify's Web Player homepage. (https://open.spotify.com/)
            driver.get("https://open.spotify.com/");

            // Click on the 'Log In' link.
            driver.findElement(By.xpath("//button[@data-testid='login-button']")).click();

            // Enter valid credentials (email and password).
            String email = "emilyjohnson202324@gmail.com";
            String password = "Duotech2023";

            driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys(password);
            driver.findElement(By.xpath("//button[@data-testid='login-button']")).click();

             // Validate that the profile icon is displayed at the top right, indicating a successful login.
            // (it is a <figure> element)
            WebElement expected = driver.findElement(By.xpath("//div[@data-testid='placeholder-wrapper']"));
            System.out.println(expected.isDisplayed());

            // Click on Search link on the left bar and Use the search bar at the top to search for a specific artist and song,
            // e.g., "Adele Hello".
            driver.findElement(By.xpath("//a[@aria-label='Search']")).click();
            driver.findElement(By.xpath("//input[@class='Type__TypeElement-sc-goli3j-0 ieTwfQ QO9loc33XC50mMRUCIvf']")).sendKeys("Adele Hello",Keys.ENTER);
            driver.findElement(By.xpath("//button[@aria-label='Play']")).click();

            // Once song is playing, validate the play functionality by verifying the song name (Hello) and artist (Adele)
            // in the now-playing section at the left bottom corner.
            WebElement song = driver.findElement(By.xpath("//a[@data-testid='context-item-link']"));
            WebElement artist = driver.findElement(By.xpath("//a[@data-testid='context-item-info-artist']"));
            Assert.assertEquals(song.getText(),"Hello");
            Assert.assertEquals(artist.getText(),"Adele");

            // Click on the profile icon at the top right to access the account dropdown.
            driver.findElement(By.xpath("//div[@data-testid='placeholder-wrapper']")).click();

            // Click 'Log out'.
            driver.findElement(By.xpath("//button[@data-testid='user-widget-dropdown-logout']")).click();
            // Validate the user has been logged out by ensuring the 'Log In' button is present.

             WebElement expect = driver.findElement(By.xpath("//span[@class='ButtonInner-sc-14ud5tc-0 bABUvF encore-inverted-light-set']"));
             System.out.println(expect.isDisplayed());

        }finally {
            driver.quit();
        }
        }
    }


