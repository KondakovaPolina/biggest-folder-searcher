import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //for (int i = 0; i < args.length; i++) {
        //    System.out.println(i + "=>" + args[i]);
        //}

        //System.exit(0);
        
        String folderPath = "C:/Users/user/Desktop/Pasport";
        long sizeLimit = 25 * 1024 * 1024;
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
