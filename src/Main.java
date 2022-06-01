import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        ParametersBag bag = new ParametersBag(args);
        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root.toString());

        long duration = System.currentTimeMillis() - start; //время в милисекундах
        System.out.println(duration + " ms");

    }

}
