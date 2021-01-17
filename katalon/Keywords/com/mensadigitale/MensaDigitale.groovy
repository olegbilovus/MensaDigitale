package com.mensadigitale
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Cookie

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


public class MensaDigitale {

	/**
	 * Keyword per fare il login, bisogna settare le variabili globali <code>email</code>, <code>username</code> e <code>password</code>. 
	 * La variabile <code>password</code> va criptata (Help -> Encrypt Text).
	 * 
	 * Aggiorna i driver del browser (Tools -> Update WebDrivers -> {YOUR_BROWSER}) se <code>switchToWindowIndex</code> lancia eccezioni.
	 * @return 
	 */
	@Keyword(keywordObject = "auto")
	def login(){
		WebUI.openBrowser('')

		WebUI.navigateToUrl('http://localhost:8080/mensadigitale/login.jsp')

		WebUI.click(findTestObject('Object Repository/Page_Login/span_Sign in'))

		WebUI.switchToWindowIndex(1)

		WebUI.setText(findTestObject('Object Repository/Page_Accedi - Account Google/input_MensaDigitale_identifier'), GlobalVariable.email)

		WebUI.click(findTestObject('Object Repository/Page_Accedi - Account Google/div_Avanti_VfPpkd-RLmnJb'))

		WebUI.setText(findTestObject('Object Repository/Page_Web Login Service/input_SPID__username'), GlobalVariable.username)

		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Web Login Service/input_SPID_j_password'), GlobalVariable.password)

		WebUI.click(findTestObject('Object Repository/Page_Web Login Service/button_Login'))

		WebUI.switchToWindowIndex(0)

		WebUI.waitForElementNotPresent(findTestObject('Page_Login/span_Sign in'), 20)

		println System.getProperty("user.dir")

		FileWriter file = new FileWriter("cookie")
		PrintWriter printer = new PrintWriter(file)
		printer.print(DriverFactory.getWebDriver().manage().getCookieNamed("JSESSIONID").getValue())
		printer.close()

		WebUI.verifyElementNotPresent(findTestObject('Page_Login/span_Sign in'), 0)

		//WebUI.closeBrowser()
	}

	/**
	 * Keyword per navaidare ad un url, in automatico sarà aggiunto il cookie all'url, eseguire prima il test case Login
	 * @param url url dove si vuole navigare
	 * @return
	 */
	@Keyword(keywordObject = "auto")
	def navigateWithCookie(String url){

		File file = new File("cookie")

		Cookie ck = new Cookie("JSESSIONID", new Scanner(file).nextLine())

		println ck.getValue()

		WebUI.openBrowser('')

		WebUI.navigateToUrl(url + ";jsessionid=" + ck.getValue())
	}
}