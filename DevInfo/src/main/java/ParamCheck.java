public class ParamCheck {
    private static final StringBuilder errorInfo = new StringBuilder();
    public static void paramCheck(int seq, IPhoneModels im, String osv, String ua)
    {
        errorInfo.delete(0,errorInfo.length());
        if (!modelCheck(im) || !uaCheck(osv, ua))
        {
            System.out.println(seq + ", 设备信息错误: " + errorInfo.toString());
        }
    }

    private static boolean modelCheck(IPhoneModels im)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("设备参数异常：[");
        int flag = 0;
        IPhoneModel model = Models.getModels(im.getMod());
        if (model != null){
            if (model.getSW() != im.getSw())
            {
                stringBuilder.append("屏幕宽度不匹配或为空; ");
                flag++;
            }
            if (model.getSH() != im.getSh())
            {
                stringBuilder.append("屏幕高度不匹配或为空; ");
                flag++;
            }
            if (model.getDS() != im.getDs())
            {
                stringBuilder.append("缩放因子不匹配或为空; ");
                flag++;
            }
            if (!model.getNTC().equals(String.valueOf(im.getNtc())))
            {
                stringBuilder.append("刘海屏存否不匹配或为空; ");
                flag++;
            }
            if (!model.getTSP().contains(im.getTsp()))
            {
                stringBuilder.append("机身容量不在匹配范围内或为空; ");
                flag++;
            }
            if (model.getTMR() != im.getTmr())
            {
                stringBuilder.append("总内存数不匹配或为空; ");
                flag++;
            }
            if (!model.getIN().equals(im.getIn()))
            {
                stringBuilder.append("内部工程代号不匹配或为空; ");
                flag++;
            }
        } else {
            stringBuilder.append("本地校验未发现此机型; ");
            flag++;
        }
        if (flag != 0)
        {
            errorInfo.append(stringBuilder.append("]")).append("\t");
        }
        return flag == 0;
    }

    private static boolean hasThisOSV(String osv)
    {
        if (osv == null || !Models.getIosList().contains(osv))
        {
            errorInfo.append("获取到iOS版本为空或该版本iOS不存在; ").append("\t");
        }
        return Models.getIosList().contains(osv);
    }

    private static boolean uaCheck(String osv, String ua)
    {
        int flag = 0;
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UA异常：[");
        if (hasThisOSV(osv)){
            if (ua == null)
            {
                stringBuilder.append("获取到UA为空; ");
                flag++;
            }
            else
            {
                if (!ua.contains("iPhone"))
                {
                    stringBuilder.append("当前UA不是用于iPhone设备; ");
                    flag++;
                }

                s = ContentUtils.getSpecificContent(ua, "OS \\S+? like")
                        .replace("_", ".")
                        .replace("OS ", "")
                        .replace(" like", "");
                if (!osv.equals(s))
                {
                    stringBuilder.append("当前系统版本与UA中系统版本不匹配; ");
                    flag++;
                }
            }
        } else {
            stringBuilder.append("获取到系统版本为空或系统版本不存在; ");
            flag++;
        }
        if (flag != 0)
        {
            errorInfo.append(stringBuilder.append("]")).append("\t");
        }
        return flag == 0;
    }
}
