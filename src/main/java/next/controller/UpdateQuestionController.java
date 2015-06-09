package next.controller; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class UpdateQuestionController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QuestionDao questionDao = new QuestionDao();
		
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		Question question = questionDao.findById(questionId);	
		
		ModelAndView mav = jstlView("updateForm.jsp");
		mav.addObject("questions", question);
		return mav; 
	}
}
