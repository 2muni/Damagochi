/* �ڹ� �ܺ� ��Ű�� ������ �ҷ� ����ϱ����� import ���
 * java.��Ű�� ��.Ŭ���� �� �������� �ҷ��´�*/
import java.io.*;		//����°� �ű⿡ ������� ����ó���� ���� import
import java.util.*;		//Scanner �� Random, ����ó���� �ϱ����� import

public class DamaSystem {
	//�ý��� ���� ����
	int squ, num, probNum;
	int time = 9;
	//dayCnt ������ ���, ��� Ŭ�������� ���� �����ؾ� �ϹǷ� ��������(static)�� ����
	static int dayCnt = 1;
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	
	/*---------------------------- �ý��� ���� ----------------------------*/		
	
	/* Ȯ���� ���� ��� ���� ����ϴ� �޼ҵ�
	 * case�� 10���� ����ġ���� �ۼ��Ͽ� Ȯ���� ����ϰ� ����
	 * 0~9�� �������� ������ �����ϰ�, �����Ǵ� ������ ���� ������ ���� ������� ���
	 * ������ ������� �����Ǵ� Ȯ��: �ּ��� ���(30%), ������ ���(60%), �־��� ���(10%) */
	public int probNum() {
		num = random.nextInt(10);	// 0~9���� ���� ���� ���� ����
		//������ ������ ���������� switch�� �ۼ�
		switch(num) {
		//case�� ���, break�� �������� ������ ����ؼ� �Ʒ� case���� ����
		//���� num ���� 9�� ���, case 7���� ������ probNum = 2 �� ����
		case 9:
		case 8:
		case 7: probNum = 2; break;	//�ּ��� ���
		case 6: 
		case 5: 
		case 4:
		case 3:
		case 2:
		case 1: probNum = 1; break;	//������ ���
		default: probNum = 0;		//�־��� ���
		}
		//return�� �����Ͽ� �޼ҵ尡 probNum�̶�� ���� ������
		return probNum;
	}
	
	/* ���ڸ� �Է¹��� �� �߻��ϴ� ����(����)�� ó���ϱ� ���� �޼ҵ�
	 * ���� ���ڸ� �Է¹޾ƾ� �� ����, ���ڸ� �Է¹޾��� ���� ó���� �� �ִ�*/
	public void inputNum() {
		//�ùٸ� ���� �Է¹��� ������ ����Ǿ� �ϹǷ� while(true)�� ����Ͽ���
		while(true) {
			/*����(����)�� ó���ϱ����� try...catch�� ����ߴ�.
			 * try{ ���๮�� } catch( ���� ���ܺ�����  ) { ���� �߻� �� ���� ���� }*/
			try {
				num = scan.nextInt();	//int�� num������ ������� �Է��� ����
				break;
			//�Է¹��� �������� �ڷ����� ������ �ٸ� ��� InputMismatchException ������ �߻��Ѵ�.
			}catch( InputMismatchException ime) {
				//Scanner ��ü�� ���� �����Ͽ� ������ �ذ��Ѵ�.
				scan = new Scanner(System.in);
				System.out.println("�����ڸ� �Է��ϼ���!");
				System.out.println("����������������������������������������������������������������");	
				System.out.print("������: ");
			}
		}
	}
	/* �����带 �����ð� ������Ű�� �޼ҵ�
	 * ���α׷��� �帧�� �� �� �����ϰ� �����ֱ� ���� ���
	 * ���� �����带 ��ӹ��� �ʾ����Ƿ�, ���� ���α׷��� ���� ������� ���ȴ�*/
	public void sleep (int sleepMs) {
		//������� �����带 ms������ �Ͻ����� (1000ms = s)
		try { 
			Thread.sleep(sleepMs);
		//�����尡 �ߴܵǾ��� �� �߻��ϴ� InterruptedException ����ó��
		}catch(InterruptedException ie) {
			/* �ܼ��� �����带 �����ð����� ���� �ϴ� �� ���̹Ƿ� 
			 * ���ܰ� �߻����� �� ���� ����Ǵ� ������ ����*/
		}
	}
	/* enter�Է� �� ���α׷��� ����Ǵ� �޼ҵ�
	 * ���Ǹ� ���� System.in.read()�� ����Ͽ� ������� �Է��� �޾Ҵ� */
	public void pause() {
		try {
			/* Enter�� �������� ������� �Է°��� �ƽ�Ű �ڵ�� �޴´�
			 * ��, EnterŰ ���� �ƽ�Ű�ڵ带 �����Ƿ� ���ӵ� ��뿣 ���ǰ� ����*/
      		System.in.read();
      		/* �⺻ ������� ó���� ����, �ݵ�� ����ó���� �ʿ��ϴ� 
      		 * ����� ���� ���� IOException ����ó��*/
    	}catch (IOException e) { 
    		/* �ܼ��� ������� �޴� �� ���̹Ƿ� 
			 * ���ܰ� �߻����� �� ���� ����Ǵ� ������ ����*/
    		}
		/* �������� JVM�� �����ϴ� �޼���
		 * int�� �Ű������� ���� �ڵ带 �ǹ��ϸ�, �������� �� ���������� 0�� �ۼ��Ѵ�*/
		System.exit(0);
	}
	
	//������ �ð�ȭ
	public void square(int data){
		//�� ������ ��ġ�� �Ű����� data�� �������� 10 ������ �� ���
		squ = data / 10;
		for(int i = 0; i < squ; i++){
			System.out.print("��");
		}
		System.out.println();
	}
	//�ð� ����
	public void timeCheck(int hour){
		//�� �׸�� �ҿ�Ǵ� �ð��� �Ű����� hour�� �ð� time�� ��������
		time += hour;
		//���� �ð��� 24�ø� �Ѿ�� ��¥�� ��������
		if(time>24){
			time = time-24;
			dayCnt++;
		}
	}
	
}
