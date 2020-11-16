import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentUtils {
    public static String getSpecificContent(String content, String matchReg){
        String target = null;
        if (content == null || content.equals("")){
            System.err.println("传入了空文本进行匹配");
            target = "No Matched Content";
        } else {
            Pattern pattern = Pattern.compile(matchReg);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find())
            {
                target = matcher.group();
            }
            if (target != null){
                target = target.replaceAll("\"\\S+?\":","")
                        .replace(",","")
                        .replace("\"","")
                        .replace("}","");
            } else {
                target = "未发现匹配内容";
            }
        }
        return target;
    }
}
