package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class UpdateController extends AbstractController{

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QuestionDao questionDao = new QuestionDao();
		
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		Question question = new Question(request.getParameter("writer"),request.getParameter("title"),request.getParameter("contents"));
		
		questionDao.update(question, questionId);

		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("questions", question);
		return mav;
	}
}
