package com.macmil.framework.config;

public class Config {
    private String browserType;
    private String browserViewport;
    private String remoteUri;
    private String testUrl;

    private Config(Builder builder) {
        setBrowserType(builder.browserType);
        setBrowserViewport(builder.browserViewport);
        setRemoteUri(builder.remoteUri);
        setTestUrl(builder.testUrl);
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getBrowserViewport() {
        return browserViewport;
    }

    public void setBrowserViewport(String browserViewport) {
        this.browserViewport = browserViewport;
    }

    public String getRemoteUri() {
        return remoteUri;
    }

    public void setRemoteUri(String remoteUri) {
        this.remoteUri = remoteUri;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public static final class Builder {
        private String browserType;
        private String browserViewport;
        private String remoteUri;
        private String testUrl;

        /**
         * Sets browserType field
         * @param browserType browser type, e.g. CHROME
         * @return builder object
         */
        public Builder withBrowserType(String browserType) {
            this.browserType = browserType;
            return this;
        }

        /**
         * Sets browserViewport field
         * @param browserViewport browserViewport size, e.g. LAPTOP
         * @return builder object
         */
        public Builder withBrowserViewport(String browserViewport) {
            this.browserViewport = browserViewport;
            return this;
        }

        /**
         * Sets remoteUri field
         * @param remoteUri URL to Selenium node instance
         * @return builder object
         */
        public Builder withRemoteUri(String remoteUri) {
            this.remoteUri = remoteUri;
            return this;
        }

        /**
         * Sets testUrl field
         * @param testUrl URL of the page under test
         * @return builder object
         */
        public Builder withTestUrl(String testUrl) {
            this.testUrl = testUrl;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
