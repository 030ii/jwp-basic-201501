package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AddAnswerController;
import next.controller.AddQuestionController;
import next.controller.DelAnswerController;
import next.controller.ListController;
import next.controller.ShowController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/save.next", new AddQuestionController()); // 3번 - 질문하기 기능 
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/api/addanswer.next", new AddAnswerController()); // 5번 - 답변하기 기능 
		mappings.put("/api/delanswer.next", new DelAnswerController()); // 6번 - 답변하기 기능 
		mappings.put("/form.next", new ForwardController("form.jsp"));
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
