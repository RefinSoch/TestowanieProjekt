import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl(webPage)

// Need to reload the page due to page trying to start notifications.
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_reddit the front page of the internet/div_I Agree__1DK52RbaamLOWw5UPaht_S _3Ig_Es_1cdf1d'), 
    0)

WebUI.navigateToUrl(webPage)

WebUI.click(findTestObject('Object Repository/Page_reddit the front page of the internet/a_Log In'))

WebUI.verifyElementNotVisible(findTestObject('Object Repository/Page_reddit the front page of the internet/div_Incorrect username or password'), 
    FailureHandling.STOP_ON_FAILURE)

TestData logins = findTestData('Test_data')

for (def row = 1; row <= logins.getRowNumbers(); row++) {
	Login = logins.getValue('Login', row)
	Password = logins.getValue('Password', row)
    WebUI.setText(findTestObject('Object Repository/Page_reddit the front page of the internet/input_or_username'), Login)

    WebUI.setText(findTestObject('Object Repository/Page_reddit the front page of the internet/input_Username_password'), Password)

    WebUI.click(findTestObject('Object Repository/Page_reddit the front page of the internet/button_Log In'))

    WebUI.verifyElementVisible(findTestObject('Object Repository/Page_reddit the front page of the internet/div_Incorrect username or password'), 
        FailureHandling.STOP_ON_FAILURE)

    assert WebUI.verifyElementVisible(findTestObject('Object Repository/Page_reddit the front page of the internet/div_Incorrect username or password')) == 
    true : 'login failed as username is not correct'
}

WebUI.closeBrowser()

