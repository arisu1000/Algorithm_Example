
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int nData = 10;	//정렬할 데이터 개수 설정.
		double TargetDataList[] = new double[nData]; 
		
		//데이터 생성
		for(int i = 0; i < nData; i++)
		{
			TargetDataList[i] = Math.random();
			
			System.out.print(TargetDataList[i]+" ");	//데이터 출력
		}
		System.out.println();
		
		//정렬 수행
		for(int i = 0; i < nData; i++){
			for(int j =0; j < nData; j++){
				if(TargetDataList[i] < TargetDataList[j]){
					double tmp = TargetDataList[i];
					TargetDataList[i] = TargetDataList[j];
					TargetDataList[j] = tmp;
				}
			}
		}
		
		//정렬된 데이터 확인.
		for(int i =0; i < nData; i++){
			System.out.print(TargetDataList[i] + " ");
		}
		System.out.println();
		
	}

}
