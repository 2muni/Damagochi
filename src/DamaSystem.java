/* 자바 외부 패키지 파일을 불러 사용하기위한 import 명령
 * java.패키지 명.클래스 명 형식으로 불러온다*/
import java.io.*;		//입출력과 거기에 따라오는 예외처리를 위한 import
import java.util.*;		//Scanner 및 Random, 예외처리를 하기위한 import

public class DamaSystem {
	//시스템 관련 변수
	int squ, num, probNum;
	int time = 9;
	//dayCnt 변수의 경우, 모든 클래스에서 값이 동일해야 하므로 전역변수(static)로 선언
	static int dayCnt = 1;
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	
	/*---------------------------- 시스템 영역 ----------------------------*/		
	
	/* 확률에 따라 결과 값을 출력하는 메소드
	 * case가 10개인 스위치문을 작성하여 확률을 명시하고 생성
	 * 0~9의 범위에서 난수를 생성하고, 생성되는 각각의 난수 범위에 따라 결과물을 출력
	 * 각각의 결과물이 생성되는 확률: 최선의 결과(30%), 보통의 결과(60%), 최악의 결과(10%) */
	public int probNum() {
		num = random.nextInt(10);	// 0~9까지 범위 내의 난수 생성
		//생성된 난수를 조건으로한 switch문 작성
		switch(num) {
		//case의 경우, break를 선언하지 않으면 계속해서 아래 case문을 실행
		//가령 num 값이 9인 경우, case 7까지 내려가 probNum = 2 를 수행
		case 9:
		case 8:
		case 7: probNum = 2; break;	//최선의 결과
		case 6: 
		case 5: 
		case 4:
		case 3:
		case 2:
		case 1: probNum = 1; break;	//보통의 결과
		default: probNum = 0;		//최악의 결과
		}
		//return을 선언하여 메소드가 probNum이라는 값을 가진다
		return probNum;
	}
	
	/* 숫자를 입력받을 때 발생하는 예외(에러)를 처리하기 위한 메소드
	 * 가령 숫자를 입력받아야 할 때에, 문자를 입력받았을 때를 처리할 수 있다*/
	public void inputNum() {
		//올바른 값을 입력받을 때까지 수행되야 하므로 while(true)를 사용하였다
		while(true) {
			/*예외(에러)를 처리하기위해 try...catch를 사용했다.
			 * try{ 수행문장 } catch( 예외 예외변수명  ) { 예외 발생 시 수행 문장 }*/
			try {
				num = scan.nextInt();	//int형 num변수에 사용자의 입력을 받음
				break;
			//입력받은 데이터의 자료형이 변수와 다를 경우 InputMismatchException 에러가 발생한다.
			}catch( InputMismatchException ime) {
				//Scanner 객체를 새로 생성하여 에러를 해결한다.
				scan = new Scanner(System.in);
				System.out.println("　숫자만 입력하세요!");
				System.out.println("├──────────────────────────────┤");	
				System.out.print("　선택: ");
			}
		}
	}
	/* 스레드를 일정시간 정지시키는 메소드
	 * 프로그램의 흐름을 좀 더 유연하게 보여주기 위해 사용
	 * 따로 스레드를 상속받지 않았으므로, 현재 프로그램은 단일 스레드로 운용된다*/
	public void sleep (int sleepMs) {
		//사용중인 스레드를 ms단위로 일시정지 (1000ms = s)
		try { 
			Thread.sleep(sleepMs);
		//스레드가 중단되었을 때 발생하는 InterruptedException 예외처리
		}catch(InterruptedException ie) {
			/* 단순히 스레드를 일정시간동안 중지 하는 것 뿐이므로 
			 * 예외가 발생했을 때 따로 수행되는 문장은 없다*/
		}
	}
	/* enter입력 시 프로그램이 종료되는 메소드
	 * 편의를 위해 System.in.read()를 사용하여 사용자의 입력을 받았다 */
	public void pause() {
		try {
			/* Enter를 기준으로 사용자의 입력값을 아스키 코드로 받는다
			 * 단, Enter키 역시 아스키코드를 가지므로 연속된 사용엔 주의가 따름*/
      		System.in.read();
      		/* 기본 입출력을 처리할 때엔, 반드시 예외처리가 필요하다 
      		 * 입출력 관련 에러 IOException 예외처리*/
    	}catch (IOException e) { 
    		/* 단순히 입출력을 받는 것 뿐이므로 
			 * 예외가 발생했을 때 따로 수행되는 문장은 없다*/
    		}
		/* 실행중인 JVM을 종료하는 메서드
		 * int형 매개변수는 상태 코드를 의미하며, 정상종료 시 관례적으로 0을 작성한다*/
		System.exit(0);
	}
	
	//데이터 시각화
	public void square(int data){
		//각 데이터 수치를 매개변수 data를 기준으로 10 단위씩 ■ 출력
		squ = data / 10;
		for(int i = 0; i < squ; i++){
			System.out.print("■");
		}
		System.out.println();
	}
	//시간 관리
	public void timeCheck(int hour){
		//각 항목당 소요되는 시간인 매개변수 hour가 시간 time에 더해진다
		time += hour;
		//만약 시간이 24시를 넘어가면 날짜가 더해진다
		if(time>24){
			time = time-24;
			dayCnt++;
		}
	}
	
}
