package com

import com.abhi.{Calc, Foo}
import com.google.inject.{AbstractModule, Guice}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSpec

/**
  * Created by ASrivastava on 3/18/17.
  */
class abhiFooTest extends FunSpec  {
   describe("test Foo but mock calc") {
      it("should use mock instance ") {
         val module = new TestModule
         val injector = Guice.createInjector(module)
         val foo = injector.getInstance(classOf[Foo])
         val result = foo.sayHello("Abhi", 10, 10)
         assert(result == "Hello Abhi30")
      }
   }
}

class TestModule extends AbstractModule with MockFactory {
   override def configure() = {
      val x = mock[Calc]
      (x.add _).expects(10, 10).returning(30)
      bind(classOf[Calc]).toInstance(x)
   }
}
