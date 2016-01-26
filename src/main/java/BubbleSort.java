import java.util.ArrayList;

public class BubbleSort{
    /**
     * This method performs bubble sort
     * @param a is the collection of elements that has to sorted
     */
    public static ArrayList<WordsDocument> sort(ArrayList<WordsDocument> a, int orderBy, int orderDirection){
        if(a.size() <= 0)
            throw new IllegalArgumentException();


        int r = a.size() - 2;
        boolean swapped = true;

        while(r >= 0 && swapped){
            swapped = false;

            for(int i = 0; i <= r; i++){
                int comp = 0;
                switch (orderBy){
                    case 0:
                    comp = a.get(i).compareByCount(a.get(i+1));
                    break;

                    case 1:
                    comp = a.get(i).compareByPopularity(a.get(i+1));
                    break;

                    case 2:
                    comp = a.get(i).compareByOccurrence(a.get(i+1));
                    break;
                }

                if(orderDirection == 0){
                    if(comp > 0) {
                        swapped = true;
                        swap(a, i, i+1);
                    }
                }else{
                    if(comp < 0) {
                        swapped = true;
                        swap(a, i, i+1);
                    }
                }
            }
            r--;
        }

        return a;
    }

    private static void swap(ArrayList<WordsDocument> a, int first, int second){
        WordsDocument temp = a.get(second);
        a.set(second, a.get(first));
        a.set(first, temp);
    }
}