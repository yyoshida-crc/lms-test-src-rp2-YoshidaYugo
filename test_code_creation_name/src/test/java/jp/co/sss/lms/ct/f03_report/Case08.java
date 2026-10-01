package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {

		goTo("http://localhost:8080/lms/");
		assertEquals("ログイン | LMS", webDriver.getTitle());

		getEvidence(new Object() {

		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {

		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.id("password")).sendKeys("PasswordAA01");
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		WebDriverUtils.visibilityTimeout(By.cssSelector(".navbar-brand"), 10);
		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle());
		WebDriverUtils.getEvidence(new Object() {

		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		List<WebElement> detailElements = webDriver.findElements(By.className("btn-default"));
		WebElement deteilNumberElement = detailElements.get(2);

		deteilNumberElement.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", pageTitle);

		getEvidence(new Object() {

		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		List<WebElement> submitElements = webDriver.findElements(By.className("btn-default"));
		WebElement submitNumberElement = submitElements.get(3);

		scrollTo("document.body.scrollHeight");

		submitNumberElement.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート登録 | LMS", pageTitle);

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		final WebElement reportElement = webDriver.findElement(By.id("content_1"));
		final WebElement submissionElement = webDriver.findElement(By.className("btn-primary"));

		scrollTo("document.body.scrollHeight");

		reportElement.clear();

		reportElement.sendKeys("case08Test");
		submissionElement.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("セクション詳細 | LMS", pageTitle);

		getEvidence(new Object() {

		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {

		final WebElement userDetailElement = webDriver
				.findElement(By.cssSelector("a[href=\\\"/lms/user/detail\\\"]\""));

		userDetailElement.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("ユーザー詳細", pageTitle);

		getEvidence(new Object() {

		});

	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {

		List<WebElement> detailElements = webDriver.findElements(By.className("btn-default"));
		WebElement detailButtonElement = detailElements.get(2);

		scrollTo("document.body.scrollHeight");
		detailButtonElement.click();

		String pageTitle = webDriver.getTitle();
		assertEquals("レポート詳細 | LMS", pageTitle);

		getEvidence(new Object() {

		});

	}

}
