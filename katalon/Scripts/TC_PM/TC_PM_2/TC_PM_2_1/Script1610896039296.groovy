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

CustomKeywords.'com.mensadigitale.MensaDigitale.navigateWithCookie'('http://localhost:8080/mensadigitale/inserisciValutazione.jsp')

CustomKeywords.'com.mensadigitale.MensaDigitale.navigateWithCookie'('http://localhost:8080/mensadigitale/inserisciValutazione.jsp')

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/mensadigitale/login.jsp;jsessionid=798F2A5B21A79B93D31697AD7248EA70')

WebUI.click(findTestObject('Object Repository/Page_Mensa Digitale/button_Toggle navigation'))

WebUI.click(findTestObject('Object Repository/Page_Mensa Digitale/a_Consulta Men'))

WebUI.click(findTestObject('Object Repository/Page_Mensa Digitale/span_Toggle navigation_navbar-toggler-icon'))

WebUI.click(findTestObject('Object Repository/Page_Mensa Digitale/a_Valuta Servizio'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Recensioni/select_TESTING                             _0e66db'), 
    'SEC TESTING', true)

WebUI.click(findTestObject('Object Repository/Page_Recensioni/input_Valutazione_valutazione'))

WebUI.click(findTestObject('Object Repository/Page_Recensioni/input_Valutazione_valutazione'))

WebUI.click(findTestObject('Object Repository/Page_Recensioni/input_Valutazione_valutazione'))

WebUI.click(findTestObject('Object Repository/Page_Recensioni/input_Valutazione_valutazione'))

WebUI.click(findTestObject('Object Repository/Page_Recensioni/input_Valutazione_valutazione'))

WebUI.click(findTestObject('Object Repository/Page_Recensioni/button_Invia                        Valutazione'))

