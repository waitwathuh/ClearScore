// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseCreditController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def creditcards(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "creditcards")
    }
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:12
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/lib/swagger-ui"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "docs/swagger-ui/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }


}
