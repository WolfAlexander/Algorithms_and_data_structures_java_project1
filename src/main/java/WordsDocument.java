import se.kth.id1020.util.Document;

/**
 * This object is created for every DictionaryWord
 * Holds document reference to Document, number of occurrences of a word is this document
 * and first occurrence of a word
 */
public class WordsDocument implements Comparable<Document>{
    private Document document;
    private int occurrencesCount;
    private int firstOccurrence;

    /**
     * Constructor of the Words Document
     * @param document a document of type Document
     */
    public WordsDocument(Document document, int firstOccurrense){
        this.document = document;
        this.occurrencesCount = 1;
        this.firstOccurrence = firstOccurrense;
    }

    /**
     * This method compares document in this object with another document
     * @param doc that will be compared to this objects document
     * @return int value 1 is this document is greater, 0 if equals and -1 is smaller
     */
    public int compareTo(Document doc){
        return this.document.compareTo(doc);
    }

    public int compareTo(WordsDocument wDoc){
        System.out.println("Compare this two " + this.document.name + " and " + wDoc.document.name + " gives " + this.document.name.compareTo(wDoc.document.name));
        return this.document.name.compareTo(wDoc.document.name);
    }

    /**
     * Compare this document popularity to another
     * @param second is a document that this document will be compared tp
     * @return 1 is larger, -1 is smaller, 0 is equal
     */
    public int compareByPopularity(WordsDocument second){
        if(this.getDocument().popularity > second.getDocument().popularity)
            return 1;
        else if(this.getDocument().popularity < second.getDocument().popularity)
            return -1;
        else
            return 0;
    }

    /**
     * Compare this document first word occurrence to another
     * @param second is a document that this document will be compared tp
     * @return 1 is larger, -1 is smaller, 0 is equal
     */
    public int compareByOccurrence(WordsDocument second){
        if(this.getFirstOccurrence() > second.getFirstOccurrence())
            return 1;
        else if(this.getFirstOccurrence() < second.getFirstOccurrence())
            return -1;
        else
            return 0;
    }

    /**
     * Compare this documents word occurrence-count to another
     * @param second is a document that this document will be compared tp
     * @return 1 is larger, -1 is smaller, 0 is equal
     */
    public int compareByCount(WordsDocument second){
        if(this.getOccurrencesCount() > second.getOccurrencesCount())
            return 1;
        else if(this.getOccurrencesCount() < second.getOccurrencesCount())
            return -1;
        else
            return 0;
    }

    /**
     * This method returns document
     * @return document
     */
    public Document getDocument(){
        return document;
    }

    /**
     * This method increment count
     */
    public void incrementOccurrencesCount(){
        this.occurrencesCount++;
    }

    /**
     * This method returns count
     * @return int count
     */
    public int getOccurrencesCount(){
        return this.occurrencesCount;
    }

    /**
     * This method returns first occurrence of the word in document
     * @return int first occurrence in the document
     */
    public int getFirstOccurrence(){
        return this.firstOccurrence;
    }

    /**
     * This method compares this WordDocument object with another by there document names
     * @param obj is the object that this object will be compared to
     * @return boolean true if they have same document name
     *         or false if not the same name
     *         or false if object is not a WordDocument
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WordsDocument){
            WordsDocument test = (WordsDocument)obj;
            return this.document.name.equals(test.document.name);
        }else
            return false;

    }
}
