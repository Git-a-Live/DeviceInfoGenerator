import java.util.Scanner;

public class Main {
    public static int mod;
    public static boolean specified = false;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入来源：");
        String origin = scanner.nextLine();
        System.out.println("输入品牌：");
        String brand = scanner.nextLine();
        System.out.println("是否指定机型（Y/N）：");
        String spmod = scanner.nextLine();
        if (spmod.equalsIgnoreCase("Y")){
            specified = true;
            System.out.println("输入指定机型（1-iPhone 6s, 6-iPhone 7, 11-iPhone 8, 16-iPhone X, 21-iPhone 11）：");
            mod = scanner.nextInt();
        }
        System.out.println("输入要创建的设备信息数量(不要超过65500条)：");
        int num = scanner.nextInt();
        try {
            ExcelUtils.setExcel(origin,brand,num);
        } catch (Exception e){
            System.err.println(e.getCause().toString());
        }
    }
}
