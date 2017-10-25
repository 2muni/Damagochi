import java.util.*;

public class DamaExam {

	int n1, n2;
	int randNum, numArith, numConv;
	int cnt=3; //오답 시, 최대 물어보는 횟수 (초기 1회 포함)
	String answer, userAnswer;
	
	//사용자가 정답을 맞추거나 틀리거나를 판별하는 변수
	boolean correct;
	/* 문제 배열
	 * [문제 유형을 구분하는 배열][문제를 구분하는 배열]로 구성된 2차원 배열이다*/
	String[][] exam ={ {"두 값을 더한 값은 얼마인가요?", "첫 값에 다음 값을 뺸 값은 얼마인가요?", "두 값을 곱한 값은 얼마인가요?" }
					  ,{"값 을 2진수로 바꾼 값은 얼마인가요?","값 을 8진수로 바꾼 값은 얼마인가요?","값 을 16진수로 바꾼 값은 얼마인가요?\n(a~f는 소문자로 입력하세요!)"} 
						};
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	
	
	/*---------------------------- 문제 영역 ----------------------------*/
	
	
	//입력된 범위 내에서 난수 생성
	public int randNum(int randNum) {
		randNum = random.nextInt(randNum);
		return randNum;
	}
	//생성된 난수 값을 문제에 출력되는 각각의 변수에 대입  
	public void makeNum(int randNumArea) {
		n1 = randNum(randNumArea);
		n2 = randNum(randNumArea);
	}
	
	//문제 유형 (사칙연산)
	public int arithmetic () {
		//사칙연산 유형의 랜덤한 문제를 출력하기 위한 난수 생성
		numArith = randNum(3);
		switch(numArith) {
		/* 생성된 문제에 맞는 정답 생성
		 * String형 변수에 값을 넣어야 하므로 int형 타입의 데이터를 문자열로 변환*/
		case 0: answer = Integer.toString(n1 + n2); break;
		case 1: answer = Integer.toString(n1 - n2); break;
		case 2: answer = Integer.toString(n1 * n2); break;
		}
		//생성된 문제 번호를 출력
		return numArith;
	}
	
	/* 문제 유형 (10진수를 변환)
	 * 기본적인 내용은 위의 사칙연산과 동일하다*/
	public int convFromDec () {
		numConv = randNum(3);
		switch(numConv) {
		//10진수 -> 2진수 변환 수를 String으로 변환
		case 0: answer = Integer.toBinaryString(n1); break;
		//10진수 -> 8진수 변환 수를 String으로 변환
		case 1: answer = Integer.toOctalString(n1); break;
		//10진수 -> 16진수 변환 수를 String으로 변환
		case 2: answer = Integer.toHexString(n1); break;
		}
		return numConv;
	}
	/* 문제 출력 ( 문제의 숫자 범위, 문제 유형 )
	 * 숫자 범위를 받아서 그에 따른 문제에 등장하는 숫자 난수를 생성
	 * 문제 유형을 받아서 그에 따른 문제를 출력한다*/
	public void examDisplay(int numArea, int examNum) {
		//0 ~ (numArea-1)까지의 숫자 생성
		makeNum(numArea);
		//만약 입력받은 유형이 사칙연산일 경우 아래 구문 수행
		if (examNum == 1) {	
			//사칙연산 문제 생성
			arithmetic();
			System.out.println();
			System.out.print("　[ "+n1+" ]"+"[ "+n2+" ] ");
			//사칙연산 문제 출력
			System.out.println(exam[0][numArith]);
		}
		//만약 입력받은 유형이 진수변환일 경우 아래 구문 수행
		if (examNum == 2) {
			//진수변환 문제 생성
			convFromDec();
			System.out.println();
			System.out.print("　[ "+n1+" ] ");
			System.out.println(exam[1][numConv]);
		}
		//사용자로부터 정답을 입력받을 때 까지 최대 3번의 입력을 받는다
		for(int i=1; i<=cnt; i++) {
			System.out.print("　답안: ");
			//사용자로 부터 입력값을 받음
			userAnswer = scan.next();
			/* 만약 입력값이 생성된 문제에 맞는 답일 경우
			 * String형 입력값과 String형 정답을 비교하기위해 .equals()을 사용
			 * 자바에서 String은 객체이기 때문에, 관계연산자 ==로는 값을 비교할 수 없다*/
			if(userAnswer.equals(answer)) {
				//정답이기 때문에 correct의 상태를 true로 설정
				correct = true;
				System.out.println();
				System.out.println("　정답입니다!");
				System.out.println();
				break;
				//만약 입력값이 답이 아닐 경우
			}else {
				//틀렸기 때문에 correct의 상태를 false로 설정
				correct = false;
				System.out.println();
				System.out.println("　오답입니다! "+"("+i+"/3)");
				System.out.println();
				continue;
			}
		}
	}

}