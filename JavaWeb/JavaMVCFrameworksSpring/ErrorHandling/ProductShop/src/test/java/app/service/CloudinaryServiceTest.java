package app.service;

import com.cloudinary.Cloudinary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CloudinaryServiceTest {
    private CloudinaryService cloudinaryService;

    @Before
    public void init() {
        Map<String, String> cloudinaryConfig = new HashMap<>();
        cloudinaryConfig.put("api_key", "288157521458636");
        cloudinaryConfig.put("api_secret", "Pyt7LbLO13vCgmvyl3Awggpa-JU");
        cloudinaryConfig.put("cloud_name", "rado-cloud");
        Cloudinary cloudinary = new Cloudinary(cloudinaryConfig);
        this.cloudinaryService = new CloudinaryServiceImpl(cloudinary);
    }


    @Test
    public void uploadImage() throws IOException {
        MultipartFile image = new MockMultipartFile("tomatoes", new FileInputStream(new File("C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductShop\\src\\main\\resources\\templates\\img\\tomatoes-200x200.jpg")));
        String imageUrl = this.cloudinaryService.uploadImage(image);

        Assert.assertTrue(imageUrl.contains("http://res.cloudinary.com/"));
    }
}
