package yhc.java.hadoop;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZHUANGHAOTANG
 * @Date: 2018/11/6 11:49
 * @Description:
 */
public class HDFSUtils {

    /**
     * HDFS NamenNode URL
     */
    private static final String NAMENODE_URL = "hdfs://mycluster:8020";

    /**
     * 配置项
     */
    private static Configuration conf = null;

    static {
        conf = new Configuration();
        //指定默认连接的NameNode,使用NameService的名称
        conf.set("fs.defaultFS", "hdfs://mycluster");
        //指定NameService的名称
        conf.set("dfs.nameservices", "mycluster");
        //指定NameService下的NameNode列表
        conf.set("dfs.ha.namenodes.mycluster", "nn1,nn2");
        //分别指定NameNode的RPC通讯地址
        conf.set("dfs.namenode.rpc-address.mycluster.nn1", "hadoop1:8020");
        conf.set("dfs.namenode.rpc-address.mycluster.nn2", "hadoop2:8020");
        //配置NameNode失败自动切换的方式
        conf.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
    }

    /**
     * 创建目录
     */
    public static void mkdir(String dir) throws Exception {
        if (StringUtils.isBlank(dir)) {
            throw new Exception("Parameter Is NULL");
        }
        dir = NAMENODE_URL + dir;
        FileSystem fs = FileSystem.get(URI.create(NAMENODE_URL), conf);
        if (!fs.exists(new Path(dir))) {
            fs.mkdirs(new Path(dir));
        }
        fs.close();
    }

    /**
     * 删除目录或文件
     */
    public static void delete(String dir) throws Exception {
        if (StringUtils.isBlank(dir)) {
            throw new Exception("Parameter Is NULL");
        }
        dir = NAMENODE_URL + dir;
        FileSystem fs = FileSystem.get(URI.create(NAMENODE_URL), conf);
        fs.delete(new Path(dir), true);
        fs.close();
    }


    /**
     * 遍历指定路径下的目录和文件
     */
    public static List<String> listAll(String dir) throws Exception {
        List<String> names = new ArrayList<>();
        if (StringUtils.isBlank(dir)) {
            throw new Exception("Parameter Is NULL");
        }
        dir = NAMENODE_URL + dir;
        FileSystem fs = FileSystem.get(URI.create(dir), conf);
        FileStatus[] files = fs.listStatus(new Path(dir));
        for (int i = 0, len = files.length; i < len; i++) {
            if (files[i].isFile()) { //文件
                names.add(files[i].getPath().toString());
            } else if (files[i].isDirectory()) { //目录
                names.add(files[i].getPath().toString());
            } else if (files[i].isSymlink()) { //软或硬链接
                names.add(files[i].getPath().toString());
            }
        }
        fs.close();
        return names;
    }


    /**
     * 上传当前服务器的文件到HDFS中
     */
    public static void uploadLocalFileToHDFS(String localFile, String hdfsFile) throws Exception {
        if (StringUtils.isBlank(localFile) || StringUtils.isBlank(hdfsFile)) {
            throw new Exception("Parameter Is NULL");
        }
        hdfsFile = NAMENODE_URL + hdfsFile;
        FileSystem fs = FileSystem.get(URI.create(NAMENODE_URL), conf);
        Path src = new Path(localFile);
        Path dst = new Path(hdfsFile);
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }


    /**
     * 通过流上传文件
     */
    public static void uploadFile(String hdfsPath, InputStream inputStream) throws Exception {
        if (StringUtils.isBlank(hdfsPath)) {
            throw new Exception("Parameter Is NULL");
        }
        hdfsPath = NAMENODE_URL + hdfsPath;
        FileSystem fs = FileSystem.get(URI.create(NAMENODE_URL), conf);
        FSDataOutputStream os = fs.create(new Path(hdfsPath));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] data = new byte[1024];
        while (bufferedInputStream.read(data) != -1) {
            os.write(data);
        }
        os.close();
        fs.close();
    }


    /**
     * 从HDFS中下载文件
     */
    public static byte[] readFile(String hdfsFile) throws Exception {
        if (StringUtils.isBlank(hdfsFile)) {
            throw new Exception("Parameter Is NULL");
        }
        hdfsFile = NAMENODE_URL + hdfsFile;
        FileSystem fs = FileSystem.get(URI.create(NAMENODE_URL), conf);
        Path path = new Path(hdfsFile);
        if (fs.exists(path)) {
            FSDataInputStream is = fs.open(path);
            FileStatus stat = fs.getFileStatus(path);
            byte[] data = new byte[(int) stat.getLen()];
            is.readFully(0, data);
            is.close();
            fs.close();
            return data;
        } else {
            throw new Exception("File Not Found In HDFS");
        }
    }
}
