package com.macmil.framework.model.pages.factory;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.PageVariantReader;
import com.macmil.framework.webdriver.DriverManager;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MultiVariantPageObjectFactory {
    @SuppressWarnings(value = "unchecked")
    public <T extends BasePageObject> T create(Class<T> clazz) {
        T retval = null;

        for (String variant : load_page_variants(clazz)) {
            T instance;

            try {
                instance = (T) Class.forName(variant).newInstance();
                init_page_elements(instance);
                if (instance.applies()) {
                    retval = instance;
                    break;
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(clazz + "' could not be found!", e);
            } catch (InstantiationException e) {
                throw new IllegalStateException(clazz + "' could not be instantiated!", e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(clazz + "' has no default constructor!", e);
            }
        }

        return retval;
    }

    private <T extends BasePageObject> List<String> load_page_variants(Class<T> clazz) {
        return PageVariantReader.getPageVariants(clazz.getName());
    }

    private <T extends BasePageObject> void init_page_elements(T instance) {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), instance);
    }
}
