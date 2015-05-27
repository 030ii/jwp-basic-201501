#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* web.xml 파일을 읽는다. 
* 이 파일에 보면 **ContextLoaderListender**와 **DispatcherServlet**의 설정정보가 선언되어있다.
* next.support.context.**ContextLoaderListender**에서는 생성된 객체인 context를 초기화하기 위한 contextInitialized()를 실행하여 초기화된다. (여기서 데이터베이스의 초기화가 이루어짐~)
* core.mvc.**DispatcherServlet**에서는 loadOnStartup = 1 (톰캣이 시작되자마자 실행하라! 라는 뜻!)이기 때문에 init()를 실행하여 초기화된다.
(여기서는 RequestMapping와 initMapping라는 맵핑이 이루어짐~)

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 

