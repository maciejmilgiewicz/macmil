package com.macmil.framework.model;

import com.typesafe.config.ConfigFactory;

import java.util.List;

public final class PageVariantReader {
    private static final String PAGEVARIANTS = "pagevariants/pagevariants";

    private PageVariantReader() {
    }

    public static List<String> getPageVariants(String page) {
        return ConfigFactory.parseResourcesAnySyntax(PAGEVARIANTS).getStringList(page);
    }
}
