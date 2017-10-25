import java.util.Random;

public class DamaStatus {
	//�ٸ���ġ ��ġ ����
	int health = 100, stress = 0, fatigue = 0, 
		food = 50, clean = 100, age = 1, knowledge = 0,
		randStat;
	
	String name;
	
	//���� ��ġ�� ���ϴ� �����͸� ��� ����, ���� ��ġ�� ���� ������ ����� �ٲ��
	int end_1 = 300, end_2 = 700, end_3 = 1000;
	
	//trigger ������ ��� ��� Ŭ�������� ���� �����ؾ� �ϹǷ� ��������(static)�� ����
	static boolean trigger;
	
	
	/* Ȯ���� ���� ��� �޼��� �迭, ������� �ǹ��Ѵ�
	 * [������ �����ϴ� �迭][������ ������� �����ϴ� �迭]�� ������ 2���� �迭�̴�*/
	String [][] msg = { {"������ ���� �� ����...","������ϰ� ���� �Ծ���.","�����İ��� �� �Ծ���ȴ�!!"},
						{"���������� �ʾҴ�...", "���׷����� ������.", "����û�� �����̴�!!"},
						{"������ ��̾��� ���δ�...","������ ��̰� ��Ҵ�.","����ģ���� �پ��Ҵ�!!"} };

	Random random = new Random();
	
	//DamaSystem�� ������ �����Ͽ� DamaSystem��ü�� ����
	DamaSystem sys = new DamaSystem();
	
	/*---------------------------- ���� ���� ----------------------------*/		
	
	/* ���� ó��
	 * ���α׷��� ����Ǵ� �޼ҵ带 �ǹ�
	 * ������ �ٸ���ġ�� �ǰ�(= ü��)�� 0�� �ǰų�, ��¥�� 20���� ������ ����ȴ� */
	public void ending(){
		//�ٸ���ġ�� �ǰ��� 0�� �Ǿ��� ��
		if (health<=0){
			for(int i=0; i<5; i++) {
				System.out.println();	}
			System.out.println("������"+name+"�� �׾����ϴ�!");
			for(int i=0; i<5; i++) {
				System.out.println();	}
			System.out.print("Enter Ű�� ������ ����˴ϴ�...");
			sys.pause();
			
		//��¥�� 20���� �Ѿ��� ��
		}else if (DamaSystem.dayCnt>=21) {
			System.out.println();
			System.out.println("����> ==*== < 21��° ��� > ==*== <");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.print("������"+name+"��(��)...?");
			for(int i=0; i<5; i++) {
				sys.sleep(500);	//�����带 0.5�ʰ� �ߴ�
				System.out.println();
			}
			sys.sleep(1000);
			//���ɿ� ���� ���� ���
			if (knowledge>=end_3) {
				System.out.println("������< �뺧���� ������ ���ڰ� �Ǿ����ϴ�! > ");
			}else if (knowledge>=end_2){ 				
				System.out.println("������< �ڻ������� ����߽��ϴ�! > ");
			}else if (knowledge>=end_1){ 				
				System.out.println("������< ����� �ٸ���ġ�� ���� ��ҽ��ϴ� > ");
			}else{ 				
				System.out.println("������< �丸 �೻�� �ٸ���ġ�� �Ǿ����ϴ�... > ");
			}
			for(int i=0; i<5; i++) {
				System.out.println();
			}
			System.out.print("Enter Ű�� ������ ����˴ϴ�.");
			sys.pause();
		}
	}
	
	/* ��ġ ����
	 * �������� ���� ��, �ٸ���ġ�� ���� ���� �������� �����Ѵ�
	 * ���� trigger�� true�� �� health���� ��ȭ��Ų��*/
	public void setData(){
		//��¥�� 4���� ������ ���� ���̰� 1�� �����Ѵ�
		if(DamaSystem.dayCnt>=4) {
			age=DamaSystem.dayCnt/4 + 1;
		}
		//overflow ó�� (�ǰ��� ��Ʈ������ �ִ�, �ּҰ� ����)
		 if (health>100){
			health=100;
		}if (stress<0){
			stress=0;
		}
		
		//���� ���� ������ ���� �ǰ� ��ȭ
		if(trigger==true) {
			//��Ʈ������ 100�� �Ѿ�� �ǰ� ���� �پ���
			if (stress>100){
				health -= (stress-100)*0.45;
			//�Ƿε��� 80�� �Ѿ�� �ǰ� ���� �پ���
			}if (fatigue>80){
				health -= (fatigue-80)*0.30;
			//�������� 0�̸��� �Ǹ� �׸�ŭ �ǰ� ������ �پ���. 
			}if (food<0){
				health += food;
				food=0;
			//û�ᵵ�� 0�̸��� �Ǹ� �׸�ŭ �ǰ� ���� �پ���.
			}if (clean<0){
				health += clean;
				clean=0;
			}
		}
	}
	
