package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void successfulSubmitFormTest() {
        String firstName = "test name";
        String lastName = "test last name";
        String userEmail = "someemail@mail.ma";
        String gender1 = "Male";
        String gender2 = "Female";
        String gender3 = "Other";
        String userNumber = "9999999999";
        String dateOfBirth = "15 January,2001";
        String subject = "Arts";
        String hobby1 = "Sports";
        String hobby2 = "Reading";
        String hobby3 = "Music";
        String picture = "this-is-a-test.jpg";
        String address = "Test address";
        String state = "Uttar Pradesh";
        String city = "Agra";

        open("/automation-practice-form");
        $("* h5").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender1)).click();
        $(byText(gender2)).click();
        $(byText(gender3)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2001");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText(hobby1)).click();
        $(byText(hobby2)).click();
        $(byText(hobby3)).click();
        $("#uploadPicture").uploadFromClasspath(picture); //load pic
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue("Utta").pressEnter();
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue("Ag").pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();
        $(".h4").shouldHave(text("Thanks for submitting the form"));

        $x("//td[text()='Student Name']/following-sibling::td").shouldHave
                (text(firstName + " " + lastName));
        $x("//td[text()='Student Email']/following-sibling::td").shouldHave(text(userEmail));
        $x("//td[text()='Gender']/following-sibling::td").shouldHave(text(gender3));
        $x("//td[text()='Mobile']/following-sibling::td").shouldHave(text(userNumber));
        $x("//td[text()='Date of Birth']/following-sibling::td").shouldHave(text(dateOfBirth));
        $x("//td[text()='Subjects']/following-sibling::td").shouldHave(text(subject));
        $x("//td[text()='Hobbies']/following-sibling::td").shouldHave
                (text(hobby1 + ", " + hobby2 + ", " + hobby3));
        $x("//td[text()='Picture']/following-sibling::td").shouldHave(text(picture));
        $x("//td[text()='Address']/following-sibling::td").shouldHave(text(address));
        $x("//td[text()='State and City']/following-sibling::td").shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();
        $("* h5").shouldHave(text("Student Registration Form"));
    }
}