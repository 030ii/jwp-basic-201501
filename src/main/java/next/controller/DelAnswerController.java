package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DelAnswerController extends AbstractController {
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		Long answerId = ServletRequestUtils.getRequiredLongParameter(request, "answerId");
		Long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");

		System.out.println("asdasd1");
		answerDao.delete(answerId); // 답변 삭제
		System.out.println("asdasd2");
		questionDao.decreaseCountOfComment(questionId); // 답변수 감소  
		System.out.println("asdasd3");
		ModelAndView mav = jstlView("redirect:/"); // 새로고침!
		return mav;
	}
}
