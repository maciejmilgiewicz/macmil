package com.macmil.framework.config;

public class ConfigFactory {
    private static final String BROWSER_TYPE = ConfigReader.getConfig().getString("browser.type");
    private static final String BROWSER_VIEWPORT = ConfigReader.getConfig().getString("browser.viewport");
    private static final String REMOTE_URI = ConfigReader.getConfig().getString("browser.remote_uri");
    private static final String TEST_URL = ConfigReader.getConfig().getString("test.url");

    private ConfigFactory() {
    }

    public static Config getConfig() {
        return new Config.Builder()
                .withBrowserType(BROWSER_TYPE)
                .withBrowserViewport(BROWSER_VIEWPORT)
                .withRemoteUri(REMOTE_URI)
                .withTestUrl(TEST_URL)
                .build();
    }
}
