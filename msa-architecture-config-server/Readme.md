1. 설정 서버
> 설정 서버는 프로파일(profile)을 관리하는 서버입니다. 설정 파일은 서버에서 사용할 프로파일 정보를 깃 서버에 저장함  
> 설정 서버가 실행 될때 application.yml에 기록된 깃 저장소 주소를 참조하여 깃 저장소 주소에 등록된 yml의 프로파일 정보를 로드함.  
> 깃에 저장된 프로파일명이 config-server.yml 이므로 주소정보는 http://localhost:8888/config-server/refresh 하면 마이크로서비스의 재기동 없이 변경된 설정값이 반영.  
> 스프링 부트 어플리케이션을 생성하고 @EnableConfigServer 어노테이션만 지정하고 기동하면 되는데, 이때 application.yml에 git url 정보를 등록만 하면 됨.    