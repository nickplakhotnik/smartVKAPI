package utils;

import pojos.BaseUploadedPhotoPojos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class DeserializeJSONToUnloadedPhoto {

    public static BaseUploadedPhotoPojos getUploadedPhotoObject(String stringJSON) {
        ObjectMapper mapper = new ObjectMapper();
        BaseUploadedPhotoPojos uploadedPhoto = null;
        try {
            uploadedPhoto = mapper.readValue(stringJSON, BaseUploadedPhotoPojos.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadedPhoto;
    }
}
