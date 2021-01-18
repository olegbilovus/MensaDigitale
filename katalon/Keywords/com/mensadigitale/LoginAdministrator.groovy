package com.mensadigitale

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Cookie

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class LoginAdministrator {
	/**
	 * Keyword per fare il login come addetto, bisogna settare le variabili globali <code>emailAddetto</code>, <code>usernameAddetto</code> e <code>passwordAddetto</code>.
	 * La variabile <code>passwordAddetto</code> va criptata (Help -> Encrypt Text).
	 *
	 * Aggiorna i driver del browser (Tools -> Update WebDrivers -> {YOUR_BROWSER}) se <code>switchToWindowIndex</code> lancia eccezioni.
	 * @return
	 */
	@Keyword(keywordObject = "auto")
	def login(){
		WebUI.openBrowser('')

		WebUI.navigateToUrl('http://localhost:8080/mensadigitale/login?email=' + GlobalVariable.emailAdmin + '&action=loginGoogle')


		WebUI.switchToWindowIndex(0)

		WebUI.waitForElementNotPresent(findTestObject('Page_Login/span_Sign in'), 20)

		println System.getProperty("user.dir")

		FileWriter file = new FileWriter("cookie")
		PrintWriter printer = new PrintWriter(file)
		printer.print(DriverFactory.getWebDriver().manage().getCookieNamed("JSESSIONID").getValue())
		printer.close()

		WebUI.verifyElementNotPresent(findTestObject('Page_Login/span_Sign in'), 0)

		WebUI.closeBrowser()
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