	/* ���� �˶�
	 * ���� ���� �������� �������� �˸� �޼����� ��µȴ�
	 * health���� ���� �켱�� �Ǿ� �Ǻ��ǰ�, �Ʒ��� ������ ���� �켱������ �پ���*/
	public void status(){
		if(health<=30){
			System.out.println("���°� �ɰ��ϴ�!");
		}else if(health<=50){
			System.out.println("��� ���� �� ����");
		}else if(fatigue>=70){
			System.out.println("���� �� ����");
		}else if(food<=30){
			System.out.println("�谡 ���� �� ����");
		}else if (clean<=30){
			System.out.println("����� ������");
		}else if (stress>=80){
			System.out.println("����� ȭ�� �� �� ����");
		}else if (stress>=60){
			System.out.println("����� �� ���� �� ����");
		//�� ���ǵ��� ���� ���յ��� ������ �ǰ��غ��δٴ� �޼����� ���
		}else System.out.println("�ǰ��� ���δ�!");
	}
	/* 0~(n-1)������ ���� ����: random.nextInt(n)�� ����ߴ�
	 * ���� ������ 0~10���� ������ �� �Ű������� ���Ͽ� ���� ����ϵ��� ����*/
	public int randStat(int randStat) {
		//���� Ŭ�������� ����ϴ� ������ ����ϱ� ���� this. �� ����Ͽ� �Ű������� ������ ��
		this.randStat = random.nextInt(11)+(randStat);
		//return�� �����Ͽ� �޼ҵ尡 this.randStat�̶�� ���� ������
		return this.randStat; 
	}
	/* ���� ��ġ ��ȭ
	 * ��̸� ����, �� �ൿ�� ���� ���� �������� ���� ��ġ�� ������ ó��
	 * �Ű����� stat�� ���� ������ �����ϸ�, evenrandStat�� �������� ������ �Է¹޴´�
	 * ������ DamaSystem Ŭ������ randStat �޼ҵ带 ���� �����ȴ�*/
	public void ranStatCir(int stat, int evenrandStat) {
		sys.sleep(500);
		//���¸� ��Ÿ���� �Ű����� stat�� �������� switch�� ����
		switch (stat) {
		case 1:
			health += randStat(evenrandStat);
			System.out.println("���ǰ� +"+randStat);
			break;
		case -1:
			health -= randStat(evenrandStat);
			System.out.println("���ǰ� -"+randStat);
			break;
		case 2:
			stress += randStat(evenrandStat);
			System.out.println("����Ʈ���� +"+randStat);
			break;
		case -2:
			stress -= randStat(evenrandStat);
			System.out.println("����Ʈ���� -"+randStat);
			break;
		case 3:
			fatigue += randStat(evenrandStat);
			System.out.println("���Ƿ� +"+randStat);
			break;
		case -3:
			fatigue -= randStat(evenrandStat);
			System.out.println("���Ƿ� -"+randStat);
			break;
		case 4:
			food += randStat(evenrandStat);
			System.out.println("�������� +"+randStat);
			break;
		case -4:
			food -= randStat(evenrandStat);
			System.out.println("�������� -"+randStat);
			break;
		case 5:
			clean += randStat(evenrandStat);
			System.out.println("��û�ᵵ +"+randStat);
			break;		
		case -5:
			clean -= randStat(evenrandStat);
			System.out.println("��û�ᵵ -"+randStat);
			break;
		case 11:
			knowledge += randStat(evenrandStat);
			System.out.println("��[����] +"+randStat);
			System.out.println();
			break;
		}
	}
		
	/* ��ġ ���
	 * �ٸ���ġ�� ���� �������� ����ϴ� �޼ҵ�
	 * DamaSystem Ŭ������ square �޼ҵ带 ���Ͽ� �����͸� ���������� �Ǵ� �����ϴ�*/
	public void displayStatus(){
		System.out.println("������ :  "+age);
		System.out.println();
		System.out.print("���ǰ�"+"("+health+") ");sys.square(health);
		System.out.print("����Ʈ����"+"("+stress+") ");sys.square(stress);
		System.out.print("���Ƿ�"+"("+fatigue+") ");sys.square(fatigue);
		System.out.print("��������"+"("+food+") ");sys.square(food);
		System.out.print("��û�ᵵ"+"("+clean+") ");sys.square(clean);
		
		System.out.println();
	}
	
