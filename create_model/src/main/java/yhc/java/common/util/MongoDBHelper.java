package yhc.java.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MongoDB管理类
 * @author jiangyuechao
 *
 */
public class MongoDBHelper {
    static Logger logger = LoggerFactory.getLogger(MongoDBHelper.class);

    public static void main(String[] args) {
        MongoCredential credential = MongoCredential.createCredential("pubuser", "admin", "KAov0XvKeFPVrF+4+rz-6P49B9U6Rz".toCharArray());
        MongoClientOptions build = MongoClientOptions.builder().connectTimeout(40000).applicationName("test").build();
        MongoClient client = new MongoClient(new ServerAddress("172.17.200.2",20017), credential,build);
        MongoDatabase database = client.getDatabase("changingService");
        MongoCollection<Document> mongoCollection = client.getDatabase("activity").getCollection("activityRecommendRewordRecordPo");
        System.out.println(mongoCollection.find().limit(100).first().toString());
        System.out.println(mongoCollection.countDocuments());

        MongoCollection<Document> collection = database.getCollection("OrderChargingDetail");
        System.out.println("collection length ：" + collection.count());
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
            logger.info(next.toString());

        }

        client.close();
    }
}
