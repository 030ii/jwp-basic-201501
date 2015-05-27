/* <3번> 
 * 질문 목록은 정상적으로 동작하지만 질문하기 기능은 정상적으로 동작하지 않는다. 
 * 질문하기 기능을 구현한다. 
 * 질문 추가 로직은 QuestionDao 클래스의 insert method 활용 가능하다.
 * HttpServletRequest에서 값을 추출할 때는 ServletRequestUtils 클래스를 활용 가능하다. 
 * 질문하기를 성공한 후 질문 목록 페이지로 이동해야 한다.
 */

package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddQuestionController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");

		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		ModelAndView mav = jstlView("redirect:/list.next"); // 질문 목록 페이지를 다시 보여줘!
		return mav;
	}
}
