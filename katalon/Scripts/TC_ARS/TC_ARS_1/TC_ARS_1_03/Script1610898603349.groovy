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

CustomKeywords.'com.mensadigitale.MensaDigitale.navigateWithCookie'('http://localhost:8080/mensadigitale/attivazione.jsp')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Nome_nome'), 'M')

WebUI.setText(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Cognome_cognome'), 'Rossi')

WebUI.click(findTestObject('Object Repository/Page_Attivazione/Page_MD_Login/input_Acconsento_btn btn-success text-center'))

WebUI.verifyAlertNotPresent(2)

WebUI.closeBrowser()

