package controllers

import play.api._
import play.api.mvc._

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

import play.api.libs.iteratee.Iteratee

import reactivemongo.bson.BSONDocument
import reactivemongo.api.collections.bson.BSONCollection

//import play.api.mvc.Action._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def mongodbinfo = Action {
    val futureList = MongoDB.dotest()
    val resultContent = Await.result(futureList, Duration.Inf)
    var responseContent = ""
    resultContent foreach { doc =>
      responseContent = s"found document: ${BSONDocument pretty doc}"
      println(s"found document: ${BSONDocument pretty doc}")
    }
    Ok(views.html.temp1(responseContent))
  }

  def aysncTest = Action.async { implicit request =>
    val futureList = MongoDB.dotest()
    var responseContent = ""

    futureList.map { list =>
      val doc:BSONDocument = list(0)
      responseContent = s"found document: ${BSONDocument pretty doc}"
      Ok(views.html.temp1(responseContent))
    }
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
    val query = BSONDocument("appId" -> "com.handpay.hx.ag")
    // select only the fields 'lastName' and '_id'
    //    val filter = BSONDocument("resources" -> 1, "repoId" -> 1)
    val filter = BSONDocument()
    // excute search in back ground:
    var result:BSONDocument = null

    // excute search in sync model
    val futureList: Future[List[BSONDocument]] =
      collection.
        find(query, filter).
        cursor[BSONDocument].
        collect[List]()

    return futureList
  }

}