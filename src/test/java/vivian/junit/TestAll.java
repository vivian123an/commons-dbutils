package vivian.junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestAll extends TestCase {

	public static Test suite(){
		TestSuite suite = new TestSuite("vivian's testSuite");
		suite.addTestSuite(TestDemo.class);
		suite.addTestSuite(TestDemo2.class);
		return suite;
	}
	
	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
