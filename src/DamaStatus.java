import java.util.Random;

public class DamaStatus {
	//다마고치 수치 변수
	int health = 100, stress = 0, fatigue = 0, 
		food = 50, clean = 100, age = 1, knowledge = 0,
		randStat;
	
	String name;
	
	//지능 수치를 뜻하는 데이터를 담는 변수, 지능 수치에 따라 엔딩의 결과가 바뀐다
	int end_1 = 300, end_2 = 700, end_3 = 1000;
	
	//trigger 변수의 경우 모든 클래스에서 값이 동일해야 하므로 전역변수(static)로 선언
	static boolean trigger;
	
	
	/* 확률에 따라 출력 메세지 배열, 컨디션을 의미한다
	 * [행위를 구분하는 배열][행위의 컨디션을 구분하는 배열]로 구성된 2차원 배열이다*/
	String [][] msg = { {"　맛이 없는 것 같다...","　평범하게 밥을 먹었다.","　순식간에 다 먹어버렸다!!"},
						{"　집중하지 않았다...", "　그럭저럭 끝났다.", "　엄청난 성장이다!!"},
						{"　별로 재미없어 보인다...","　같이 즐겁게 놀았다.","　미친듯이 뛰어놀았다!!"} };

	Random random = new Random();
	
	//DamaSystem형 변수를 생성하여 DamaSystem객체를 생성
	DamaSystem sys = new DamaSystem();
	
	/*---------------------------- 상태 영역 ----------------------------*/		
	
	/* 엔딩 처리
	 * 프로그램이 종료되는 메소드를 의미
	 * 조건은 다마고치의 건강(= 체력)이 0이 되거나, 날짜가 20일이 넘으면 수행된다 */
	public void ending(){
		//다마고치의 건강이 0이 되었을 때
		if (health<=0){
			for(int i=0; i<5; i++) {
				System.out.println();	}
			System.out.println("　　　"+name+"가 죽었습니다!");
			for(int i=0; i<5; i++) {
				System.out.println();	}
			System.out.print("Enter 키를 누르면 종료됩니다...");
			sys.pause();
			
		//날짜가 20일이 넘었을 때
		}else if (DamaSystem.dayCnt>=21) {
			System.out.println();
			System.out.println("　　> ==*== < 21日째 결과 > ==*== <");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("　　　"+name+"은(는)...?");
			for(int i=0; i<5; i++) {
				sys.sleep(500);	//스레드를 0.5초간 중단
				System.out.println();
			}
			sys.sleep(1000);
			//지능에 따른 엔딩 결과
			if (knowledge>=end_3) {
				System.out.println("　　　< 노벨상을 수상한 학자가 되었습니다! > ");
			}else if (knowledge>=end_2){ 				
				System.out.println("　　　< 박사학위를 취득했습니다! > ");
			}else if (knowledge>=end_1){ 				
				System.out.println("　　　< 평범한 다마고치로 삶을 살았습니다 > ");
			}else{ 				
				System.out.println("　　　< 밥만 축내는 다마고치가 되었습니다... > ");
			}
			for(int i=0; i<5; i++) {
				System.out.println();
			}
			System.out.print("Enter 키를 누르면 종료됩니다.");
			sys.pause();
		}
	}
	
	/* 수치 갱신
	 * 직접적인 행위 시, 다마고치의 여러 상태 변수들을 갱신한다
	 * 또한 trigger가 true일 때 health값을 변화시킨다*/
	public void setData(){
		//날짜가 4일이 지날때 마다 나이가 1씩 증가한다
		if(DamaSystem.dayCnt>=4) {
			age=DamaSystem.dayCnt/4 + 1;
		}
		//overflow 처리 (건강과 스트레스의 최대, 최소값 정의)
		 if (health>100){
			health=100;
		}if (stress<0){
			stress=0;
		}
		
		//여러 상태 변수에 따른 건강 변화
		if(trigger==true) {
			//스트레스가 100이 넘어가면 건강 값이 줄어든다
			if (stress>100){
				health -= (stress-100)*0.45;
			//피로도가 80이 넘어가면 건강 값이 줄어든다
			}if (fatigue>80){
				health -= (fatigue-80)*0.30;
			//포만감이 0미만이 되면 그만큼 건강 값이이 줄어든다. 
			}if (food<0){
				health += food;
				food=0;
			//청결도가 0미만이 되면 그만큼 건강 값이 줄어든다.
			}if (clean<0){
				health += clean;
				clean=0;
			}
		}
	}
	
