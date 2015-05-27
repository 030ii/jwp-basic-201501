package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerController extends AbstractController {
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		Long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");

		Answer answer = new Answer(writer, contents, questionId);
		answerDao.insert(answer); // 답변 등
		questionDao.increaseCountOfComment(questionId); // 답변수 증가 
		
		ModelAndView mav = jstlView("redirect:/"); // 새로고침!
		return mav;
	}
}
