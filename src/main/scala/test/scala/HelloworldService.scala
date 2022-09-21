package test.scala

import test.hello._

import scala.concurrent.Future

class HelloworldService extends GreeterGrpc .Greeter{
  override def sayHello(request: HelloRequest): Future[HelloReply] = {
    val name : String = request.name
    val reply = HelloReply(message = "Welcome, " + name)
    Future.successful(reply)
  }
}
