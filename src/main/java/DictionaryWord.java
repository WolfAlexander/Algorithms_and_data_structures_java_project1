import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;

/**
 * This object is used for word and list documents where this word is found
 */
public class DictionaryWord implements Comparable<String>{
    private String word;
    private ArrayList<WordsDocument> documentExistingIn = new ArrayList<WordsDocument>();

    /**
     * Constructor of dictionary word
     * @param word is the word
     * @param attr is the attributes of the word
     */
    public DictionaryWord(Word word, Attributes attr) {
        this.word = word.word;
        this.documentExistingIn.add(new WordsDocument(attr.document, attr.occurrence));
    }

    /**
     * This method returns number of occurrences of this word in specific document
     * @param document is the document where words occurrences is counted
     * @return int number of occurrences
     */
    public int getNumberOccurrencesInDocument(Document document){
        int index = findDocumentInWordsDocuments(document);

        if(index >= 0)
            return documentExistingIn.get(index).getOccurrencesCount();
        else
            return 0;
    }

    /**
     * This method makes this object comparable
     * @param t is the object this DictionaryWord will be compared to
     * @return int
     */
    public int compareTo(String t){
        return this.word.compareTo(t);
    }

    /**
     * This method return list of document where this word exists in
     * @return list of documents as ArrayList of Document
     */
    public ArrayList<Document> getDocuments(){
        ArrayList<Document> documents = new ArrayList<Document>();

        for(WordsDocument x : documentExistingIn)
            documents.add(x.getDocument());

        return documents;
    }

    /**
     * This method returns list of documents where this word exists in
     * @return list of documents
     */
    public ArrayList<WordsDocument> getDocumentExistingIn(){
        return this.documentExistingIn;
    }

    /**
     * This method adds attributes(document and occurrence) of this word or increment occurrences count if
     * attributes already exist
     * @param attr is the attributes of this word as Attribute object
     */
    public void addDocument(Attributes attr){
        int index = findDocumentInWordsDocuments(attr.document);

        if(index < 0)
            this.documentExistingIn.add(new WordsDocument(attr.document, attr.occurrence));
        else
            this.documentExistingIn.get(index).incrementOccurrencesCount();
    }

    private int findDocumentInWordsDocuments(Document document){
        BinarySearch<WordsDocument, Document> binarySearch = new BinarySearch<WordsDocument, Document>();
        return binarySearch.search(documentExistingIn, document);
    }
}