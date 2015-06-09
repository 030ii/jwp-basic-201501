package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ApiDelQuestionController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		
		long questionId = Integer.parseInt(request.getParameter("questionId"));
		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
	    String questionWriter = question.getWriter();
		
		if (!answers.isEmpty()) { 
			for (int i = 0; i < answers.size(); i++) {
				if (answers.get(i).getWriter() != questionWriter) {
					ModelAndView mav = jstlView("redirect:/show.next?questionId="+questionId);
					mav.addObject("result", Result.fail("질문자와 다른 사용자가 답변을 추가했기 때문에 이 질문은 삭제할 수 없습니다.")); // 실패 
					return mav;
				}
			}
		}

		questionDao.delete(questionId);
		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("result", Result.ok()); // 성공 

		return mav;
	}
}