package com.macmil.framework.model.pages.marketspage;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.pages.factory.MultiVariantPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketsPage extends BasePageObject<MarketsPage> {
    private static final String ASSETS = "All assets";

    @FindBy(css = "input[placeholder='Search all cryptocurrencies']")
    private WebElement searchCryptoInput;

    @FindBy(css = "ul li button")
    private List<WebElement> buttons;

    @FindBy(css = "table tbody tr td:nth-child(3) span")
    private List<WebElement> prices;

    public static MarketsPage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    public static MarketsPage waitForPage() {
        return new MultiVariantPageObjectFactory().create(MarketsPage.class);
    }

    public MarketsPage filterAsset(String asset) {
        buttons.stream()
                .filter(item -> item.getText().equals(ASSETS))
                .findFirst()
                .ifPresent(b -> getBrowser().getButton(b).waitForEnabled().and().click());
        getBrowser().getTextField(searchCryptoInput).type(asset);
        getBrowser().waitForPageAction(5);
        return this;
    }

    public boolean areAllPricesMoreThanZero() {
        prices.forEach(price -> getBrowser().getElement(price).waitForEnabled());
        return prices.stream()
                .noneMatch(p -> Double.parseDouble(p.getText()
                        .replaceFirst("^£", "")
                        .replaceAll(",", "")) <= 0);
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        getBrowser().getElement(searchCryptoInput).waitForEnabled();
        return true;
    }
}
