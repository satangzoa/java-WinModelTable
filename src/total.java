import java.util.Scanner;

public class total {
public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	
	int kor=0, eng=0, math=0;

	int sum=0;

	double avg=0;
	
	System.out.println("세 과목 점수를 입력하시오: ");
	kor = sc.nextInt();
	eng = sc.nextInt();
	math = sc.nextInt();
	
	sum = kor + eng + math;

	avg = (double)sum / 3;
	
	System.out.println("세 과목의 총합: "+ sum);
	System.out.println("세 과목 평균:" + avg);
}
}
