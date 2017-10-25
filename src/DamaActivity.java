
public class DamaActivity {
	
	DamaSystem sys = new DamaSystem();
	DamaStatus stat = new DamaStatus();
	DamaExam exam = new DamaExam();
	
	/*---------------------------- ���� ���� ----------------------------*/		
	
	/* �۸�
	 * �ٸ���ġ�� �̸��� ����ڷκ��� �Է¹޴� �޼ҵ�*/
	public void naming(){
		while(true){
			System.out.println("����������������������������������������������������������������");
			System.out.println("���̸��� �Է��ϼ���. (���� ���� �ִ� 6����)");
			System.out.println("����������������������������������������������������������������");
			System.out.println();
			System.out.print("���Է�: ");
			/* ����� �Է� ���� String�� name������ ���� 
			 * ������ ������ ������ �Է¹ޱ����� nextLine�� ���*/
			stat.name = sys.scan.nextLine();
			//name������ ���� ���� int�� num������ ����
			sys.num = stat.name.length();
			//���� �̸��� ���̰� 6���ڸ� �Ѿ�� �ٽ� ����ڷκ��� �Է°��� �޴´�
			if(sys.num>6){
				System.out.println();
				System.out.println("��6�� �̳��� �̸��� �Է��ϼ���.");
				sys.sleep(500);
				//while �ݺ����� ó������ ��ȯ
				continue;			
			}else {
				sys.sleep(500);
				System.out.print("��["+stat.name+"](��)�� �����մϴ�.");
				for(int i=0; i<4; i++) {
					sys.sleep(500);
					System.out.print(".");
					}
				sys.sleep(800);
				System.out.println("!");
				sys.sleep(500);
				System.out.println();
				//while �ݺ��� Ż��
				break;				
			}
		}
	}
	
