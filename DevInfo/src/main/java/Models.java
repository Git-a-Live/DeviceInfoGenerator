import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Models {
    private static final ArrayList<String> iosList = new ArrayList<>(Arrays.asList(
            "10.0", "10.0.1", "10.0.2", "10.0.3", "10.1", "10.1.1", "10.2", "10.2.1", "10.3", "10.3.1",
            "10.3.2", "10.3.3", "10.3.4",

            "11.0", "11.0.1", "11.0.2", "11.0.3", "11.1", "11.1.1", "11.1.2", "11.2", "11.2.1", "11.2.2",
            "11.2.5", "11.2.6", "11.3", "11.3.1", "11.4", "11.4.1",

            "12.0", "12.0.1", "12.1", "12.1.1", "12.1.2", "12.1.3", "12.1.4", "12.2", "12.3", "12.3.1",
            "12.3.2", "12.4", "12.4.1", "12.4.2", "12.4.3", "12.4.4", "12.4.5", "12.4.6", "12.4.7", "12.4.8",

            "13.0", "13.1", "13.1.1", "13.1.2", "13.1.3", "13.2", "13.2.1", "13.2.2", "13.2.3", "13.3", "13.3.1",
            "13.4", "13.4.1", "13.5", "13.5.1", "13.6", "13.6.1"));


    public static IPhoneModel getModels(String iphonename)
    {
        return getMap().get(iphonename);
    }

    public static ArrayList<String> getIosList()
    {
        return iosList;
    }

    private static Map<String, IPhoneModel> getMap()
    {
        Map<String, IPhoneModel> iphoneMap = new HashMap<>();
        iphoneMap.put("iPhone 6s", new IPhoneModel(375, 667, 2,
                setROMs(new Integer[]{16, 32, 64, 128}), 2, "0", "N71AP"));

        iphoneMap.put("iPhone 7", new IPhoneModel(375, 667, 2,
                setROMs(new Integer[]{32, 128, 256}), 2, "0", "D10AP"));

        iphoneMap.put("iPhone 8", new IPhoneModel(375, 667, 2,
                setROMs(new Integer[]{64, 128, 256}), 2, "0", "D20AP"));

        iphoneMap.put("iPhone X", new IPhoneModel(375, 812, 3,
                setROMs(new Integer[]{64, 256}), 3, "1", "D22AP"));

        iphoneMap.put("iPhone 11", new IPhoneModel(414, 896, 2,
                setROMs(new Integer[]{64, 128, 256}), 4, "1", "N104AP"));

        return iphoneMap;
    }

    private static ArrayList<Integer> setROMs(Integer[] roms){
        return new ArrayList<>(Arrays.asList(roms));
    }
}
