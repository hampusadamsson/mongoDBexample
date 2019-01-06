import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.mongodb.client.model.Indexes;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBExamples {
    private static final Logger logger = Logger.getLogger( MongoDBExamples.class.getName() );

    public static void main(final String[] args) {

        // 1. Connect to MongoDB instance running on localhost
        MongoClient mongoClient = new MongoClient();

        // 2. Access database named 'PeoplePojo'
        String db = "PeoplePojo";
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongo.getDatabase(db);

        MongoCollection<People> collection = database.getCollection("People", People.class);

        People p1 = new People("John", "Doe");
        p1.setRelatives(asList(new People("Jane", "Doe")));

        // 3. Insert
        collection.insertOne(p1);

        // 4. Query for object
        ArrayList<People> results = collection.find().into(new ArrayList<People>());
        //System.out.println(results.get(0));
        results.get(5).print();
        /*

        // 4. Create Index
        collection.createIndex(Indexes.ascending("name"));

        // 5. Perform Aggregation
        collection.aggregate(asList(match(eq("categories", "Bakery")),
                group("$stars", sum("count", 1))));
        */

        mongoClient.close();

    }

}