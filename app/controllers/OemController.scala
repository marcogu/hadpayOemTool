package controllers

/**
  * Created by marco on 15/12/30.
  */

import javax.inject.Inject
import play.api.libs.json.{Json}
import play.api.libs.json.{JsArray, JsObject}
import play.api.mvc.{ Action} //, BodyParsers, Call, Controller, Result
import play.api.mvc.Controller

import play.modules.reactivemongo._
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection

import reactivemongo.api.Cursor

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import models._

class OemController @Inject() (val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def projCollection: JSONCollection = db.collection[JSONCollection]("proj")
  def oemsCollection: JSONCollection = db.collection[JSONCollection]("oems")
  def repoCollection: JSONCollection = db.collection[JSONCollection]("repos")

  def index = Action async {
    val cursor: Cursor[JsObject] = oemsCollection.find(Json.obj()).cursor[JsObject]()
    val futurePersonsList: Future[List[JsObject]] = cursor.collect[List]()
    val futurePersonsJsonArray: Future[JsArray] = futurePersonsList.map { persons => Json.arr(persons) }
    futurePersonsJsonArray.map { persons =>
      Ok(persons)
    }
  }

  def projs = Action async {
    val cursor: Cursor[Proj] = projCollection.find(Json.obj()).cursor[Proj]()
    val futureList:Future[List[Proj]] = cursor.collect[List]()
    futureList.map{ projs =>
      Ok(projs.toString())
    }
  }

  def oems = Action async {
    val cursor: Cursor[ZZTongOem] = oemsCollection.find(Json.obj()).cursor[ZZTongOem]()
    val futureList:Future[List[ZZTongOem]] = cursor.collect[List]()
    futureList.map{ oems =>
      Ok(oems.toString())
    }
  }
}