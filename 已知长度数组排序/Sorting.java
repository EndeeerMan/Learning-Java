class Sorting {
    private int swapper;
    public int[] sort(int arr[],int size){
        for(int i=0;i<=size-2;i++){
            if(arr[i]>arr[i+1]){
                swapper = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = swapper;
                swapper = 0;
            }
        }
        return arr;
    }

}
