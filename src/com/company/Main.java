package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int arr[] = {7,5,2,1,3};

        System.out.println("Array before sorting...");
        printArr(arr);
//
//        bubbleSort(arr);
//        System.out.println("Array after bubble sort:");
//        printArr(arr);
//
//        selectionSort(arr);
//        System.out.println("Array after selection sort:");
//        printArr(arr);

//        insertionSort(arr);
//        System.out.println("Array after insertion sort:");
//        printArr(arr);

//        shellSort(arr);
//        System.out.println("Array after shell sort:");
//        printArr(arr);

//        mergeSort(arr);
//        System.out.println("Array after merge sort:");
//        printArr(arr);

//        quickSort(arr,0,4);
//        System.out.println("Array after quick sort:");
//        printArr(arr);

        radixSort(arr);
        System.out.println("Array after radix sort:");
        printArr(arr);

    }

    public static void printArr(int arr[])
    {
        for(int ele: arr)
        {
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    public static void bubbleSort(int arr[])
    {
        for(int i =0; i< arr.length-1; i++)
            for(int j =0; j< arr.length-i-1; j++)
            {
                if(arr[j+1]<arr[j])
                {
                    int tmp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
    }

    public static void selectionSort(int arr[])
    {
        for(int i =0; i< arr.length-1; i++)
        {
            int ind = i;
            int ele = arr[i];

            for(int j =i; j< arr.length; j++)
            {
                if(arr[j]<ele)
                {
                    ind = j;
                    ele = arr[j];
                }
            }
            int tmp = arr[ind];
            arr[ind] = arr[i];
            arr[i] = tmp;
        }
    }

    public static void insertionSort(int arr[])
    {
        for(int i=1; i< arr.length; i++)
        {
            int val = arr[i];
            int ind = i;

            while(ind >0 && arr[ind-1]>val)
            {
                arr[ind] = arr[ind-1];
                ind--;
            }
            arr[ind] = val;
        }
    }

    public static void shellSort(int arr[])
    {
        for(int gap = arr.length/2; gap>0; gap = gap/2)
            for(int i = gap; i< arr.length; i++)
            {
                int val = arr[i];
                int ind = i;

                while(ind >=gap && arr[ind -gap]>val)
                {
                    arr[ind] = arr[ind-gap];
                    ind = ind - gap;
                }

                arr[ind] = val;
            }
    }

    public static void mergeSort(int arr[])
    {
        int n = arr.length;
        if(n<2)
            return;
        int mid = n/2;
        int left[] = new int[mid];
        int right[] = new int[n-mid];

        for(int i =0; i<mid; i++)
            left[i]=arr[i];
        for(int i =mid; i< n; i++)
            right[i-mid] = arr[i];

        mergeSort(left);
        mergeSort(right);
        merge(arr,left,right);
    }

    public static void merge(int arr[], int left[], int right[])
    {
        int i,j,k;
        i=j=k=0;
        int nl = left.length;
        int rl = right.length;

        while(i<nl && j < rl)
        {
            if(left[i]<right[j])
                arr[k++] = left[i++];
            else
                arr[k++]= right[j++];
        }
        while(i<nl)
            arr[k++] = left[i++];
        while (j<rl)
            arr[k++] = right[j++];
    }

    public static void quickSort(int arr[], int start, int end)
    {
        if(start<end)
        {
            int pIndex = partition(arr,start,end);
            partition(arr,start,pIndex-1);
            partition(arr,pIndex+1,end);
        }
    }

    public static int partition(int arr[], int start, int end)
    {
        int pivot = arr[end];
        int pIndex = start;

        for (int i= start; i< end; i++)
        {
            if(arr[i]<pivot)
            {
                int tmp = arr[i];
                arr[i] = arr[pIndex];
                arr[pIndex]= tmp;
                pIndex++;
            }
        }
        int tmp = arr[pIndex];
        arr[pIndex]= arr[end];
        arr[end]=tmp;

        return pIndex;
    }

    public static void radixSort(int arr[])
    {
        int n = arr.length;
        int m = Arrays.stream(arr).max().getAsInt();

        for(int exp =1; m/exp >0; exp = exp*10)
            countSort(arr,n,exp);
    }
    public static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n];
        int count[] = new int[10];

        for(int i =0; i<n; i++)
            count[(arr[i]/exp)%10]++;

        for(int i =1; i< 10; i++)
            count[i]+=count[i-1];

        for(int i = n-1; i>=0; i--)
        {
            output[count[(arr[i]/exp)%10]-1] = arr[i];
            count[(arr[i]/exp)%10]--;
        }

        for(int i =0; i< n; i++)
            arr[i]= output[i];
    }
}
