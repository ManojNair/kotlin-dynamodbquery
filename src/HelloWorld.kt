import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.QueryRequest

fun main(args: Array<String>) {


    var dynamoDB = AmazonDynamoDBClientBuilder.defaultClient()

    var request = QueryRequest()
            .withTableName("Products")
            .withExpressionAttributeNames(hashMapOf(

                    "#id" to "Id",
                    "#type" to "Type"

            ))
            .withExpressionAttributeValues(hashMapOf(

                    ":id" to AttributeValue().withN("7"),
                    ":type" to AttributeValue().withS("Car")
            ))
            .withKeyConditionExpression("#id = :id AND #type = :type")


    dynamoDB.query(request).items.forEach {

        i ->
        i.forEach { t, u ->
            run {
                println("$t : ${u?.s ?: u.n} ")
            }
        }
    }

}

