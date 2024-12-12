import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import com.fazecast.jSerialComm.SerialPort;

public class ArduinoCommandSender {

    private SerialPort serialPort;
    private PrintWriter output;

    // Khởi tạo kết nối với Arduino
    public ArduinoCommandSender(String portName, int baudRate) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        if (serialPort.openPort()) {
            System.out.println("Kết nối với Arduino thành công trên cổng " + portName);
            OutputStream out = serialPort.getOutputStream();
            output = new PrintWriter(out, true);
            try {
                Thread.sleep(3000); // Đợi 3 giây sau khi kết nối
            } catch (InterruptedException e) {
                System.out.println("Lỗi khi chờ sau kết nối: " + e.getMessage());
            }
        } else {
            System.out.println("Không thể kết nối với Arduino trên cổng " + portName);
        }
    }

    // Gửi lệnh tới Arduino
    public void sendCommand(String command) {
        if (output != null) {
            output.println(command);
            System.out.println("Đã gửi lệnh: " + command);
            readResponse(command);
        } else {
            System.out.println("Lỗi: Chưa kết nối với Arduino!");
        }
    }

    // Đọc phản hồi từ Arduino trong 5 giây
    public void readResponse(String commandType) {
        Scanner scanner = new Scanner(serialPort.getInputStream());
        long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < 5000) {
                if (scanner.hasNextLine()) {
                    String response = scanner.nextLine();
                    System.out.println("Phản hồi từ Arduino: " + response);
                    if (response.equals("WRITE_DONE")) {
                        System.out.println("Ghi hoàn tất!");
                        scanner.close();
                        return;
                    } else if (response.equals("READ_DONE")) {
                        System.out.println("Đọc hoàn tất!");
                        scanner.close();
                        return;
                    }
                }
            }
            System.out.println("time out");
            scanner.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc phản hồi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Đóng kết nối
    public void close() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Đã đóng kết nối với Arduino.");
        }
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng với cổng serial tương ứng
        ArduinoCommandSender arduino = new ArduinoCommandSender("COM5", 115200);
        
        // Gửi lệnh mẫu
        arduino.sendCommand("WRITE|123");
        arduino.sendCommand("READ|");

        // Đóng kết nối
        arduino.close();
    }
}
