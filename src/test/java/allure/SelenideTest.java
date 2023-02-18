package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browser = "chrome";
    }


    @Test
    void testSelenide() {

        open("https://github.com/");
        $(".form-control").click();
        $("input.header-search-input").setValue("selenide").pressEnter();
        $(By.linkText("selenide/selenide")).click();
        $("#issues-tab").click();
        $(withText("#2160")).should(Condition.exist);

    }
}
