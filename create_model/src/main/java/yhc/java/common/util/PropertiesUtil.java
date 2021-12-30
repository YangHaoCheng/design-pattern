package yhc.java.common.util;

import cn.hutool.core.io.file.FileReader;

public class PropertiesUtil {

    public PropertiesUtil() {}
    public FileReader reader = null;

    public String getValue(String path, String key){

        reader = new FileReader(path);
        String result = reader.readString();

        if(null != result && !result.equals("")){
            String[] lines = result.split("\r\n");
            String lineValue = getLineValue(lines, key);
            return lineValue;
        }

        return  "";
    }

    public String getLineValue(String[] lines, String key){
        for (String line : lines) {
            if(line.toLowerCase().startsWith(key.toLowerCase()+"=")){
                return line.split("=")[1];
            }
        }
        return "";
    }

}
