package vk.api.test;

import aquality.selenium.browser.AqualityServices;
import pages.MyPage;
import pages.NewsPage;
import pages.PostForm;
import pojos.BaseLikePojos;
import pojos.BaseSavePhotoPojos;
import pojos.BaseCreateNewPostOnWallPojos;
import pojos.BaseUploadedPhotoPojos;
import utils.DeserializeJSONToUnloadedPhoto;
import utils.GenerateStringForPhoto;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ImageUtils;
import utils.restApi.ApiUtils;
import utils.restApi.ResponseSpecs;
import utils.restApi.RequestUploadPhoto;
import java.util.stream.Collectors;

public class VKTest extends BaseTest {

    @Test
    public void VKTest() {

        logger.info("1. Login test");
        LoginSteps.loginStep(user);

        logger.info("2. Open My Page");
        NewsPage newsPage = new NewsPage();
        newsPage.clickOnMyPageButton();
        MyPage myPage = new MyPage();
        Assert.assertTrue(myPage.state().waitForDisplayed(), "MyPage is not open");

        logger.info("3. Create post on wall with random text");
        String firstRandomText = RandomStringUtils.randomAlphabetic(10);
        BaseCreateNewPostOnWallPojos createdNewPostOnWall = ApiUtils.postOnWall(firstRandomText, ResponseSpecs.OK);

        logger.info("4. Checking created post");
        PostForm post = myPage.getPostForm(user.getOwnerId(), createdNewPostOnWall.getResponse().getPostId());
        Assert.assertTrue(post.isNewCreatedPostExist(), "Post is not created");
        Assert.assertTrue(post.getTextFromPost().contains(firstRandomText), "Post is not contains the text");

        logger.info("5. Edit post");
        String uploadServerUrl = ApiUtils.getWallPhotoUploadServer(ResponseSpecs.OK).getResponse().getUploadUrl();
        BaseUploadedPhotoPojos baseUploadedPhotoPojos = DeserializeJSONToUnloadedPhoto.getUploadedPhotoObject(
                RequestUploadPhoto.uploadPhotoToServer(uploadServerUrl));
        BaseSavePhotoPojos baseSavePhotoPojos = ApiUtils.saveUploadWallPhoto(baseUploadedPhotoPojos.getServer(),
                baseUploadedPhotoPojos.getPhoto(), baseUploadedPhotoPojos.getHash(), ResponseSpecs.OK);
        String editRandomText = RandomStringUtils.randomAlphabetic(10);
        String photo = GenerateStringForPhoto.getString(user.getOwnerId(), baseSavePhotoPojos.getResponse().get(0).getId());
        BaseCreateNewPostOnWallPojos baseCreateNewPostOnWallPojos = ApiUtils.editPostOnWall(editRandomText,
                createdNewPostOnWall.getResponse().getPostId(), photo, ResponseSpecs.OK);

        logger.info("6. Checking edited post");
        Assert.assertFalse(post.getTextFromPost().contains(firstRandomText), "Post did not change the text");
        Assert.assertTrue(post.isPhotoFromPostDisplayed(baseSavePhotoPojos.getResponse().get(0).getId()),
                "Photo is not found");
        String actualImage = post.downloadFullImageFromPost(baseSavePhotoPojos.getResponse().get(0).getId());
        String expectedImage = testData.getPhoto();
        Assert.assertEquals(String.valueOf(ImageUtils.compare(expectedImage, actualImage)), testData.getDifference(), "Images aren't the same");
        AqualityServices.getBrowser().goBack();
        ImageUtils.deleteFile(actualImage);

        logger.info("7. Add comment");
        String randomStringForComment = RandomStringUtils.randomAlphabetic(40);
        ApiUtils.createNewCommentOnWall(createdNewPostOnWall.getResponse().getPostId(), randomStringForComment,
                ResponseSpecs.OK);

        logger.info("8. Checking new comment");
        post.clickOnShowNextComment();
        Assert.assertTrue(post.isNewCommentOnWallDisplayed(), "Comment from correct user is not created");

        logger.info("9. Add like on post");
        post.clickOnLikePost();

        logger.info("10. Checking for like");
        BaseLikePojos likesPojos = ApiUtils.getLikesFromPost(createdNewPostOnWall.getResponse().getPostId(), ResponseSpecs.OK);
        Assert.assertTrue(likesPojos.getResponse().getUsers().stream().map(user -> user.getUid()).collect(Collectors.toList())
                .contains(Integer.valueOf(user.getOwnerId())), "No like from this User");

        logger.info("11. Delete the post");
        ApiUtils.deletePostOnWall(createdNewPostOnWall.getResponse().getPostId(), ResponseSpecs.OK);
        post.state().waitForNotDisplayed();

        logger.info("12. Checking post exist");
        Assert.assertFalse(post.isNewCreatedPostExist(),"Post is not deleted");
    }
}
