
public class DamaActivity {
	
	DamaSystem sys = new DamaSystem();
	DamaStatus stat = new DamaStatus();
	DamaExam exam = new DamaExam();
	
	/*---------------------------- 행위 영역 ----------------------------*/		
	
	/* 작명
	 * 다마고치의 이름을 사용자로부터 입력받는 메소드*/
	public void naming(){
		while(true){
			System.out.println("────────────────────────────────");
			System.out.println("　이름을 입력하세요. (공백 포함 최대 6글자)");
			System.out.println("────────────────────────────────");
			System.out.println();
			System.out.print("　입력: ");
			/* 사용자 입력 값을 String형 name변수에 저장 
			 * 공백을 포함한 문장을 입력받기위해 nextLine을 사용*/
			stat.name = sys.scan.nextLine();
			//name변수의 글자 수를 int형 num변수에 저장
			sys.num = stat.name.length();
			//만약 이름의 길이가 6글자를 넘어가면 다시 사용자로부터 입력값을 받는다
			if(sys.num>6){
				System.out.println();
				System.out.println("　6자 이내의 이름을 입력하세요.");
				sys.sleep(500);
				//while 반복문의 처음으로 반환
				continue;			
			}else {
				sys.sleep(500);
				System.out.print("　["+stat.name+"](으)로 시작합니다.");
				for(int i=0; i<4; i++) {
					sys.sleep(500);
					System.out.print(".");
					}
				sys.sleep(800);
				System.out.println("!");
				sys.sleep(500);
				System.out.println();
				//while 반복문 탈출
				break;				
			}
		}
	}
	
