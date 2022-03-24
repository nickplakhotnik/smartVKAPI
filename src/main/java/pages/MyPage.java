package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {

    private static final String PAGE_LOC = "page_info_wrap";

    public MyPage() {
        super(By.id(PAGE_LOC), "My Page");
    }

    public PostForm getPostForm(String ownerId, int idOfPost) {
        return new PostForm(ownerId, idOfPost);
    }

}
