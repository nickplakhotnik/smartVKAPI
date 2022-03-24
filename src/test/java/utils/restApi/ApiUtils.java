package utils.restApi;

import enums.ParamsEnum;
import enums.RestApiRequestMethodEnum;
import pojos.BaseLikePojos;
import pojos.BaseSavePhotoPojos;
import pojos.BaseCreateNewPostOnWallPojos;
import pojos.BaseWallPhotoUploadServerPojos;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static BaseCreateNewPostOnWallPojos postOnWall(String message, ResponseSpecification responseSpecification) {
        return given()
            .when()
            .queryParam(ParamsEnum.MESSAGE.getParam(), message)
            .post(RestApiRequestMethodEnum.WALL_POST.getMethod())
            .then().spec(responseSpecification).log().all()
            .extract().as(BaseCreateNewPostOnWallPojos.class);
    }

    public static void deletePostOnWall(Integer postId, ResponseSpecification responseSpecification) {
        given()
                .when()
                .queryParam(ParamsEnum.POST_ID.getParam(), postId)
                .post(RestApiRequestMethodEnum.WALL_DELETE_POST.getMethod())
                .then().spec(responseSpecification).log().all();
    }

    public static BaseWallPhotoUploadServerPojos getWallPhotoUploadServer(ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(RestApiRequestMethodEnum.PHOTOS_GET_WALL_UPLOAD_SERVER.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(BaseWallPhotoUploadServerPojos.class);
    }

    public static BaseSavePhotoPojos saveUploadWallPhoto(Integer server, String photo, String hash, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.SERVER.getParam(), server)
                .queryParam(ParamsEnum.PHOTO.getParam(), photo)
                .queryParam(ParamsEnum.HASH.getParam(), hash)
                .post(RestApiRequestMethodEnum.PHOTOS_SAVE_WALL_PHOTO.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(BaseSavePhotoPojos.class);
    }

    public static BaseCreateNewPostOnWallPojos editPostOnWall(String message, Integer idPostFromWall, String photo, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.MESSAGE.getParam(), message)
                .queryParam(ParamsEnum.ATTACHMENTS.getParam(), photo)
                .queryParam(ParamsEnum.POST_ID.getParam(), idPostFromWall)
                .post(RestApiRequestMethodEnum.WALL_EDIT_POST.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(BaseCreateNewPostOnWallPojos.class);
    }

    public static void createNewCommentOnWall(Integer post_id, String message, ResponseSpecification responseSpecification) {
        given()
                .queryParam(ParamsEnum.POST_ID.getParam(), post_id)
                .queryParam(ParamsEnum.MESSAGE.getParam(), message)
                .post(RestApiRequestMethodEnum.WALL_CREATE_COMMENT.getMethod())
                .then().spec(responseSpecification).log().all();
    }

    public static BaseLikePojos getLikesFromPost(Integer post_id, ResponseSpecification responseSpecification) {
        return given()
                .queryParam(ParamsEnum.POST_ID.getParam(), post_id)
                .post(RestApiRequestMethodEnum.WALL_GET_LIKES.getMethod())
                .then().spec(responseSpecification).log().all()
                .extract().as(BaseLikePojos.class);
    }
}