	/* �⺻ ����
	 * ���α׷��� ���̾ƿ��� ����ϴ� �޼ҵ�
	 * ���� �������� ������ ��Ÿ����
	 * ��üȭ �� �����͵��� ������*/
	public void rayout(){
		while(true){
			System.out.println("����������������������������������������������������������������");
			//DamaSystem Ŭ������ ��¥, �ð��� �ҷ��� ���
			System.out.println("��"+DamaSystem.dayCnt+"�� / ����ð� "+sys.time+" : "+"00");
			System.out.println();
			//DamaStatus Ŭ������ ĳ���� ����ȭ �޼ҵ�
			stat.character();
			System.out.print("��"+stat.name+"�� ����: ");
			//DamaStatus Ŭ������ ���� �˶� �޼ҵ� 
			stat.status(); 	
			System.out.println("����������������������������������������������������������������");
			//DamaStatus Ŭ������ ���� ��� �޼ҵ� 
			stat.displayStatus(); 		
			sys.sleep(500);
			System.out.println("����������������������������������������������������������������");
			//DamaStatus Ŭ������ ���� ó�� �޼ҵ� 
			stat.ending(); 				
			System.out.println("��1. ����̱� (2h)");
			System.out.println("��2. ����ġ�� (6h)");	
			System.out.println("��3. ����ֱ� (2h)");
			System.out.println("��4. �ı�� (1h)");				
			System.out.println("��5. ���ڱ� (12h)");
			System.out.println("��99. �ɷ�ġ Ȯ��");
			System.out.println("����������������������������������������������������������������");
			sys.sleep(500);
			//����ڷκ��� ���ڸ� �Է¹ް�, �׿� ���� ���� �޼ҵ带 �����ϴ� ����
			while(true) {
				System.out.print("������: ");
				//DamaSystem Ŭ������ ��ȣ �Է� �޼ҵ�
				sys.inputNum();	
				//DamaSystem Ŭ������ ���� num�� �������� �ϴ� switch��
				switch(sys.num){
				case 1:	eat();		break;
				case 2:	study();	break;
				case 3:	play();		break;
				case 4:	wash();		break;
				case 5:	sleep(); 	break;
				case 99: stat.displayAbility();	break;
				//����ڷκ��� �Է¹��� ���ڰ� case�� �ش��ϴ� ���ڰ� �ƴ� ��� ����Ǵ� ����
				default:
					System.out.println("���ٽ� �Է��ϼ���!");
					System.out.println("����������������������������������������������������������������");
					//�������� �ൿ�� �ƴϱ� ������ DamaStatus Ŭ������ setData �޼ҵ尡 ����Ǿ �ȵ�
					DamaStatus.trigger=false;
					//while �ݺ����� ó������ ��ȯ
					continue;
				}
				//while �ݺ����� Ż��
				break;
			}
			sys.sleep(500);
			//DamaStatus Ŭ������ ������ ���� �޼ҵ� 
			stat.setData(); 				
			System.out.println("����������������������������������������������������������������");
		}
	}
/* �⺻ ���� ���� �޼ҵ� */	
	/* �Ա�
	 * �������� �����ϰ� �ö��� �ʵ��� �ѵ����� ����
	 * ���� ���� �� ���� �ǰ��� 10�� �����ϰ�, �ǰ��� 100�� ���� �ʵ��� ����*/
	public void eat(){
		//�������� 90 �����϶� �Ʒ� �������� �����Ѵ�
		if(stat.food < 90){
			//DamaSystem Ŭ������ Ȯ�� ��� ���� �޼ҵ�
			sys.probNum();
			/* DamaStatus Ŭ������ ����� �迭 ���, 0�� �ǰ��� ���õ� ���ڿ� �迭�̴�
			 * �ռ� ������ sys.probNum�� �������� ����� ���ڿ��� ����Ѵ�*/
			System.out.println(stat.msg[0][sys.probNum]);
			System.out.println();
			sys.sleep(500);
			//���� �ǰ��� 90���ϸ� �ǰ��� 10 �ö󰣴�
			if(stat.health<=90) {
				stat.health += 10;
				System.out.println("���ǰ� +10");
			//���� �ǰ��� 90�ʰ� 100���ϸ� 100�� �°� �ǰ��� �ö󰣴�
			}else if(stat.health>90 && stat.health<=100) {
				System.out.println("���ǰ� +"+(100-stat.health));
				stat.health=100;
			}
			//DamaStat Ŭ������ ���� ���� ����(�׸�, ���� ����) 
			stat.ranStatCir(3, 20);
			//�ռ� ������ sys.probNum�� �������� ����ǿ� ���� ���� ��ġ ������ ����
			switch(sys.probNum){
			case 0: stat.ranStatCir(4, 20); break;
			case 1: stat.ranStatCir(4, 30); break;
			case 2: stat.ranStatCir(4, 60);	}
			stat.ranStatCir(-5, 20);
			//DamaSystem Ŭ������ �ð� ���� �޼ҵ�
			sys.timeCheck(2);
			//�������� �����̱⿡ setData()�޼ҵ带 �����ϴ� trigger ���¸� true�� ����
			DamaStatus.trigger=true;
		//�������� 90���� ũ�� �Ա� ������ ������� �ʴ´�
		}else{
			System.out.println("�����̻� ���� �� ����!");
			DamaStatus.trigger=false;
		}
	}
	/* ����
	 * ���̰� 2�캸�� ������ �ܼ��� ���� ��ġ�� ����
	 * ���� ���̰� 2���� �Ѿ�� �ٸ���ġ�� ������ ����ڰ� �ذ��ؾ��Ѵ�
	 * DamaExam Ŭ������ ��üȭ ���� ������ �޴� �޼ҵ带 �����Ѵ� */
	public void study() {
		//���� ���̰� 2�캸�� ������ �Ʒ� ���� ����
		if(stat.age < 2) {
			//������ �Ա� �޼ҵ�� ����
			sys.probNum();
			System.out.println(stat.msg[1][sys.probNum]);
			System.out.println();
			switch(sys.probNum) {
			case 0: stat.ranStatCir(11, 20); break;
			case 1: stat.ranStatCir(11, 30); break;
			case 2: stat.ranStatCir(11, 60);	}
		//���� ���̰� 2���̻�, 4�� �̸��̸� �Ʒ� ���� ����	
		}else if (stat.age < 4) {
			/* DamaExam Ŭ������ ���� ���� �޼ҵ� ( ���� ����, ���� ���� )
			 * ���� 1�� ��� �⺻���� ���ϱ�, �E��, ������ ����Ѵ�*/
			exam.examDisplay(100, 1);
			//���� ������ ���� ��, �Ʒ� ���� ����
			if(exam.correct==true) {
				stat.ranStatCir(11, 30);
			}
		//���� ���̰� 4�� �̻��̸� �Ʒ� ���� ����
		}else {
			//���� 2�� ��� 10������ 2����, 8����, 16������ ��ȯ�ϴ� ������ ����Ѵ�
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
	//���� �ı� �޼ҵ�� �Ա� �޼ҵ�� ���� �����ϹǷ� �ּ��� �����Ѵ�
	//���
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
	//�ı�
	public void wash(){
		System.out.println("��<����� �˵� ġ��ϴ�...>");
		System.out.println();
		sys.sleep(500);
		System.out.println("��û�ᵵ +"+(100-stat.clean));
		stat.clean=100;
		stat.ranStatCir(-2, 10);
		stat.ranStatCir(3, 20);
		stat.ranStatCir(-4, 10);
		DamaStatus.trigger=true;
		sys.timeCheck(1);
	}
	/* ���ڱ�
	 * �Ƿε��� 70���� ���������� ����*/
	public void sleep(){
		System.out.println("��< zzzZZZ >");
		System.out.println();
		//���� �Ƿε��� 70�̸��̸� �Ʒ� ���� ����
		if(stat.fatigue<70){
			System.out.println("������ �ǰ����� ���� �� ����...");
			DamaStatus.trigger=false;
		//���� �Ƿε��� 70�̻��̸� �Ʒ� ���� ����
		}else{
			sys.sleep(500);
			//�Ƿε��� 0���� �ʱ�ȭ
			System.out.println("���Ƿε� -"+stat.fatigue);
			stat.fatigue=0;
			stat.ranStatCir(-2, 20);
			stat.ranStatCir(-4, 30);
			sys.timeCheck(12);
			DamaStatus.trigger=true;
		}
	}
	
}