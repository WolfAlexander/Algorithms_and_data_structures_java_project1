import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

/**
 * Starts TinySearchEngine and whole program
 */
public class Startup {
    public static void main(String[] args) throws Exception{
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);
    }
}