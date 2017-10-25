import java.util.*;

public class DamaExam {

	int n1, n2;
	int randNum, numArith, numConv;
	int cnt=3; //���� ��, �ִ� ����� Ƚ�� (�ʱ� 1ȸ ����)
	String answer, userAnswer;
	
	//����ڰ� ������ ���߰ų� Ʋ���ų��� �Ǻ��ϴ� ����
	boolean correct;
	/* ���� �迭
	 * [���� ������ �����ϴ� �迭][������ �����ϴ� �迭]�� ������ 2���� �迭�̴�*/
	String[][] exam ={ {"�� ���� ���� ���� ���ΰ���?", "ù ���� ���� ���� �A ���� ���ΰ���?", "�� ���� ���� ���� ���ΰ���?" }
					  ,{"�� �� 2������ �ٲ� ���� ���ΰ���?","�� �� 8������ �ٲ� ���� ���ΰ���?","�� �� 16������ �ٲ� ���� ���ΰ���?\n(a~f�� �ҹ��ڷ� �Է��ϼ���!)"} 
						};
	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	
	
	/*---------------------------- ���� ���� ----------------------------*/
	
	
	//�Էµ� ���� ������ ���� ����
	public int randNum(int randNum) {
		randNum = random.nextInt(randNum);
		return randNum;
	}
	//������ ���� ���� ������ ��µǴ� ������ ������ ����  
	public void makeNum(int randNumArea) {
		n1 = randNum(randNumArea);
		n2 = randNum(randNumArea);
	}
	
	//���� ���� (��Ģ����)
	public int arithmetic () {
		//��Ģ���� ������ ������ ������ ����ϱ� ���� ���� ����
		numArith = randNum(3);
		switch(numArith) {
		/* ������ ������ �´� ���� ����
		 * String�� ������ ���� �־�� �ϹǷ� int�� Ÿ���� �����͸� ���ڿ��� ��ȯ*/
		case 0: answer = Integer.toString(n1 + n2); break;
		case 1: answer = Integer.toString(n1 - n2); break;
		case 2: answer = Integer.toString(n1 * n2); break;
		}
		//������ ���� ��ȣ�� ���
		return numArith;
	}
	
	/* ���� ���� (10������ ��ȯ)
	 * �⺻���� ������ ���� ��Ģ����� �����ϴ�*/
	public int convFromDec () {
		numConv = randNum(3);
		switch(numConv) {
		//10���� -> 2���� ��ȯ ���� String���� ��ȯ
		case 0: answer = Integer.toBinaryString(n1); break;
		//10���� -> 8���� ��ȯ ���� String���� ��ȯ
		case 1: answer = Integer.toOctalString(n1); break;
		//10���� -> 16���� ��ȯ ���� String���� ��ȯ
		case 2: answer = Integer.toHexString(n1); break;
		}
		return numConv;
	}
	/* ���� ��� ( ������ ���� ����, ���� ���� )
	 * ���� ������ �޾Ƽ� �׿� ���� ������ �����ϴ� ���� ������ ����
	 * ���� ������ �޾Ƽ� �׿� ���� ������ ����Ѵ�*/
	public void examDisplay(int numArea, int examNum) {
		//0 ~ (numArea-1)������ ���� ����
		makeNum(numArea);
		//���� �Է¹��� ������ ��Ģ������ ��� �Ʒ� ���� ����
		if (examNum == 1) {	
			//��Ģ���� ���� ����
			arithmetic();
			System.out.println();
			System.out.print("��[ "+n1+" ]"+"[ "+n2+" ] ");
			//��Ģ���� ���� ���
			System.out.println(exam[0][numArith]);
		}
		//���� �Է¹��� ������ ������ȯ�� ��� �Ʒ� ���� ����
		if (examNum == 2) {
			//������ȯ ���� ����
			convFromDec();
			System.out.println();
			System.out.print("��[ "+n1+" ] ");
			System.out.println(exam[1][numConv]);
		}
		//����ڷκ��� ������ �Է¹��� �� ���� �ִ� 3���� �Է��� �޴´�
		for(int i=1; i<=cnt; i++) {
			System.out.print("�����: ");
			//����ڷ� ���� �Է°��� ����
			userAnswer = scan.next();
			/* ���� �Է°��� ������ ������ �´� ���� ���
			 * String�� �Է°��� String�� ������ ���ϱ����� .equals()�� ���
			 * �ڹٿ��� String�� ��ü�̱� ������, ���迬���� ==�δ� ���� ���� �� ����*/
			if(userAnswer.equals(answer)) {
				//�����̱� ������ correct�� ���¸� true�� ����
				correct = true;
				System.out.println();
				System.out.println("�������Դϴ�!");
				System.out.println();
				break;
				//���� �Է°��� ���� �ƴ� ���
			}else {
				//Ʋ�ȱ� ������ correct�� ���¸� false�� ����
				correct = false;
				System.out.println();
				System.out.println("�������Դϴ�! "+"("+i+"/3)");
				System.out.println();
				continue;
			}
		}
	}

}