	/* 기본 행위
	 * 프로그램의 레이아웃을 출력하는 메소드
	 * 여러 행위들의 선택을 나타낸다
	 * 객체화 된 데이터들의 총집합*/
	public void rayout(){
		while(true){
			System.out.println("┌──────────────────────────────┐");
			//DamaSystem 클래스의 날짜, 시간을 불러와 출력
			System.out.println("　"+DamaSystem.dayCnt+"日 / 현재시각 "+sys.time+" : "+"00");
			System.out.println();
			//DamaStatus 클래스의 캐릭터 형상화 메소드
			stat.character();
			System.out.print("　"+stat.name+"의 상태: ");
			//DamaStatus 클래스의 상태 알람 메소드 
			stat.status(); 	
			System.out.println("├──────────────────────────────┤");
			//DamaStatus 클래스의 상태 출력 메소드 
			stat.displayStatus(); 		
			sys.sleep(500);
			System.out.println("├──────────────────────────────┤");
			//DamaStatus 클래스의 엔딩 처리 메소드 
			stat.ending(); 				
			System.out.println("　1. 밥먹이기 (2h)");
			System.out.println("　2. 가르치기 (6h)");	
			System.out.println("　3. 놀아주기 (2h)");
			System.out.println("　4. 씻기기 (1h)");				
			System.out.println("　5. 잠자기 (12h)");
			System.out.println("　99. 능력치 확인");
			System.out.println("├──────────────────────────────┤");
			sys.sleep(500);
			//사용자로부터 숫자를 입력받고, 그에 따른 행위 메소드를 실행하는 구문
			while(true) {
				System.out.print("　선택: ");
				//DamaSystem 클래스의 번호 입력 메소드
				sys.inputNum();	
				//DamaSystem 클래스의 변수 num을 조건으로 하는 switch문
				switch(sys.num){
				case 1:	eat();		break;
				case 2:	study();	break;
				case 3:	play();		break;
				case 4:	wash();		break;
				case 5:	sleep(); 	break;
				case 99: stat.displayAbility();	break;
				//사용자로부터 입력받은 숫자가 case에 해당하는 숫자가 아닐 경우 실행되는 구문
				default:
					System.out.println("　다시 입력하세요!");
					System.out.println("├──────────────────────────────┤");
					//직접적인 행동이 아니기 때문에 DamaStatus 클래스의 setData 메소드가 실행되어선 안됨
					DamaStatus.trigger=false;
					//while 반복문의 처음으로 반환
					continue;
				}
				//while 반복문을 탈출
				break;
			}
			sys.sleep(500);
			//DamaStatus 클래스의 데이터 갱신 메소드 
			stat.setData(); 				
			System.out.println("└──────────────────────────────┘");
		}
	}
/* 기본 행위 관련 메소드 */	
	/* 먹기
	 * 포만감이 과도하게 올라가지 않도록 한도값을 지정
	 * 밥을 먹을 때 마다 건강이 10씩 증가하고, 건강이 100이 넘지 않도록 조절*/
	public void eat(){
		//포만감이 90 이하일때 아래 구문들을 수행한다
		if(stat.food < 90){
			//DamaSystem 클래스의 확률 결과 생성 메소드
			sys.probNum();
			/* DamaStatus 클래스의 컨디션 배열 출력, 0은 건강과 관련된 문자열 배열이다
			 * 앞서 생성된 sys.probNum을 바탕으로 컨디션 문자열을 출력한다*/
			System.out.println(stat.msg[0][sys.probNum]);
			System.out.println();
			sys.sleep(500);
			//만약 건강이 90이하면 건강이 10 올라간다
			if(stat.health<=90) {
				stat.health += 10;
				System.out.println("　건강 +10");
			//만약 건강이 90초과 100이하면 100에 맞게 건강이 올라간다
			}else if(stat.health>90 && stat.health<=100) {
				System.out.println("　건강 +"+(100-stat.health));
				stat.health=100;
			}
			//DamaStat 클래스의 스탯 난수 생성(항목, 난수 범위) 
			stat.ranStatCir(3, 20);
			//앞서 생성된 sys.probNum을 바탕으로 컨디션에 따른 상태 수치 증감을 수행
			switch(sys.probNum){
			case 0: stat.ranStatCir(4, 20); break;
			case 1: stat.ranStatCir(4, 30); break;
			case 2: stat.ranStatCir(4, 60);	}
			stat.ranStatCir(-5, 20);
			//DamaSystem 클래스의 시간 관리 메소드
			sys.timeCheck(2);
			//직접적인 행위이기에 setData()메소드를 관리하는 trigger 상태를 true로 설정
			DamaStatus.trigger=true;
		//포만감이 90보다 크면 먹기 행위가 수행되지 않는다
		}else{
			System.out.println("　더이상 먹일 수 없다!");
			DamaStatus.trigger=false;
		}
	}
	/* 공부
	 * 나이가 2살보다 적으면 단순히 상태 수치만 증감
	 * 만약 나이가 2살이 넘어가면 다마고치의 질문을 사용자가 해결해야한다
	 * DamaExam 클래스를 객체화 시켜 질문을 받는 메소드를 생성한다 */
	public void study() {
		//만약 나이가 2살보다 적으면 아래 구문 수행
		if(stat.age < 2) {
			//설명은 먹기 메소드와 동일
			sys.probNum();
			System.out.println(stat.msg[1][sys.probNum]);
			System.out.println();
			switch(sys.probNum) {
			case 0: stat.ranStatCir(11, 20); break;
			case 1: stat.ranStatCir(11, 30); break;
			case 2: stat.ranStatCir(11, 60);	}
		//만약 나이가 2살이상, 4살 미만이면 아래 구문 수행	
		}else if (stat.age < 4) {
			/* DamaExam 클래스의 문제 제출 메소드 ( 숫자 범위, 문제 유형 )
			 * 유형 1의 경우 기본적인 더하기, 뺼셈, 곱셈을 출력한다*/
			exam.examDisplay(100, 1);
			//만약 문제를 맞출 시, 아래 구문 수행
			if(exam.correct==true) {
				stat.ranStatCir(11, 30);
			}
		//만약 나이가 4살 이상이면 아래 구문 수행
		}else {
			//유형 2의 경우 10진수를 2진수, 8진수, 16진수로 변환하는 문제를 출력한다
			exam.examDisplay(200, 2);
			if(exam.correct==true) {
				stat.ranStatCir(11, 30);
			}
		}
		stat.ranStatCir(2, 50);
		stat.ranStatCir(3, 50);
		stat.ranStatCir(-4, 50);
		sys.timeCheck(6);
		DamaStatus.trigger=true;
	}
	//놀기와 씻기 메소드는 먹기 메소드와 거진 유사하므로 주석을 생략한다
	//놀기
	public void play(){
		sys.probNum();
		System.out.println(stat.msg[2][sys.probNum]);
		System.out.println();
		switch(sys.probNum){
		case 0: stat.ranStatCir(-2, 20); break;
		case 1: stat.ranStatCir(-2, 30); break;
		case 2: stat.ranStatCir(-2, 60); }
		stat.ranStatCir(3, 30);
		stat.ranStatCir(-4, 30);
		stat.ranStatCir(-5, 30);
		sys.timeCheck(2);
		DamaStatus.trigger=true;
	}
	//씻기
	public void wash(){
		System.out.println("　<겸사겸사 똥도 치웁니다...>");
		System.out.println();
		sys.sleep(500);
		System.out.println("　청결도 +"+(100-stat.clean));
		stat.clean=100;
		stat.ranStatCir(-2, 10);
		stat.ranStatCir(3, 20);
		stat.ranStatCir(-4, 10);
		DamaStatus.trigger=true;
		sys.timeCheck(1);
	}
	/* 잠자기
	 * 피로도가 70보다 적어질때만 수행*/
	public void sleep(){
		System.out.println("　< zzzZZZ >");
		System.out.println();
		//만약 피로도가 70미만이면 아래 구문 수행
		if(stat.fatigue<70){
			System.out.println("　아직 피곤하지 않은 것 같다...");
			DamaStatus.trigger=false;
		//만약 피로도가 70이상이면 아래 구문 수행
		}else{
			sys.sleep(500);
			//피로도를 0으로 초기화
			System.out.println("　피로도 -"+stat.fatigue);
			stat.fatigue=0;
			stat.ranStatCir(-2, 20);
			stat.ranStatCir(-4, 30);
			sys.timeCheck(12);
			DamaStatus.trigger=true;
		}
	}
	
}