### Bài 2: Coupling
    Ong Thế Tùng - Subteam2:
        - Đánh giá Coupling trong codebase: 
            + package common đa phần là data coupling
            + package controller chứa nhiều common coupling là AuthenticationController/getMainUser(),logout(), BaseController/checkMediaInCart(),getListCartMedia(), PaymentController emptyCart(), PlaceOrderController/placeOrder(), createOrder(), ViewCartController/checkAvailabilityOfProduct() do sử dụng global data chưa có thành phần quản lí là SessionInformation.
            + package dao đa phần là data coupling
            + package entity: Cart/checkMediaInCart() stamp coupling, Media/getQuantity() content coupling, Oder constructor stamp coupling, common coupling, DeliveryInfo/calculateShippingFee() stamp coupling
            + package subsystem: InterbankPayloadConverter/extractPaymentTransaction() control coupling
            + package utils: ApplicationProgrammingInterface/allowMethods() content coupling, MyMap/toMyMap() content coupling, 
            + package views:  common coupling: CartScreenHandler/setupFunctionality(), requestToPlaceOrder(), displayCartWithMediaAvailability(), MediaHandler/setMediaInfo(), HomeScreenHandler/setupData(), setupFunctionality(), setImage(), update(), redirectLoginScreen(), InvoiceScreenHandler/setupData(), confirmInvoice(), PaymentScreenHandler/confirmToPayOrder(), ShippingScreenHandler/setupData(), submitDeliveryInfo(), App/start() .stamp coupling:  HomeScreenHandler/setupData(), redirectLoginScreen(), LoginScreenHandler/setupData(), IntroScreenHandler/setupData(), PopupScreen/ PopupScreen(), success(), error(), loading(), setupData().


### Bài 2: Cohesion
    Ong Thế Tùng - Subteam 2:
        - Đánh giá Cohesion của codebase:
            + package common đa phần là data coupling
            + package controller: 
                - AuthenticationController: Temporal cohesion: md5() không liên quan đến class chỉ thực hiện theo thứ tự thời gian bởi việc thực hiện login() sử dụng md5() nên để md5() ở phần utils
                - PaymentController: Temporal cohesion: getExpirationDate() không liên quan đến class chỉ thực hiện theo thứ tự thời gian bởi việc thực hiện payOrder() sử dụng getExpirationDate() 
                - PlaceOrderController: Temporal cohesion: validateDeliveryInfo(), validatePhoneNumber(), validateName(), validateAddress() chỉ thực hiện theo thứ tự thời gian bởi các method của lớp, nên tách riêng ra thành các class riêng và nên có 1 interface chung để chúng có thể implement
            + package dao 
                - MediaDAO: Communicational cohesion: getAllMedia() và các method liên quan đến MediaDAO chỉ thực hiện các thao tác liên quan đến Media
            + package entity: 
                - AIMSDB: Temporal cohesion: main() chỉ liên quan đến việc kết nối database bởi việc App chạy sẽ gọi đến getConnection() của AIMSDB
                - DeliveryInfo: Communicational cohesion: calculateShippingFee() thực hiện các thao tác liên quan đến việc tính phí ship dựa trên order có dữ liệu liên quan đến DeliveryInfo 
            + package subsystem: 
                - InterBankPayloadConverter: Coincidental cohesion: getToday không liên quan đến class
            + package utils: hầu hết là Function cohesion
            + package views:  
                - PopupScreen: Logical cohesion: success(), error(), loading() thực hiện các thao tác liên quan đến hiển thị popup

