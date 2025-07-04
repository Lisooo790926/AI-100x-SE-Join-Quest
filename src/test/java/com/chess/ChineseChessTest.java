package com.chess;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/chinese.chess.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.chess.steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports/report.html,json:target/cucumber-reports/cucumber.json,junit:target/cucumber-reports/cucumber.xml")
public class ChineseChessTest {
} 