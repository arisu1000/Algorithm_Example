
public class InsertionSort {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int nData = 100000;	//정렬할 데이터 개수 설정.
		double TargetDataList[] = new double[nData]; 
		
		//데이터 생성
		for(int i = 0; i < nData; i++)
		{
			TargetDataList[i] = Math.random();
			
			System.out.print(TargetDataList[i]+" ");	//데이터 출력
		}
		System.out.println();
		
		StopWatch.start();

		//정렬 수행
		for(int i = 0; i < nData; i++){
			for(int j =0; j < i; j++){

				if(TargetDataList[i] < TargetDataList[j]){
					double tmp = TargetDataList[i];
					
					//삽입하고 남은배열들을 뒤로 밀어 넣음.
					for(int moveidx = i; moveidx > j; moveidx--){ 
						TargetDataList[moveidx] = TargetDataList[moveidx - 1];
					}
					
					
					TargetDataList[j] = tmp;
					break;
				}
			}
		}
		StopWatch.stop();
		System.out.println("Insertion Sort Time : " + StopWatch.TotalTimeInSec());
		
//		//정렬된 데이터 확인.
//		for(int i =0; i < nData; i++){
//			System.out.print(TargetDataList[i] + " ");
//		}
//		System.out.println();
		
	}
}
