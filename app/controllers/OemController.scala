package controllers

/**
  * Created by marco on 15/12/30.
  */

import javax.inject.Inject
import play.api.mvc.{ Action} //, BodyParsers, Call, Controller, Result
/**  import reactivemongo.bson.BSONDocument // for direct connect db */


import play.api.mvc.Controller
import play.modules.reactivemongo._

/** import scala.concurrent.ExecutionContext.Implicits.global // for direct connect db */

class OemController @Inject() (val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def index = Action {
//    println(s"")
    Ok(views.html.index("Your new application is ready."))
  }

  def aysncTest = Action{
    Ok(views.html.temp1("this is a stub for test direct connect mongo db"))
  }

  /** // for direct connect db
//  def aysncTest = Action.async { implicit request =>
//    val futureList = MYMongoDB.dotest()
//    var responseContent = ""
//
//    futureList.map { list =>
//      val doc:BSONDocument = list(0)
//      responseContent = s"found document: ${BSONDocument pretty doc}"
//      Ok(views.html.temp1(responseContent))
//    }
//  }
    */
}

/** // for direct connect db
//import reactivemongo.api.MongoDriver
//import reactivemongo.api.collections.bson.BSONCollection
//import reactivemongo.bson.BSONDocument
//
//import scala.concurrent.Future
//
//
//object MYMongoDB {
//
//  var isConnected = false
//
//  def dotest():Future[List[BSONDocument]] = {
//
//    if (isConnected)
//      return null
//
//    // gets an instance of the driver
//    // (creates an actor system)
//    val driver = new MongoDriver
//    val connection = driver.connection(List("localhost:27017"))
//    // Gets a reference to the database "plugin"
//    val db = connection("local")
//    // Gets a reference to the collection "acoll"
//    // By default, you get a BSONCollection.
//    val collection:BSONCollection = db("oems")
//    // Select only the documents which field 'bundleId' equals 'com.handpay.hx.ghtx'
//    val query = BSONDocument("appId" -> "com.handpay.hx.ag")
//    // select only the fields 'lastName' and '_id'
//    //    val filter = BSONDocument("resources" -> 1, "repoId" -> 1)
//    val filter = BSONDocument()
//    // excute search in back ground:
//    var result:BSONDocument = null
//
//    // excute search in sync model
//    val futureList: Future[List[BSONDocument]] =
//      collection.
//        find(query, filter).
//        cursor[BSONDocument].
//        collect[List]()
//
//    return futureList
//  }
//
//}
  */


