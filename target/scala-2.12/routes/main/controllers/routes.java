// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseCreditController CreditController = new controllers.ReverseCreditController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseCreditController CreditController = new controllers.javascript.ReverseCreditController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
