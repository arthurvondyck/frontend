package controllers

import org.scalatest.FunSuite
import scala.concurrent.{Await, Future}
import play.api.libs.json.{JsString, JsValue, Json}
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import play.api.libs.ws.Response
import scala.concurrent._
import ExecutionContext.Implicits.global

class OpenCtaApiTest extends FunSuite with OpenCtaApi {


  test("That we can actually fetch top comment") {
    val topCommentFuture: Future[JsValue] = getTopComment()
    val topCommentJson = Await.result(topCommentFuture, Duration(1, TimeUnit.SECONDS))
    assert(expectedComment === topCommentJson)
  }
  
  override protected def getJsonOrError(url: String, onError: (Response) => String, headers: (String, String)*): Future[JsValue] = {
    future{Json.parse(
      """
        |
        |{
        |    "status": "ok",
        |    "self": "http://cta.guardian.com/article/p/1234",
        |    "components": [
        |        {
        |            "type": "top-comments-for-article",
        |            "discussionMetadata": {
        |                "key": "/p/323gn",
        |                "webUrl": "http://www.theguardian.com/commentisfree/2011/sep/21/you-tell-us",
        |                "apiUrl": "http://discussion.guardianapis.com/discussion-api/discussion//p/323gn",
        |                "title": "Ideas for September 21-22"
        |            },
        |            "comments": [
        |                {
        |                    "apiUrl": "http://discussion.guardianapis.com/discussion-api/comment/28656413",
        |                    "body": "<p>In terms of the diversity and originality of his work as well as his lasting influence, it's probably Aristotle. Marx's ideas and arguments have only been around for 160 years, and have been thoroughly critiqued in theory and practice. Aristotle not only founded most of our academic disciplines, but his ideas on logic, epistemology, metaphysics, physics, biology, ethics, psychology, the nature of God, poetry, rhetoric, and more, were the generally accepted view of things in the West for over 1,600 years. He was also pretty influential in the Islamic world, too.</p>",
        |                    "date": "07 November 2013 11:59am",
        |                    "id": 28656413,
        |                    "isHighlighted": true,
        |                    "isoDateTime": "2013-11-07T11:59:46Z",
        |                    "numRecommends": 8,
        |                    "numResponses": 1,
        |                    "status": "visible",
        |                    "userProfile": {
        |                        "apiUrl": "http://discussion.guardianapis.com/discussion-api/profile/4235958",
        |                        "avatar": "http://static.guim.co.uk/sys-images/discussion/avatars/2010/09/23/JamesDavid/0663561f-c609-43e2-a713-0c0006c1ec45/60x60.png",
        |                        "badge": [ ],
        |                        "displayName": "JamesDavid",
        |                        "secureAvatarUrl": "https://static-secure.guim.co.uk/sys-images/discussion/avatars/2010/09/23/JamesDavid/0663561f-c609-43e2-a713-0c0006c1ec45/60x60.png",
        |                        "userId": "4235958",
        |                        "webUrl": "http://www.theguardian.com/discussion/user/id/4235958"
        |                    },
        |                    "webUrl": "http://discussion.theguardian.com/comment-permalink/28656413"
        |                }
        |            ]
        |        },
        |        {
        |            "type": "discussion-prompt",
        |            "content": {}
        |        }
        |    ]
        |}
      """.stripMargin)}
  }

  private val expectedComment:
                         JsValue = Json.parse(
                       """{
    "apiUrl": "http://discussion.guardianapis.com/discussion-api/comment/28656413",
    "body": "<p>In terms of the diversity and originality of his work as well as his lasting influence, it's probably Aristotle. Marx's ideas and arguments have only been around for 160 years, and have been thoroughly critiqued in theory and practice. Aristotle not only founded most of our academic disciplines, but his ideas on logic, epistemology, metaphysics, physics, biology, ethics, psychology, the nature of God, poetry, rhetoric, and more, were the generally accepted view of things in the West for over 1,600 years. He was also pretty influential in the Islamic world, too.</p>",
    "date": "07 November 2013 11:59am",
    "id": 28656413,
    "isHighlighted": true,
    "isoDateTime": "2013-11-07T11:59:46Z",
    "numRecommends": 8,
    "numResponses": 1,
    "status": "visible",
    "userProfile": {
      "apiUrl": "http://discussion.guardianapis.com/discussion-api/profile/4235958",
      "avatar": "http://static.guim.co.uk/sys-images/discussion/avatars/2010/09/23/JamesDavid/0663561f-c609-43e2-a713-0c0006c1ec45/60x60.png",
      "badge": [],
      "displayName": "JamesDavid",
      "secureAvatarUrl": "https://static-secure.guim.co.uk/sys-images/discussion/avatars/2010/09/23/JamesDavid/0663561f-c609-43e2-a713-0c0006c1ec45/60x60.png",
      "userId": "4235958",
      "webUrl": "http://www.theguardian.com/discussion/user/id/4235958"
    },
    "webUrl": "http://discussion.theguardian.com/comment-permalink/28656413"
  }""")


}



