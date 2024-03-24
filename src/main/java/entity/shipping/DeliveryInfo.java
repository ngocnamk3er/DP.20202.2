package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;

/*
 * SOLID - Single responsibility principle: DeliveryInfo đang thực hiện nhiều hơn 1 nhiệm vụ đó là cung cấp thông tin giao hàng và tính phí ship
 * Có xem xét tách phần tính phí ship ra thành class khác
 */

/*
 * SOLID - Open/closed principle: DeliveryInfo khó mở rộng nếu trong tương lai nếu có thêm cách thức tính phí mới hoặc sử dụng thư viện tính toán khác có thể xem xét tạo interface cho DistanceCalculator
 * với method calculateDistance() để các class tính phí ship khác có thể implement
 */

/*
 * SOLID - Dependency inversion principle: DeliveryInfo phụ thuộc vào DistanceCalculator ảnh hưởng đến việc mở rộng nếu có thêm cách tính phí ship hay thư viện tính toán khác
 */

// Communicational cohesion: calculateShippingFee() thực hiện các thao tác liên quan đến việc tính phí ship dựa trên order có dữ liệu liên quan đến DeliveryInfo 

public class DeliveryInfo {

    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    // Stamp coupling: Truyền vào order nhưng không dùng đến
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * 1.2);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
