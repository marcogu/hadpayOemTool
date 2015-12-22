package controllers

import play.api._
import play.api.mvc._

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.libs.iteratee.Iteratee

import reactivemongo.bson.BSONDocument
import reactivemongo.api.collections.bson.BSONCollection

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def mongodbinfo = Action {
    val futureList = MongoDB.dotest()
    futureList.map { list =>
      list.foreach { doc =>
        println(s"found document: ${BSONDocument pretty doc}")
      }
    }
    println("-------------")
    Ok(views.html.temp1("Your new application is ready."))
  }

}

object MongoDB {

  var isConnected = false

  def dotest():Future[List[BSONDocument]] = {

    if (isConnected)
      return null

    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost:27017"))
    // Gets a reference to the database "plugin"
    val db = connection("local")
    // Gets a reference to the collection "acoll"
    // By default, you get a BSONCollection.
    val collection:BSONCollection = db("oems")
    // Select only the documents which field 'bundleId' equals 'com.handpay.hx.ghtx'
    val query = BSONDocument("bundleId" -> "com.handpay.hx.ghtx")
    // select only the fields 'lastName' and '_id'
    val filter = BSONDocument("bundleDisplayName" -> 1, "targetName" -> 1)
    // excute search in back ground:
    var result:BSONDocument = null
//    collection.find(query, filter).cursor[BSONDocument].enumerate().apply(Iteratee.foreach { doc =>
//      result = doc
//      println(s"found document: ${BSONDocument pretty doc}")
//    })
//    println(s"--------${result}")
//    isConnected = true

    // excute search in sync model
    val futureList: Future[List[BSONDocument]] =
      collection.
        find(query, filter).
        cursor[BSONDocument].
        collect[List]()

    return futureList
  }

}