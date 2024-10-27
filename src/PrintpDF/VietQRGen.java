/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PrintpDF;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import model.DTO.HoaDon;

public class VietQRGen {

    private static byte[] decodeDataURI(String dataURI) {
        // Tách phần base64
        String base64Image = dataURI.split(",")[1]; // Phần sau dấu phẩy là dữ liệu base64
        return Base64.getDecoder().decode(base64Image);
    }

    private static void saveImage(byte[] imageBytes, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(imageBytes);
            System.out.println("QR Code image saved as: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VietQRGen(HoaDon hoaDon) {
        String url = "https://api.vietqr.io/v2/generate";

        // Tạo đối tượng RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Tạo yêu cầu header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-client-id", "50ccaeeb-630f-4fa1-9a3b-fee332349e7f"); // Thay thế bằng Client ID của bạn
        headers.set("x-api-key", "0101a28b-dcbd-40ca-aa48-623ac8672683"); // Thay thế bằng API key của bạn

        // Tạo dữ liệu request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("accountNo", "1030183164"); // Số tài khoản ngân hàng
        requestBody.put("accountName", "TRAN NGOC PHAT"); // Tên tài khoản
        requestBody.put("acqId", 970436); // Mã định danh ngân hàng
        requestBody.put("amount", hoaDon.getTongTien()); // Số tiền chuyển
        requestBody.put("addInfo", "Thanh toán HD00" + hoaDon.getMaHoaDon()); // Nội dung chuyển tiền
        requestBody.put("template", "print"); // Mẫu VietQR trả về

        // Log request body
        System.out.println("Request Body: " + requestBody);

        // Đóng gói yêu cầu
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Gửi POST request và nhận response
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // In ra kết quả
        if (response.getStatusCode() == HttpStatus.OK) {
            String qrCodeUrl = response.getBody(); // URL hình ảnh QR code trả về từ API
            try {
                // Sử dụng Jackson để phân tích cú pháp JSON
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.getBody());

                String code = jsonNode.path("code").asText();
                JsonNode data = jsonNode.path("data");

                if ("00".equals(code)) {
                    String qrDataURL = data.path("qrDataURL").asText(); // Lấy Data URI
                    saveImage(decodeDataURI(qrDataURL), "./src/images/qrcode.png"); // Lưu hình ảnh
                } else {
                    System.out.println("Error: " + jsonNode.path("desc").asText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        }
    }

    public static void main(String[] args) {
        // URL API VietQR
        new VietQRGen(new HoaDon());

    }

}
