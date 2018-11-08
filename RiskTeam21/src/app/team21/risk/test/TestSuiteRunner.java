package app.team21.risk.test;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

import app.team21.risk.test.config.TestSuiteConfig;

public class TestSuiteRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestSuiteConfig.class);
		 
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            failure.getException().printStackTrace();
        }
 
         
System.out.println("Test successful? " + result.wasSuccessful());

	}

}
