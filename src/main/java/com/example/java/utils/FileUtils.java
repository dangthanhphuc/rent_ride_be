package com.example.java.utils;

import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


public class FileUtils {

    public static boolean deleteFile(String path, String fileName) {
        if (fileName == null || fileName.isEmpty())
            return false;

        String[] validExtensions = {"jpg", "png"};
        for (String extension : validExtensions) {
            if( !fileName.endsWith("." + extension)){
                return false;
            }
        }

        Path filePath = Paths.get(path, fileName);

        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
                System.out.println("File " + fileName + " deleted successfully.");
            } catch (IOException e) {
                System.err.println("Failed to delete file " + fileName + ": " + e.getMessage());
            }
        } else {
            System.out.println("File " + fileName + " does not exist.");
        }
        return true;
    }

    // Kiểm tra định dạng có phải là image fule không
    private static void isImageFile(MultipartFile file) throws UnsupportedMediaTypeException, PayloadTooLargeException {

        // Kiểm tra dịnh dạng file có phải file ảnh không
        if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
            throw new UnsupportedMediaTypeException();
        }

        // Kiểm tra kích thước < 10MB
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new PayloadTooLargeException();
        }

    }

    // Trả về tên image file
    public static String storeImage(String path, MultipartFile file) throws IOException, UnsupportedMediaTypeException, PayloadTooLargeException {
        // Kiểm tra đầu vào nếu sai thì ném lỗi
        isImageFile(file);

//        if(file.getSize() == 0) {
//            continue;
//        }
//        // Kiểm tra kích thước file và định dạng
//        if(file.getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
//            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
//                    .body(ResponseObject.builder()
//                            .message(localizationUtils
//                                    .getLocalizedMessage(MessageKeys.UPLOAD_IMAGES_FILE_LARGE))
//                            .status(HttpStatus.PAYLOAD_TOO_LARGE)
//                            .build());
//        }
//        String contentType = file.getContentType();
//        if(contentType == null || !contentType.startsWith("image/")) {
//            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                    .body(ResponseObject.builder()
//                            .message(localizationUtils
//                                    .getLocalizedMessage(MessageKeys.UPLOAD_IMAGES_FILE_MUST_BE_IMAGE))
//                            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                            .build());
//        }

        // Lấy tên file
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        // Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + '_' + filename;
        // Đường dẫn đến thư mục bạn muốn lưu file
        Path uploadDir = Paths.get("images/" + path);
        try{
            // Kiểm tra và tạo thư mục nếu nó không tồn tại
            if(!Files.exists(uploadDir)){
                Files.createDirectories(uploadDir);
            }
            //Đường dẫn đầy đủ đến file
            Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
            // Sao chép file vào thư mục đích
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e) {
            System.err.println("Failed to store file " + uniqueFilename);
            e.printStackTrace();
            throw e;
        }

        return uniqueFilename;
    }
}
