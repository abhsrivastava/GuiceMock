package com.abhi

/**
  * Created by ASrivastava on 3/18/17.
  */
import com.google.inject._

object MyApp extends App {
   val module = new MyModule
   val injector = Guice.createInjector(module)
   val foo = injector.getInstance(classOf[Foo])
   println(foo.sayHello("Abhi", 10, 10))
}

class MyModule extends AbstractModule {
   @Override def configure() = {
      bind(classOf[Calc])
   }
}

class Foo @Inject()(calc: Calc) {
   def sayHello(name: String, i: Int, j: Int) : String = {
      "Hello " + name + calc.add(i, j)
   }
}
class Calc {
   def add(i: Int, j: Int) : Int = i + j
}