	/* 상태 알람
	 * 여러 상태 변수들을 기준으로 알림 메세지가 출력된다
	 * health값이 가장 우선시 되어 판별되고, 아래로 내려갈 수록 우선순위가 줄어든다*/
	public void status(){
		if(health<=30){
			System.out.println("상태가 심각하다!");
		}else if(health<=50){
			System.out.println("어딘가 아픈 것 같다");
		}else if(fatigue>=70){
			System.out.println("졸린 것 같다");
		}else if(food<=30){
			System.out.println("배가 고픈 것 같다");
		}else if (clean<=30){
			System.out.println("상당히 더럽다");
		}else if (stress>=80){
			System.out.println("상당히 화가 난 것 같다");
		}else if (stress>=60){
			System.out.println("기분이 안 좋은 것 같다");
		//위 조건들이 전부 부합되지 않으면 건강해보인다는 메세지를 출력
		}else System.out.println("건강해 보인다!");
	}
	/* 0~(n-1)범위의 난수 생성: random.nextInt(n)를 사용했다
	 * 난수 범위를 0~10으로 설정한 후 매개변수를 더하여 값을 출력하도록 설계*/
	public int randStat(int randStat) {
		//현재 클래스에서 사용하는 변수를 명시하기 위해 this. 를 사용하여 매개변수와 구분을 둠
		this.randStat = random.nextInt(11)+(randStat);
		//return을 선언하여 메소드가 this.randStat이라는 값을 가진다
		return this.randStat; 
	}
	/* 랜덤 수치 변화
	 * 재미를 위해, 각 행동에 따른 상태 변수들의 증감 수치를 난수로 처리
	 * 매개변수 stat은 상태 변수를 구별하며, evenrandStat은 난수값의 범위를 입력받는다
	 * 난수는 DamaSystem 클래스의 randStat 메소드를 통해 생성된다*/
	public void ranStatCir(int stat, int evenrandStat) {
		sys.sleep(500);
		//상태를 나타내는 매개변수 stat을 기준으로 switch문 실행
		switch (stat) {
		case 1:
			health += randStat(evenrandStat);
			System.out.println("　건강 +"+randStat);
			break;
		case -1:
			health -= randStat(evenrandStat);
			System.out.println("　건강 -"+randStat);
			break;
		case 2:
			stress += randStat(evenrandStat);
			System.out.println("　스트레스 +"+randStat);
			break;
		case -2:
			stress -= randStat(evenrandStat);
			System.out.println("　스트레스 -"+randStat);
			break;
		case 3:
			fatigue += randStat(evenrandStat);
			System.out.println("　피로 +"+randStat);
			break;
		case -3:
			fatigue -= randStat(evenrandStat);
			System.out.println("　피로 -"+randStat);
			break;
		case 4:
			food += randStat(evenrandStat);
			System.out.println("　포만감 +"+randStat);
			break;
		case -4:
			food -= randStat(evenrandStat);
			System.out.println("　포만감 -"+randStat);
			break;
		case 5:
			clean += randStat(evenrandStat);
			System.out.println("　청결도 +"+randStat);
			break;		
		case -5:
			clean -= randStat(evenrandStat);
			System.out.println("　청결도 -"+randStat);
			break;
		case 11:
			knowledge += randStat(evenrandStat);
			System.out.println("　[지식] +"+randStat);
			System.out.println();
			break;
		}
	}
		
	/* 수치 출력
	 * 다마고치의 상태 변수들을 출력하는 메소드
	 * DamaSystem 클래스의 square 메소드를 통하여 데이터를 직관적으로 판단 가능하다*/
	public void displayStatus(){
		System.out.println("　나이 :  "+age);
		System.out.println();
		System.out.print("　건강"+"("+health+") ");sys.square(health);
		System.out.print("　스트레스"+"("+stress+") ");sys.square(stress);
		System.out.print("　피로"+"("+fatigue+") ");sys.square(fatigue);
		System.out.print("　포만감"+"("+food+") ");sys.square(food);
		System.out.print("　청결도"+"("+clean+") ");sys.square(clean);
		
		System.out.println();
	}
	
