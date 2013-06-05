import java.util.ArrayList;
import java.util.List;


public class QuickSort {

	private static double TargetDataList[] = null;
	private static boolean pivotList[] = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nData = 10000000; // 정렬할 데이터 개수 설정.
		TargetDataList = new double[nData];
		double DataForRecursive[] = new double[nData];
		
		// 데이터 생성
		for (int i = 0; i < nData; i++) {
			TargetDataList[i] = Math.random();
			DataForRecursive[i] = TargetDataList[i];
//			System.out.print(TargetDataList[i] + " "); // 데이터 출력
		}
//		System.out.println();
		
		
		pivotList = new boolean[nData];
		
		StopWatch.start();
		for(int i = 0; i < TargetDataList.length; i++){
			if(executeNonRecursiveQuickSort()){
				break;	//정렬이 완료되면 중간에라도 루프를 빠져나옴.
			}
		}
		StopWatch.stop();
		System.out.println("Non-Recursive Quick Sort Time : " + StopWatch.TotalTimeInSec());
		
		
		StopWatch.start();
		recursiveQuickSort(DataForRecursive, 0, DataForRecursive.length - 1 );
		StopWatch.stop();
		System.out.println("Recursive Quick Sort Time : " + StopWatch.TotalTimeInSec());
		
//		// 정렬된 데이터 출력
//		for (int i = 0; i < nData; i++) {
//			System.out.print(TargetDataList[i] + " "); // 데이터 출력
//		}
//		System.out.println();		
//		for (int i = 0; i < nData; i++) {
//			System.out.print(DataForRecursive[i] + " "); // 데이터 출력
//		}
//		System.out.println();
	
	}

	
	private static boolean executeNonRecursiveQuickSort() {
		
		boolean isSorted = false;	//모든 원소가 정렬되었으면 true, 아니면 false
		
		List<Integer> sortedPivotIdx = new ArrayList();
		
		//정렬된 pivot 위치를 확인
		for(int i = 0; i < pivotList.length; i++){
			if(pivotList[i] == true){
				sortedPivotIdx.add(i);
			}
		}
		
		if(sortedPivotIdx.size() == TargetDataList.length){
			return isSorted = true; 
		}
		
		if(sortedPivotIdx.size() == 0){
			intervalSort(0, TargetDataList.length - 1);
		} else if (sortedPivotIdx.size() > 0) {

			int startPosition = 0;
			int endPosition = 0;

			// 첫번째 원소부터 첫번째 pivot까지 처리
			startPosition = 0;
			endPosition = sortedPivotIdx.get(0) - 1;
			
			if(sortedPivotIdx.get(0) == 0 && sortedPivotIdx.size() > 1){	//정렬된 Pivot값이 0번 인덱스일때는 1번 인덱스부터 처리
				startPosition = 1;
				endPosition = sortedPivotIdx.get(1) - 1;
			}
			intervalSort(startPosition, endPosition);

			
			// 정렬된 pivot 사이에 추가 정렬할 원소가 있는지 확인.
			for (int i = 1; i < sortedPivotIdx.size() - 1; i++) {
				if ((sortedPivotIdx.get(i + 1) - sortedPivotIdx.get(i)) > 0) {

					/* 정렬할 원소가 있는 구간은 정렬수행 */
					startPosition = sortedPivotIdx.get(i) + 1;
					endPosition = sortedPivotIdx.get(i + 1) - 1;

					intervalSort(startPosition, endPosition);

				}
			}

			// 마지막 pivot부터 행렬끝까지 부분에 대한 처리
			startPosition = sortedPivotIdx.get(sortedPivotIdx.size() - 1) + 1;
			if(startPosition >= TargetDataList.length){startPosition = TargetDataList.length - 1;}
			
			endPosition = TargetDataList.length - 1;
			intervalSort(startPosition, endPosition);
		}
		
		return isSorted;
	}

	//개별 구간에서 pivot값에 대한 좌우정렬 실행.
	static void intervalSort(int startPosition, int endPosition){
		double pivotValue = TargetDataList[startPosition];
		int pivotIdx = startPosition;
		
		List<Double> lowerList = new ArrayList();
		List<Double> upperList = new ArrayList();
		List<Double> equalList = new ArrayList();
		
		//pivot 값을 기준대비 크고 작은 값을 분류함.
		for(int dataIdx = startPosition; dataIdx <= endPosition; dataIdx++){
			if(TargetDataList[dataIdx] > pivotValue){
				upperList.add(TargetDataList[dataIdx]);
			}else if(TargetDataList[dataIdx] < pivotValue){
				lowerList.add(TargetDataList[dataIdx]);
			}else{
				equalList.add(TargetDataList[dataIdx]);
			}
		}
		
		//pivot 값 대비 분류된 값을 작은 값은 왼쪽, 큰값은 오른쪽에 배치함.
		for(int dataIdx = 0; dataIdx < lowerList.size(); dataIdx++){
			TargetDataList[startPosition + dataIdx] = lowerList.get(dataIdx); 
		}
		for(int dataIdx = 0; dataIdx < upperList.size(); dataIdx++){
			TargetDataList[endPosition - dataIdx] = upperList.get(dataIdx); 
		}
		
		//pivot값과 같은 값은 pivot값으로 인식해서 값을 배치한 다음 정렬된 pivot 인덱스에 저장함.
		for(int dataIdx = 0; dataIdx < equalList.size(); dataIdx++){
			TargetDataList[startPosition + lowerList.size() + dataIdx] = pivotValue;
			pivotList[startPosition + lowerList.size() + dataIdx] = true;
		}
		
	}
	
	
	
	public static void recursiveQuickSort(double[] a, int p, int r)
    {
        if(p<r)
        {
            int q=recursivePartition(a,p,r);
            recursiveQuickSort(a,p,q);
            recursiveQuickSort(a,q+1,r);
        }
    }

    private static int recursivePartition(double[] a, int p, int r) {

        double x = a[p];
        int i = p-1 ;
        int j = r+1 ;

        while (true) {
            i++;
            while ( i< r && a[i] < x)
                i++;
            j--;
            while (j>p && a[j] > x)
                j--;

            if (i < j)
                swap(a, i, j);
            else
                return j;
        }
    }

    private static void swap(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}

