package vivian.junit;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDemo2 extends TestCase{

	public void testAdd() {
        int result = AddCaculate.add(1, 1);
        Assert.assertEquals(2, result);
        assertEquals(2,result);
    }
}
