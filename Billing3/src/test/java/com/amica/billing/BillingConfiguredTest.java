package com.amica.billing;

import com.amica.esa.componentconfiguration.manager.ComponentConfigurationManager;
import com.amica.escm.configuration.properties.PropertiesConfiguration;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;

import static com.amica.billing.TestUtility.*;
import static com.amica.billing.TestUtility.TEMP_FOLDER;

public class BillingConfiguredTest extends BillingTest{

    @Override
    protected String getCustomersFilename() {
        return "customers_configured.csv";
    }

    @Override
    protected String getInvoicesFilename() {
        return "invoices_configured.csv";
    }

    @Override
    protected Billing createTestTarget() {
        return new Billing();
    }

    @BeforeAll
    public static void setUpBeforeAll() {
        System.setProperty("env.name", "Configured");

        ComponentConfigurationManager manager =
                ComponentConfigurationManager.getInstance();
        manager.initialize();
    }

}
