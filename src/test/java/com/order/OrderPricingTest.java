package com.order;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.order.steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports/report.html,json:target/cucumber-reports/json/cucumber.json,junit:target/cucumber-reports/junit/cucumber.xml")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@order_pricing")
public class OrderPricingTest {
} 