package custom.annotations;

import java.lang.annotation.Annotation;
import custom.annotations.TesterInfo;

public class RuneTest {

  public static void main(String[] args) throws Exception {

	

  }
  
  public void processInfo(Class <T>) {
	  
	  System.out.println("Testing...");	
	  
//Process @TesterInfo
	  
	TestExample obj = new TestExample();
	if (obj.isAnnotationPresent(TesterInfo.class)) {

		Annotation annotation = obj.getAnnotation(TesterInfo.class);
		TesterInfo testerInfo = (TesterInfo) annotation;

		System.out.printf("%nPriority :%s", clsname.priority());
		System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
		System.out.printf("%nTags :");

		int tagLength = testerInfo.tags().length;
		for (String tag : testerInfo.tags()) {
			if (tagLength > 1) {
				System.out.print(tag + ", ");
			} else {
				System.out.print(tag);
			}
			tagLength--;
		}

		System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());
		System.out.printf("This sysout is used to check fetch merge git commands");
	}
  }
 
}