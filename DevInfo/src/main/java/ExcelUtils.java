import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

public class ExcelUtils {
    private static final XSSFWorkbook hssfWorkbook = new XSSFWorkbook();
    private static final XSSFSheet hssfSheet = hssfWorkbook.createSheet("随机设备信息");

    public static void setExcel(String origin, String brand, int row) {
        int flag;
        if (row > 65536){
            System.err.println("创建数量超出上限，强制调整为创建65500条设备信息");
            row = 65500;
        }
        XSSFCellStyle style = hssfWorkbook.createCellStyle();
        XSSFFont font = hssfWorkbook.createFont();
        font.setFontName("宋体");
        style.setFont(font);
        System.out.println("开始创建" + row + "条设备信息...");
        XSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue("序号");
        hssfRow.createCell(1).setCellValue("来源");
        hssfRow.createCell(2).setCellValue("品牌");
        hssfRow.createCell(3).setCellValue("系统名称");
        hssfRow.createCell(4).setCellValue("系统版本");
        hssfRow.createCell(5).setCellValue("屏幕宽度");
        hssfRow.createCell(6).setCellValue("屏幕高度");
        hssfRow.createCell(7).setCellValue("屏幕密度");
        hssfRow.createCell(8).setCellValue("磁盘可用空间");
        hssfRow.createCell(9).setCellValue("总内存数");
        hssfRow.createCell(10).setCellValue("广告标识");
        hssfRow.createCell(11).setCellValue("是否刘海屏");
        hssfRow.createCell(12).setCellValue("ua");
        hssfRow.createCell(13).setCellValue("工程代号");
        hssfRow.createCell(14).setCellValue("机型");
        for (int i = 1; i <= row; i++) {
            if (Main.specified){
                flag = Main.mod;
            } else {
                flag = new Random().nextInt(30);
            }
            String[] tmp = randomOSV(flag);
            String osv = tmp[new Random().nextInt(tmp.length)];
            hssfRow = hssfSheet.createRow(i);
            hssfRow.createCell(0).setCellValue(i);//序号
            hssfRow.createCell(1).setCellValue(origin);//来源
            hssfRow.createCell(2).setCellValue(brand);//品牌
            hssfRow.createCell(3).setCellValue(2);//系统名称
            hssfRow.createCell(4).setCellValue(osv);//系统版本
            hssfRow.createCell(5).setCellValue(randomModel(flag).getSw());//屏宽
            hssfRow.createCell(6).setCellValue(randomModel(flag).getSh());//屏高
            hssfRow.createCell(7).setCellValue(randomModel(flag).getDs());//屏幕密度
            hssfRow.createCell(8).setCellValue(randomModel(flag).getTsp());//磁盘可用空间
            hssfRow.createCell(9).setCellValue(randomModel(flag).getTmr());//总内存数
            hssfRow.createCell(10).setCellValue(UUID.randomUUID().toString().toUpperCase());//广告标识
            hssfRow.createCell(11).setCellValue(randomModel(flag).getNtc());//是否刘海屏
            hssfRow.createCell(12).setCellValue(getUA(osv));//UA
            hssfRow.createCell(13).setCellValue(randomModel(flag).getIn());//工程代号
            hssfRow.createCell(14).setCellValue(randomModel(flag).getMod());//机型
            hssfSheet.setColumnWidth(10,10000);
            hssfSheet.setColumnWidth(12,15000);
            ParamCheck.paramCheck(i,randomModel(flag),osv,getUA(osv));
            process(i,row);
        }
        try (FileOutputStream output = new FileOutputStream("设备信息_"+ row +".xlsx")) {
            hssfWorkbook.write(output);
            output.flush();
            System.out.println("设备信息创建完毕");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void process(int i, int row){
        if (i == 1 && i != row){
            System.out.println("0%");
        } else if (i == Math.floor(0.1 * row)){
            System.out.println("10%");
        } else if (i == Math.floor(0.2 * row)){
            System.out.println("20%");
        } else if (i == Math.floor(0.3 * row)){
            System.out.println("30%");
        } else if (i == Math.floor(0.4 * row)){
            System.out.println("40%");
        } else if (i == Math.floor(0.5 * row)){
            System.out.println("50%");
        } else if (i == Math.floor(0.6 * row)){
            System.out.println("60%");
        } else if (i == Math.floor(0.7 * row)){
            System.out.println("70%");
        } else if (i == Math.floor(0.8 * row)){
            System.out.println("80%");
        } else if (i == Math.floor(0.9 * row)){
            System.out.println("90%");
        } else if (i == row){
            System.out.println("100%");
            try {
                Thread.sleep(500);
            } catch (Exception e){
                System.out.println(e.getMessage().toString());
            }
            System.out.println("正在创建文件...");
        }
    }

    private static int randomRAM(int flag) {
        int ram;
        if (flag <= 5) { //iPhone 6s
            ram = randomRAM(new int[]{16, 32, 64, 128});
        } else if (flag <= 10) { //iPhone 7
            ram = randomRAM(new int[]{32, 128, 256});
        } else if (flag <= 15) { //iPhone 8
            ram = randomRAM(new int[]{64, 128, 256});
        } else if (flag <= 20){ //iPhone X
            ram = randomRAM(new int[]{64, 256});
        } else { //iPhone 11
            ram = randomRAM(new int[]{64, 128, 256});
        }
        return ram;
    }

    private static IPhoneModels randomModel(int flag){
        IPhoneModels mod;
        if (flag <= 5){ //iPhone 6s☑️
            mod = new IPhoneModels(375,667,2,randomRAM(flag),2,0,"N71AP","iPhone 6s");
        } else if (flag <= 10){ //iPhone 7☑️
            mod = new IPhoneModels(375,667,2,randomRAM(flag),2,0,"D10AP","iPhone 7");
        } else if (flag <= 15){ //iPhone 8☑️
            mod = new IPhoneModels(375,667,2,randomRAM(flag),2,0,"D20AP","iPhone 8");
        } else if (flag <= 20){ //iPhone X☑️
            mod = new IPhoneModels(375,812,3,randomRAM(flag),3,1,"D22AP","iPhone X");
        } else { //iPhone 11☑️
            mod = new IPhoneModels(414,896,2,randomRAM(flag),4,1,"N104AP","iPhone 11");
        }
        return mod;
    }

    private static int randomRAM(int[] rams){
        return rams[new Random().nextInt(rams.length)];
    }

    private static String[] randomOSV(int flag){
        String[] osvs;
        if (flag <= 5){ //iPhone 6s
            osvs = new String[]{"10.0.2","10.2.1","10.3.2","10.3.3"};
        } else if (flag <= 10){ //iPhone 7
            osvs = new String[]{"12.2","12.3.1","13.2.3","13.3"};
        } else if (flag <= 15){ //iPhone 8
            osvs = new String[]{"12.4.5","12.4.4","13.3","13.3.1"};
        } else if (flag <= 20){ //iPhone X
            osvs = new String[]{"13.2.3","13.5.1","13.6","13.4.1"};
        } else { //iPhone 11
            osvs = new String[]{"13.2.3","13.5.1","13.6","13.4.1"};
        }
        return osvs;
    }

    private static String getUA(String osv){
        String ua;
        switch (osv){
            case "10.0.2": {
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_2 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/14A456 Safari/602.1";
                break;
            }
            case "10.2.1":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1";
                break;
            }
            case "10.3.2":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) Version/10.0 Mobile/14F89 Safari/602.1";
                break;
            }
            case "10.3.3":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.0 Mobile/14G60 Safari/602.1";
                break;
            }
            case "12.2":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1 Mobile/15E148 Safari/604.1";
                break;
            }
            case "12.3.1":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1.1 Mobile/15E148 Safari/604.1";
                break;
            }
            case "12.4.4":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_4_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1.2 Mobile/15E148 Safari/604.1";
                break;
            }
            case "12.4.5":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_4_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1.2 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.2.3":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.3":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.4 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.3.1":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.4.1":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.5.1":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1";
                break;
            }
            case "13.6":{
                ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.2 Mobile/15E148 Safari/604.1";
                break;
            }
            default:{
                ua = "";
                break;
            }
        }
        return ua;
    }
}
