#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* web.xml 파일을 읽는다. 
* 이 파일에 보면 **ContextLoaderListender**와 **DispatcherServlet**의 설정정보가 선언되어있다.
* next.support.context.**ContextLoaderListender**에서는 생성된 객체인 context를 초기화하기 위한 contextInitialized()를 실행하여 초기화된다. (여기서 데이터베이스의 초기화가 이루어짐~)
* core.mvc.**DispatcherServlet**에서는 loadOnStartup = 1 (톰캣이 시작되자마자 실행하라! 라는 뜻!)이기 때문에 init()를 실행하여 초기화된다.
(여기서는 RequestMapping와 initMapping라는 맵핑이 이루어짐~)

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* localhost:8080으로 접근한다.
* 현재 welcome file은 index.jsp이므로 자동으로 index.jsp가 연결된다.
~~~
// index.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect("/list.next");
%>
~~~
* index.jsp파일을 보면 "/list.next"로 리다이렉트된다.
* 클라이언트에서는 'http://localhost:8080/list.next'라는 url로 다시 요청하게 된다.
* 이 요청은 위에 1번에서 말한 DispatcherServlet로 전달되고 service()가 실행된다.
* RequestMapping(= rm)클래스의 findController라는 메소드를 통해 반환된 값을 controller 객체에 넣는다.
* controller 객체의 execute 메소드를 실행하고 그 결과를 model과 view를 갖는 mav(= ModelAndView)라는 객체에 넣는다.
* 이 때, model에는 list.jsp, view에는 질문 목록들이 저장된다.
* render라는 메소드가 실행되면서 질문 목록들이 포워딩된다.
* 질문 목록들이 보이며 첫화면이 이쁘게 완성된다!

#### 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 현재 Dao들은 Controller 상단에서 선언되어 있으므로, Controller를 만들때 맨처음에 딴 한번만 Dao가 만들어 진다.
* 따라서 임계구역의 문제가 발생할 수 있다. (임계구역이란 공유된 자원에 접근을 하는 프로그램의 특정 부분을 말하며, 임계구역에는 오직 한 프로세스만이 그 안에 있을 수 있고 다른 프로세서에 의해 방해받지 않아야 한다.)
* 이러한 임계구역 문제를 해결하기 위해 Dao 객체를 Controller 실행시마다 부르지 않고, 함수 실행시마다 부르는 방법이 있다.
* 즉 함수 안에서 Dao 객체를 새롭게 만들면 된다. (메소드 안에서 객체 선언)