### Bài 3: SOLID: SRP, OCP
    Ong Thế Tùng - Subteam 2:
        - Đánh giá SOLID SRP và OCP trong codebase
            + SRP:
                - AuthenticationController đang thực hiện nhiều hơn 1 nhiệm vụ đó là xác thực người dùng, quản lý session và mã hóa dữ liệu Có xem xét đưa md5() vào Utils , để SessionInformation quản lý getMainUser() và AuthenticationController gồm: isAnonymousSession(), login(), logout()
                - PaymentController đang thực hiện nhiều hơn 1 nhiệm vụ đó là thanh toán và quản lý giỏ hàng và lấy ngày hết hạn Có xem xét đưa xử lí giỏ hàng vào CartController, getExpirationDate() có thể xử lí bằng việc tạo class CreditCardValidator
                - DeliveryInfo đang thực hiện nhiều hơn 1 nhiệm vụ đó là cung cấp thông tin giao hàng và tính phí ship. Có xem xét tách phần tính phí ship ra thành class khác
                - ApplicationProgrammingInterface đang thực hiện nhiều hơn 1 nhiệm vụ đó là thực hiện request , vừa tạo kết nối HTTP vừa thực hiện điều khiển truy cập allMethods(). Có xem xét tách ra thành các class nhỏ hơn
            + OCP:
                - Trong trường hợp tương lai có mở rộng thêm phương thức thanh toán mới nên tạo abstract class Card cho phép các class thẻ con kế thừa và các method xử lí theo Card, đưa method getExpirationDate() của class PaymentController ra ngoài để cho phép xử lí trong trường hợp có thể các loại thẻ khác có thể có kiểu date khác nhau bằng cách tạo class CardValidator để xử lí validate thẻ hoặc tạo interface CardValidator để implement và xử lí với từng Card, subsystem liên quan đến interbank và PaymentTransaction cần thay đổi tham số truyền vào là Card và tạo thêm các interface để implement cho xử lí với từng loại Card
                - PaymentController khó mở rộng nếu trong tương lai có phương thức thanh toán với thông tin của phương thức thanh toán khác như thẻ nội địa. Có xem xét tạo class CreditCardValidator để xử lí validate thẻ hoặc tạo interface CreditCardValidator để implement trong trường hợp có thể các loại thẻ khác có date khác nhau. Có thể xem xét tạo class Card để nhiều loại thẻ con như CreditCard, DomesticDebitCard kế thừa 
                - PaymentTransaction khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do hàm khởi tạo hiện tại chỉ nhận vào CreditCard
                - DeliveryInfo khó mở rộng nếu trong tương lai nếu có thêm cách thức tính phí mới hoặc sử dụng thư viện tính toán khác có thể xem xét tạo interface cho DistanceCalculator với method calculateDistance() để các class tính phí ship khác có thể implement
                - InterbankInterface khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do payOrder() và refund() chỉ nhận vào CreditCard
                - InterbankSubsystem khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do payOrder() và refund() chỉ nhận vào CreditCard
                - InterbankPayloadConverter khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do convertToRequestPayload(),  hiện tại chỉ nhận vào CreditCard và extractPaymentTransaction() hiện tại chỉ xử lí cho 1 kiểu response của CreditCard
                - InterbankPayloadConverter khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do payOrder(), refund() hiện tại chỉ nhận vào CreditCard

### Bài 3: SOLID: LSP, ISP, DIP
    Ong Thế Tùng - Subteam 2:
        - Đánh giá SOLID LSP, ISP và DIP trong codebase
            + LSP:
                - AuthenticationController không cần triển khai các phương thức khác của BaseController
                - HomeController không cần triển khai các phương thức khác của BaseController
                - PaymentController không cần triểnn khai các phương thức khác của BaseController
                - IntroScreenHandler không cần thiết triển khai một số phương thức của BaseScreenHandler như setPreviousScreen
                - PopupScreen không cần triển khai các phương thức khác của BaseScreenHandler
            + ISP: 
                - BaseController đang rộng hơn những gì các class con cần thiết gây ra LSP cho các con
                - BaseScreenHandler đang thực hiện quá nhiều phương thức
            + DIP:
                - PaymentController đang phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
                - PaymentTransaction phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
                - DeliveryInfo phụ thuộc vào DistanceCalculator ảnh hưởng đến việc mở rộng nếu có thêm cách tính phí ship hay thư viện tính toán khác
                - InterbankInterface phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
                - InterbankSubsystem phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
                - InterbankPayloadConverter phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
                - InterbankPayloadConverter phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác