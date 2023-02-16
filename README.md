네이밍 규칙

카멜 표기법(camelCase) 
단어 전체적으로 소문자를 사용하지만, 맨 첫 글자를 제외한 각 합성어의 첫 글자만 대문자로 표기한다. 
합성한 단어의 모양이 쌍봉낙타의 등과 비슷하다는 뜻에서 이름붙었다.

파스칼 표기법(PascalCase)
첫 단어를 대문자로 시작하는 표기법
예시: BackgroundColor, TypeName, PowerPoint

스네이크 표기법
스네이크 표기법은 프로그래밍에서 파일, 변수, 함수 등 대상의 이름의 띄어쓰기를 언더바(_)로 표기

 0. 공통규칙
 0.1 공통적으로 카멜 표기법(camelCase) 사용한다
 기본적으로 변수명을 모두 소문로 쓰고 여러 단어가 이어지는 경우 첫단어를 제외하고 각 단어의 첫글자만 대문자로 지정한다
     ex) camelCase, memberInsert

 0.2 약어는 최대한 쓰지않으며 풀네임을 사용한다
     예외) id, pw

 0.3 반의어는 반드시 대응되는 개념으로 사용한다
     ex) - start/finish, - insert/delete, - first/last, ...


 1. JSP(view)  
 1.1 파일명은 카멜 표기법(camelCase) 사용한다 

 - 목록 : list  ex) memberList.jsp 
 - 입력 : insert  ex) memberInsert.jsp 
 - 수정 : update  ex) memberUpdate.jsp 
 - 삭제 : delete  ex) membertDelete.jsp 

1.2 속성값은 영어로 작성하며, 스네이크 표기법을 사용한다.


 2. Package  
 2.1 모든 package는 하단에 표기된 상위 package를 가지며 영문소문자만을 사용한다

 상위패키지명 com.smart.mobility  

 

 2.2 패키지명은 표준 패턴을 사용한다
    ex) com.smart.mobility.폴더명.controller
    ex) com.smart.mobility.폴더명.service

 2.3 패키지명은 한 단어의 명사만을 사용한다
     좋은 ex) com.smart.mobility.member.Insert
     나쁜 ex) com.smart.mobility.memberInsert


 3. Class  
 3.1 모든 class는 영문자로만 구성하고 반드시 첫글자는 대문자로 시작하며 파스칼식으로 표현한다

 - DAO : 모든 DAO는 해당 테이블명을 파스칼식으로 표현하고 마지막에 Dao를 붙인다
 - DTO : 모든 DTO는 해당 테이블명(객체명)을 파스칼식으로 표현한다
 - Controller : 모든 Controller는 해당 테이블명을 파스칼식으로 표현하고 뒤에 Controller를 붙인다
 - Service : 모든 Service는 해당 테이블명을 파스칼식으로 표현하고 뒤에 Service를 붙인다
 - Mapper : 모든 Mapper는 해당 테이블명을 파스칼식으로 표현하고 뒤에 Mapper를 붙인다


 4. Method  
 4.1 메서드명에는 카멜 표기법을 사용한다
     ex) public void memberInsert(String memberId) {}


 4.2 속성에 접근하는 메서드명의 접두사는 'get' 과 'set' 을 사용한다

 4.3 데이터를 조회하는 메서드명의 접미사는 'List' 를 사용한다
     ex) public List<MemberDto> getMemberList();

 4.4 데이터를 입력하는 메서드명의 접미사는 'Insert' 를 사용한다
     ex) public int memberInsert(MemberDto memberId);

 4.5 데이터를 수정하는 메서드명의 접미사는 'Update' 를 사용한다
     ex) public int memberUpdate(MemberDto member);

 4.6 데이터를 삭제하는 메서드명의 접미사는 'Delete' 를 사용한다
     ex) pulbic int memberDelete(MemberDto memberId);


 5. Variable  
 5.1 변수와 메서드의 파라미터에는 카멜 표기법 사용
     ex) private MemberDto memberId

 5.2 변수에 모든 의미를 포함한다(약어는 쓰지 않는다 단, id와  pw는 제외한다)

 5.3 session에 들어가는 변수는 영문대문자로만 구성한다
     ex) SID, SLELVEL
