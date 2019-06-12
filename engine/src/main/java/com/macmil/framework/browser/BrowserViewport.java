package com.macmil.framework.browser;

import org.openqa.selenium.Dimension;

import java.util.Optional;

public enum BrowserViewport {
    DESKTOP(),
    MOBILE(new Dimension(768, 945));

    private Dimension dimension;

    private BrowserViewport() {
    }

    private BrowserViewport(Dimension dimension) {
        this.dimension = dimension;
    }

    public Optional<Dimension> getDimension() {
        return Optional.ofNullable(dimension);
    }
}
