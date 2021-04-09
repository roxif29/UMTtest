package ro.ubbcluj.cs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Utils {
    public boolean splitArraySameAverage(int[] initialList) {

        int sumOfAllElems = 0;
        //adding all the elements from the initial list
        for (int i : initialList) sumOfAllElems += i;

        //we are checking for the formula sum * i % n == 0, where sum is the sum of all elements, i is the length of the sublist and n is the length of the initial list
        if (!isPossible(sumOfAllElems, initialList.length)) return false;

        //we are creating a list of sets so we can write the sum of all elements in this list( that is going to help us later)
        List<Set<Integer>> listOfAllSums = new ArrayList<>();
        //Set<Integer> nullSet=new HashSet<>();
        //we are only creating n/2 lists because the sums will repeat so maximum n/2 numbers in a list are enough
        for (int i = 0; i <= initialList.length / 2; i++) {
            listOfAllSums.add(new HashSet<>());
        }

        listOfAllSums=createListOfAllSums(initialList,listOfAllSums);

        return searchingForSumInListOfSums(initialList,listOfAllSums,sumOfAllElems);
    }

    private List<Set<Integer>> createListOfAllSums(int[] initialList, List<Set<Integer>> listOfAllSums){
        //to have all the possible sums we have to add chunks of 1,2,...n/2 elements from the list
        //ex: we have the list: 1,2,3,4,5,6,7,8
        //we will add the first element with each of the other elements in the list(1+2,1+3,1+4...); after that we will add the first element with
        //other 2 elements from the list (1+2+3,1+3+4,1+4+5, then 2+3+4,.., 3+4+5...) and so on (1+2+3+4,1+2+3+5), but we have all the previous sums in the list already, so we wont add 1+2+3, we will add 3+3
        //the list will look something like this:
        //1 2 3 4 5 6 7 8
        //3 4 5 6 7 8 9 10 11...
        //6 7 8....
        listOfAllSums.get(0).add(0);
        for (int initialElem : initialList) {
            for (int i = listOfAllSums.size() - 1; i > 0; i--) {
                if (listOfAllSums.get(i - 1).size() > 0) {
                    for (int elem : listOfAllSums.get(i - 1)) listOfAllSums.get(i).add(elem + initialElem);
                }
            }
        }
        return listOfAllSums;
    }

    private boolean searchingForSumInListOfSums(int[] initialList, List<Set<Integer>> listOfAllSums,int sumOfAllElems){
        //after we create the list of sets, we have to check if on specific level we can find the sum we are searching for+checking for the formula in sublists
        for (int i = 1; i < listOfAllSums.size(); i++) {
            if (sumOfAllElems * i % initialList.length == 0 && listOfAllSums.get(i).contains(sumOfAllElems * i / initialList.length)) return true;
        }
        return false;
    }
    private boolean isPossible(int sumOfAllElems, int n) {
        //checking if the formula is ok for the initial list
        for (int i = 1; i < n; i++) {
            if (sumOfAllElems * i % n == 0) return true;
        }
        return false;
    }
}