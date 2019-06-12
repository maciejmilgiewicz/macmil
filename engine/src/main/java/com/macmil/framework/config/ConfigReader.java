package com.macmil.framework.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class ConfigReader {
    private static final String DEFAULTS_CONF = "conf/defaults";

    private static final Config CONFIG = ConfigFactory.load(DEFAULTS_CONF);

    private ConfigReader() {
    }

    public static Config getConfig() {
        return CONFIG;
    }

}
