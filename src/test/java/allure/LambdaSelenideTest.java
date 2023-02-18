package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaSelenideTest {


    private static final String BROWSER = "Chrome";

    @BeforeAll
    static void beforeAll() {

        Configuration.holdBrowserOpen = true;
        step("Открывается браузером " + BROWSER, () -> {
            Configuration.browser = BROWSER;
        });

    }

    private static final String REPOSITORY = "selenide/selenide";
    private static final int ISSUES = 2152;

    @Test
    void testSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Кликаем по полю ввода", () -> {
            $(".form-control").click();
        });
        step("Вводим репозиторий " + REPOSITORY + " и нажимаем Enter", () -> {
            $("input.header-search-input").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем Issues с номером" + ISSUES, () -> {
            $(withText("#" + ISSUES)).should(Condition.exist);
        });

    }

    @Test
    public void testAnnotatedStepsSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepsSelenideTest steps = new StepsSelenideTest();

        steps.openMainPage();
        steps.clickOnInputField();
        steps.inputRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.openIssue();
        steps.shouldSeeNumberIssue(ISSUES);

    }
}
