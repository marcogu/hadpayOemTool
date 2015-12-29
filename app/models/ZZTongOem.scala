package models

/**
  * Created by marco on 15/12/29.
  */
import play.api.data._
import play.api.data.Forms.{ text, longNumber, mapping, nonEmptyText, optional }

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

import AppPlatform._
import ZZTRepository._

case class Repos(id: Option[String], repoType:ZZTRepository, url:String, subpath:String, version:String, pathdes:String,
                 desc:String, branch:String, tag:String)

case class Proj(id: Option[String], repo:String, buildTarget:String)

case class ZZTongOem (id: Option[String], platform:AppPlatform, iosOemBuildTarget:String, certName:String,
                      iconAssetsPath:String, resources:String, repoId:String, macros:List[String],
                      desktopAppName:String, appId:String, pckver:String, outputPath:String, proj:String)
