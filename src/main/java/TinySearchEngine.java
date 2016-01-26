import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.List;

public class TinySearchEngine implements TinySearchEngineBase {
    ArrayList<DictionaryWord> dictionary = new ArrayList<DictionaryWord>();

    /**
     * This method indexes incoming words
     * @param word is the Word object
     * @param attributes is the Attributes
     */
    public void insert(Word word, Attributes attributes) {
        if(dictionary.size() == 0)
            dictionary.add(new DictionaryWord(word, attributes));
        else{
            BinarySearch<DictionaryWord, String>  binarySearch = new BinarySearch<DictionaryWord, String>();

            int index = binarySearch.search(dictionary, word.word);
            if(index < 0)
                dictionary.add((-1*index-1), new DictionaryWord(word, attributes));
            else
                dictionary.get(index).addDocument(attributes);
        }
    }

    /**
     * This method searches for string s or executes a query if s is a query
     * @param s is the string to search for of a query
     * @return list of documents that satisfies a query
     */
    public List<Document> search(String s) {
        try{
            Query query = new Query(s, dictionary);
            return query.execute();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Invalid query!");
            return null;
        }
    }
}