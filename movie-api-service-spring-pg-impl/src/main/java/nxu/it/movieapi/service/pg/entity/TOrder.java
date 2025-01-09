package nxu.it.movieapi.service.pg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_order")
public class TOrder {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "freight")
    private null freight;

    @Column(name = "contact")
    private String contact;

    @Column(name = "phone")
    private String phone;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "ship_province")
    private String shipProvince;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_region")
    private String shipRegion;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getArrivalDate() {
        return this.arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public Long getShipperId() {
        return this.shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public null getFreight() {
        return this.freight;
    }

    public void setFreight(null freight) {
        this.freight = freight;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipProvince() {
        return this.shipProvince;
    }

    public void setShipProvince(String shipProvince) {
        this.shipProvince = shipProvince;
    }

    public String getShipCity() {
        return this.shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return this.shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }
}
