package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsSelenideTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.holdBrowserOpen = true;
        Configuration.browser = "Chrome";
    }


    @Step("Открываем главную страницу")
    public void openMainPage() {

        open("https://github.com/");
    }
    @Step("Кликаем по полю ввода")
    public void clickOnInputField() {

        $(".form-control").click();
    }
    @Step("Вводим репозиторий {repo} и нажимаем Enter")
    public void inputRepository(String repo) {

        $("input.header-search-input").setValue(repo).pressEnter();
    }
    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepository(String repo) {

        $(By.linkText(repo)).click();
    }
    @Step("Открываем Issues")
    public void openIssue() {

        $("#issues-tab").click();
    }
    @Step("Проверяем Issues с номером {issue}")
    public void shouldSeeNumberIssue(int issue) {

        $(withText("#" + issue)).should(Condition.exist);
    }

}
