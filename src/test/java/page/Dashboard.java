package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Dashboard {
    private static ElementsCollection cards = $$(".list__item");
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public Dashboard() {
        heading.shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public Transfer selectCardButton(String cardId) {
        SelenideElement element = $("[data-test-id='" + cardId + "']");
        element.find("button[data-test-id=action-deposit]").click();
        return new Transfer();
    }
}