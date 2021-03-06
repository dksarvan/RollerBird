package custom.annotations;

import custom.annotations.TesterInfo;
import custom.annotations.TesterInfo.Priority;
import custom.annotations.Test;

@TesterInfo(
	priority = Priority.HIGH,
	createdBy = "mkyong.com",
	tags = {"sales","test" }
)
public class TestExample {

	@Test
	void testA() {
	  if (true)
		throw new RuntimeException("This test always failed");
	}

	@SuppressWarnings("unused")
	@Test(enabled = false)
	void testB() {
	  if (false)
		throw new RuntimeException("This test always passed");
	}

	@Test(enabled = true)
	void testC() {
	  if (10 > 1) {
		// do nothing, this test always passed.
	  }
	}

}