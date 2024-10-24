/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

public class VietQRGen {

    public static void main(String[] args) {
        // URL API VietQR
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
        requestBody.put("accountNo", "0934776810"); // Số tài khoản ngân hàng
        requestBody.put("accountName", "NGUYEN GIA BAO"); // Tên tài khoản
        requestBody.put("acqId", 970422); // Mã định danh ngân hàng
        requestBody.put("amount", 500000); // Số tiền chuyển
        requestBody.put("addInfo", "Thanh toan HD1"); // Nội dung chuyển tiền
        requestBody.put("template", "compact"); // Mẫu VietQR trả về
        
        // Log request body
        System.out.println("Request Body: " + requestBody);
        
        // Đóng gói yêu cầu
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        // Gửi POST request và nhận response
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        // In ra kết quả
        if (response.getStatusCode() == HttpStatus.OK) {
            String qrCodeUrl = response.getBody(); // URL hình ảnh QR code trả về từ API
            System.out.println("QR Code URL: " + qrCodeUrl);
        } else {
            System.out.println("Error: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        }
    }

}
