package models

/**
  * Created by XIAMI on 15/12/30.
  */


object AppPlatform extends Enumeration {
  type AppPlatform = Value
  val ios = Value("ios")
  val android = Value("android")
}

object ZZTRepository extends Enumeration {
  type ZZTRepository = Value
  var git = Value("git")
  var svn = Value("svn")
  var filesystem = Value("filesystem ")
}

import ZZTRepository._
import AppPlatform._

case class Repos(id: Option[String], repoType:String, url:String, subpath:String, version:String, pathdes:String,
                 desc:String, branch:String, tag:String)

case class Proj(id: Option[String], repo:String, buildTarget:String, platform:String)

case class ZZTongOem (platform:String, iosOemBuildTarget:String, certName:String, iconAssetsPath:String,
                      resources:String, repoId:String, desktopAppName:String, appId:String, outputPath:String,
                      pckver:String, proj:String, macros:List[String])

import play.api.libs.json.Json
object Proj {
  implicit val projFormat = Json.format[Proj]
}

object ZZTongOem {
  implicit val oemsFormat = Json.format[ZZTongOem]
}