	/* 능력치 출력
	 * 각 엔딩에 도달하기까지 필요한 지능 수치가 출력된다*/
	public void displayAbility(){
		trigger=false;
		System.out.println();
		System.out.println("　지식: "+knowledge);
		System.out.println();
		sys.sleep(1000);
		if (knowledge < end_1) {
			System.out.println("　사회로 나가기까지 남은 필요한 지식: "+(end_1 - knowledge));
		}else if (knowledge < end_2){ 				
			System.out.println("　박사학위 취득까지 남은 필요한 지식: "+(end_2 - knowledge));
		}else if (knowledge < end_3){ 				
			System.out.println("　노벨상 수상까지 남은 필요한 지식: "+(end_3 - knowledge));
		}else {
			System.out.println("　"+name+"은 충분히 영리해 보이는 군요...!");
		}
		sys.sleep(2000);
		System.out.println();
	}
	
	/* 나이에 따른 모습
	 * 나이를 기반으로 모양이 변하며, 각 상태에 따른 표정이 변화한다
	 * 상태의 우선순위는 건강, 피로도, 스트레스 순이며, 해당되는 상태가 없으면 방끗 웃는다 ^-^
	 * 청결도가 30씩 떨어질때마다 똥을 싼다*/
	public void character() {
		if(age < 2) {
			if(health<=50) {
				System.out.println();
				System.out.println();
				System.out.println("    ■■■■ ");
				System.out.println("  ■ _   _  ■ ");
				System.out.println(" ■  T ^ T   ■");
				System.out.println(" ■          ■");
				System.out.println("  ■        ■");
				System.out.print("     ■■■■    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println();
				System.out.println();
				System.out.println("    ■■■■ ");
				System.out.println("  ■ _   _  ■ ");
				System.out.println(" ■  . _ .   ■");
				System.out.println(" ■          ■");
				System.out.println("  ■        ■");
				System.out.print("    ■■■■    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println();
				System.out.println();
				System.out.println("    ■■■■ ");
				System.out.println("  ■ ＼　／ ■ ");
				System.out.println(" ■　○  ○  ■");
				System.out.println(" ■　　△    ■");
				System.out.println("  ■        ■");
				System.out.print("    ■■■■    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println();
				System.out.println();
				System.out.println("    ■■■■ ");
				System.out.println("  ■ ^   ^  ■ ");
				System.out.println(" ■  ○  ○  ■");
				System.out.println(" ■　　▽  　■");
				System.out.println("  ■        ■");
				System.out.print("    ■■■■    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}else if(age >= 2 && age < 4) {
			if(health<=50) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■    　  ■");
				System.out.println("  ■ __   __  ■ ");
				System.out.println(" ■  TT   TT   ■");
				System.out.println(" ■     ^      ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■");
				System.out.println("  ■  _   _   ■ ");
				System.out.println(" ■   . _ .    ■");
				System.out.println(" ■            ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■");
				System.out.println("  ■  ＼   ／ ■ ");
				System.out.println(" ■   ○    ○ ■");
				System.out.println(" ■      △    ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■");
				System.out.println("  ■   ^  ^   ■ ");
				System.out.println(" ■   ○  ○   ■");
				System.out.println(" ■     ▽     ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}else {
			if(health<=50) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■    　  ■    ☆");
				System.out.println("  ■ __   __  ■  / ");
				System.out.println(" ■  TT   TT   ■/");
				System.out.println(" ■     ^      ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■    ☆");
				System.out.println("  ■  _   _   ■  /");
				System.out.println(" ■   . _ .    ■/");
				System.out.println(" ■            ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■    ☆");
				System.out.println("  ■  ＼   ／ ■  /");
				System.out.println(" ■   ○    ○ ■/");
				System.out.println(" ■      △    ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println("     ■■■■ ");
				System.out.println("   ■        ■    ☆");
				System.out.println("  ■   ^  ^   ■  /");
				System.out.println(" ■   ○  ○   ■/");
				System.out.println(" ■     ▽     ■");
				System.out.println("  ■          ■");
				System.out.println("    ■■■■■   ");
				System.out.print("   □□    □□ ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}
	}

}
