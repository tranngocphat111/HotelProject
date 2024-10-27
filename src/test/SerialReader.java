import com.fazecast.jSerialComm.SerialPort;

public class SerialReader {
    public static void main(String[] args) {
        // Lấy danh sách các cổng serial khả dụng
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Các cổng khả dụng:");
        for (SerialPort port : ports) {
            System.out.println(" - " + port.getSystemPortName());
        }

        // Chọn cổng thích hợp (ví dụ: "COM3")
        SerialPort serialPort = SerialPort.getCommPort("COM7");
        serialPort.setComPortParameters(115200, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        // Mở cổng serial
        if (serialPort.openPort()) {
            System.out.println("Mở cổng serial thành công.");
        } else {
            System.out.println("Không thể mở cổng serial.");
            return;
        }

        // Đọc dữ liệu từ cổng serial
        try {
            while (true) {
                if (serialPort.bytesAvailable() > 0) {
                    byte[] readBuffer = new byte[serialPort.bytesAvailable()];
                    int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
                    String receivedData = new String(readBuffer, 0, numRead);
                    System.out.println("Dữ liệu nhận được: " + receivedData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng cổng serial khi hoàn thành
            serialPort.closePort();
            System.out.println("Đã đóng cổng serial.");
        }
    }
}
