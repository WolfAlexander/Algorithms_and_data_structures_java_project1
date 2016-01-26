import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This object is created for every user search request
 */
public class Query {
    private ArrayList<DictionaryWord> list;
    private String[] query;
    private ArrayList<String> properties = new ArrayList<String>(Arrays.asList("count", "popularity", "occurrence"));
    private ArrayList<String> orderDirections = new ArrayList<String>(Arrays.asList("asc", "desc"));

    /**
     * Constructor of query splits string query in parts
     * @param query is the incoming query
     */
    public Query(String query, ArrayList<DictionaryWord> list){
        this.list = list;
        this.query = query.split(" ");
    }

    /**
     * This method starts executing query
     * @return list of document
     */
    public ArrayList<Document> execute(){
        ArrayList<WordsDocument> listOfWordsDocuments = new ArrayList<WordsDocument>();

        for(int i = 0; i < query.length; i++){
            if(!isACommand(query[i]))
                addWithoutDuplicate(listOfWordsDocuments, getDocumentsWhereWordExists(query[i]));
            else if(i >= 1 && isACommand(query[i])) {
                listOfWordsDocuments = orderBy(listOfWordsDocuments);
                break;
            }
            else
                throw new IllegalArgumentException();
        }

        return convertToDocumentsList(listOfWordsDocuments);
    }

    private boolean isACommand(String s){
        return s.equals("orderby");
    }

    private ArrayList<WordsDocument> getDocumentsWhereWordExists(String word){
        ArrayList<WordsDocument> documents = new ArrayList<WordsDocument>();
        BinarySearch<DictionaryWord, String>  binarySearch = new BinarySearch<DictionaryWord, String>();

        int index = binarySearch.search(list, word);

        if(index >= 0)
            documents = list.get(index).getDocumentExistingIn();

        return documents;
    }

    private void addWithoutDuplicate(ArrayList<WordsDocument> addTo, ArrayList<WordsDocument> addFrom) {
        for (WordsDocument x : addFrom)
            if (!addTo.contains(x))
                addTo.add(x);
    }

    private ArrayList<WordsDocument> orderBy(ArrayList<WordsDocument> listToOrder){
        ArrayList<WordsDocument> orderedList;

        int propertyIndex = properties.indexOf(query[query.length - 2]);
        int orderDirectionIndex = orderDirections.indexOf(query[query.length-1]);

        if(propertyIndex != -1 && orderDirectionIndex != -1 && query[query.length-3].equals("orderby")){
            orderedList = BubbleSort.sort(listToOrder, propertyIndex, orderDirectionIndex);
        }
        else
            throw new IllegalArgumentException();

        return orderedList;
    }

    private ArrayList<Document> convertToDocumentsList(ArrayList<WordsDocument> list){
        ArrayList<Document> convertedList = new ArrayList<Document>();

        for(WordsDocument x : list)
            convertedList.add(x.getDocument());

        /*for(WordsDocument x : list)
            System.out.println("Document: " + x.getDocument().name + "Pop: " + x.getDocument().popularity + " Count: " + x.getOccurrencesCount() + " First occur: " + x.getFirstOccurrence());*/

        return convertedList;
    }
}