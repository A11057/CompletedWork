package com.amica.billing;

import com.amica.billing.parse.FlatParser;
import com.amica.billing.parse.QuotedCSVParser;
import com.amica.esa.componentconfiguration.manager.ComponentConfigurationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;


public class ParserFactoryConfiguredTest {

    @BeforeAll
    public static void setUpBeforeAll() {
        System.setProperty("env.name", "Quoted");

        ComponentConfigurationManager manager =
                ComponentConfigurationManager.getInstance();
        manager.initialize();
    }

    @Test
    public void testDefaultFlatParser() {
        ParserFactory.resetParsers();
        assertThat(ParserFactory.createParser("test.sherry"),instanceOf(FlatParser.class));
    }

    @Test
    public void testQuotedCsvParser() {
        ParserFactory.resetParsers();
        assertThat(ParserFactory.createParser("test.csv"),instanceOf(QuotedCSVParser.class));
    }
}
