package bgchv.antn;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class JiraWorkLogging {

    //enter ur credentials here
    String login = "";
    String password = "";

    SelenideElement
            loginInput = $("#login-form-username"),
            passwordInput = $("#login-form-password"),
            loginSubmitButton = $("#login-form-submit"),
            moreDropDown = $("#opsbar-operations_more"),
            logWorkButton = $("#log-work"),
            logWorkDateInput = $("#log-work-date-logged-date-picker"),
            logWorkTimeInput = $("#log-work-time-logged"),
            logWorkSubmitButton = $("#log-work-submit"),
            userOptionsDropDown = $("#user-options"),
            logOutButton = $("#log_out");

    @BeforeEach
    void openPageAndLogin() {
        Configuration.baseUrl = "https://jira.mos.social/browse";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @CsvFileSource(resources = "/timeSheet.csv")
    @ParameterizedTest()
    void fillWorkLog(String url, String date, String timeSpent) {
        open("/" + url);
        loginInput.setValue(login);
        passwordInput.setValue(password);
        loginSubmitButton.click();
        moreDropDown.click();
        logWorkButton.click();
        logWorkDateInput.setValue(date + " 18:00");
        logWorkTimeInput.setValue(timeSpent);
        logWorkSubmitButton.click();
        userOptionsDropDown.click();
        logOutButton.click();
    }
}
