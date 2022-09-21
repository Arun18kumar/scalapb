package v1.iot_decoder.scala
import test.hello.{HelloReply, HelloRequest}
import v1.iot_decoder._

import _root_.scala.concurrent.Future

class IOTDecoderService extends IoTDecoderServiceGrpc .IoTDecoderService{
  override def decode(requests: IOTDecodeRequests): Future[IOTDecodeResponses] ={
    val data = requests.requests
    val output = IOTDecodeResponses()
    Future.successful(output)
  }
}
