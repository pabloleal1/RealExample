package mutuamad.process;

import com.atsistemas.appium.CreateExtetReport;
import com.atsistemas.appium.DriverManager;
import com.atsistemas.appium.Process;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mutuamad.Utilities;
import mutuamad.screen.LoginScreen;
import mutuamad.screen.PrincipalScreen;
import org.openqa.selenium.Platform;
import org.testng.Assert;


public class CPQuitTutorial extends Process {

    private PrincipalScreen principalScreen;
    private LoginScreen loginScreen;
    ExtentTest node;

    public CPQuitTutorial(DriverManager driver) {
        super(driver);
        principalScreen = new PrincipalScreen(driver);
        loginScreen = new LoginScreen(driver);

    }

    public void execute(ExtentTest test1) throws Exception {
        node = test1.createNode("Tutorial - Notifications");

        driver.startScreenRecordig();
        Utilities.sleep(10000);
        principalScreen.closeStartingPopUp();
        if(principalScreen.verifyViewFlipperTutorial())
        {
            Utilities.sleep(5000);
            principalScreen.clickbuttonTutoQuit();
            node.log(Status.PASS, "Tras instalar la applicacion, se muestra el tutorial");
        }
        else
        {
            node.log(Status.FATAL, "Tras instalar la applicacion, NO se muestra el tutorial");
        }

        //Verify notifications screen is shown, only Android
        if(driver.getDriver().getCapabilities().getPlatform().is(Platform.LINUX)){
            CreateExtetReport.stepsVerifications(loginScreen.verificNotificationScreenIsShown(), node, "Notifications shown");
            Assert.assertTrue(loginScreen.verificNotificationScreenIsShown(),"La ventana de Notificaciones No se muestra");
            loginScreen.closeNotificationsScreen();
        }

        Utilities.sleep(10000);

    }
}