	/* �ɷ�ġ ���
	 * �� ������ �����ϱ���� �ʿ��� ���� ��ġ�� ��µȴ�*/
	public void displayAbility(){
		trigger=false;
		System.out.println();
		System.out.println("������: "+knowledge);
		System.out.println();
		sys.sleep(1000);
		if (knowledge < end_1) {
			System.out.println("����ȸ�� ��������� ���� �ʿ��� ����: "+(end_1 - knowledge));
		}else if (knowledge < end_2){ 				
			System.out.println("���ڻ����� ������ ���� �ʿ��� ����: "+(end_2 - knowledge));
		}else if (knowledge < end_3){ 				
			System.out.println("���뺧�� ������� ���� �ʿ��� ����: "+(end_3 - knowledge));
		}else {
			System.out.println("��"+name+"�� ����� ������ ���̴� ����...!");
		}
		sys.sleep(2000);
		System.out.println();
	}
	
	/* ���̿� ���� ���
	 * ���̸� ������� ����� ���ϸ�, �� ���¿� ���� ǥ���� ��ȭ�Ѵ�
	 * ������ �켱������ �ǰ�, �Ƿε�, ��Ʈ���� ���̸�, �ش�Ǵ� ���°� ������ ��� ���´� ^-^
	 * û�ᵵ�� 30�� ������������ ���� �Ѵ�*/
	public void character() {
		if(age < 2) {
			if(health<=50) {
				System.out.println();
				System.out.println();
				System.out.println("    ����� ");
				System.out.println("  �� _   _  �� ");
				System.out.println(" ��  T ^ T   ��");
				System.out.println(" ��          ��");
				System.out.println("  ��        ��");
				System.out.print("     �����    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println();
				System.out.println();
				System.out.println("    ����� ");
				System.out.println("  �� _   _  �� ");
				System.out.println(" ��  . _ .   ��");
				System.out.println(" ��          ��");
				System.out.println("  ��        ��");
				System.out.print("    �����    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println();
				System.out.println();
				System.out.println("    ����� ");
				System.out.println("  �� ������ �� ");
				System.out.println(" �ᡡ��  ��  ��");
				System.out.println(" �ᡡ����    ��");
				System.out.println("  ��        ��");
				System.out.print("    �����    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println();
				System.out.println();
				System.out.println("    ����� ");
				System.out.println("  �� ^   ^  �� ");
				System.out.println(" ��  ��  ��  ��");
				System.out.println(" �ᡡ����  ����");
				System.out.println("  ��        ��");
				System.out.print("    �����    ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}else if(age >= 2 && age < 4) {
			if(health<=50) {
				System.out.println("     ����� ");
				System.out.println("   ��    ��  ��");
				System.out.println("  �� __   __  �� ");
				System.out.println(" ��  TT   TT   ��");
				System.out.println(" ��     ^      ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println("     ����� ");
				System.out.println("   ��        ��");
				System.out.println("  ��  _   _   �� ");
				System.out.println(" ��   . _ .    ��");
				System.out.println(" ��            ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println("     ����� ");
				System.out.println("   ��        ��");
				System.out.println("  ��  ��   �� �� ");
				System.out.println(" ��   ��    �� ��");
				System.out.println(" ��      ��    ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println("     ����� ");
				System.out.println("   ��        ��");
				System.out.println("  ��   ^  ^   �� ");
				System.out.println(" ��   ��  ��   ��");
				System.out.println(" ��     ��     ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}else {
			if(health<=50) {
				System.out.println("     ����� ");
				System.out.println("   ��    ��  ��    ��");
				System.out.println("  �� __   __  ��  / ");
				System.out.println(" ��  TT   TT   ��/");
				System.out.println(" ��     ^      ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(fatigue>=70) {
				System.out.println("     ����� ");
				System.out.println("   ��        ��    ��");
				System.out.println("  ��  _   _   ��  /");
				System.out.println(" ��   . _ .    ��/");
				System.out.println(" ��            ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else if(stress>=60||food<30) {
				System.out.println("     ����� ");
				System.out.println("   ��        ��    ��");
				System.out.println("  ��  ��   �� ��  /");
				System.out.println(" ��   ��    �� ��/");
				System.out.println(" ��      ��    ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}else {
				System.out.println("     ����� ");
				System.out.println("   ��        ��    ��");
				System.out.println("  ��   ^  ^   ��  /");
				System.out.println(" ��   ��  ��   ��/");
				System.out.println(" ��     ��     ��");
				System.out.println("  ��          ��");
				System.out.println("    ������   ");
				System.out.print("   ���    ��� ");
				for(int i=0;i<(100-clean)/30;i++) {
					System.out.print("*");
				}System.out.println();
			}
		}
	}

}
