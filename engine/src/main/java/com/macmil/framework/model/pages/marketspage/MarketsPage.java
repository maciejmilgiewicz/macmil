package com.macmil.framework.model.pages.marketspage;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.pages.factory.MultiVariantPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MarketsPage extends BasePageObject<MarketsPage> {
    private static final String ASSETS = "Assets";

    @FindBy(css = ".filter-bar-item .menu-item")
    private List<WebElement> filterBarItems;

    @FindBy(id = "rankingsMenuAssets")
    private WebElement assetsFilterMenu;

    @FindBy(css = ".search-menu-row")
    private List<WebElement> assetsFilters;

    @FindBy(css = ".rankings-list-price")
    private List<WebElement> prices;

    public static MarketsPage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    public static MarketsPage waitForPage() {
        return new MultiVariantPageObjectFactory().create(MarketsPage.class);
    }

    public MarketsPage filterAsset(String asset) {
        filterBarItems.stream()
                .filter(item -> item.getText().equals(ASSETS))
                .findFirst()
                .ifPresent(b -> getBrowser().getButton(b).waitForEnabled().and().click());
        getBrowser().getElement(assetsFilterMenu).waitForDisplayed();
        assetsFilters.stream()
                .filter(item -> item.getAttribute("data-value").equalsIgnoreCase(asset))
                .findFirst()
                .ifPresent(b -> getBrowser().getButton(b).waitForEnabled().and().click());
        return this;
    }

    public boolean areAllPricesMoreThanZero() {
        return prices.stream()
                .filter(price -> Double.valueOf(price.getText()) <= 0)
                .collect(Collectors.toList())
                .isEmpty();
    }

    @Override
    public boolean applies() {
        getBrowser().waitForPageToLoad();
        return true;
    }
}
