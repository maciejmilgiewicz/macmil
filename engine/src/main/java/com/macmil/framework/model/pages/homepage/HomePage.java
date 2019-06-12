package com.macmil.framework.model.pages.homepage;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.pages.factory.MultiVariantPageObjectFactory;

public abstract class HomePage extends BasePageObject<HomePage> {
    protected static final String PRICES = "Prices";

    public static HomePage open(String url) {
        getBrowser().open(url);
        return waitForPage();
    }

    public static HomePage waitForPage() {
        return new MultiVariantPageObjectFactory().create(HomePage.class);
    }

    public abstract HomePage clickCreateAccountButton();

    public abstract HomePage clickPricesLink();
}
