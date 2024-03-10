### Bài 2: Coupling
- Subteam 2: 
    Ong Thế Tùng:
        - Đánh giá Coupling trong codebase: 
            + package common đa phần là data coupling
            + package controller chứa nhiều common coupling là AuthenticationController/getMainUser(),logout(), BaseController/checkMediaInCart(),getListCartMedia(), PaymentController emptyCart(), PlaceOrderController/placeOrder(), createOrder(), ViewCartController/checkAvailabilityOfProduct() do sử dụng global data chưa có thành phần quản lí là SessionInformation.
            + package dao đa phần là data coupling
            + package entity: Cart/checkMediaInCart() stamp coupling, Media/getQuantity() content coupling, Oder constructor stamp coupling, common coupling, DeliveryInfo/calculateShippingFee() stamp coupling
            + package subsystem: InterbankPayloadConverter/extractPaymentTransaction() control coupling
            + package utils: ApplicationProgrammingInterface/allowMethods() content coupling, MyMap/toMyMap() content coupling, 
            + package views:  common coupling: CartScreenHandler/setupFunctionality(), requestToPlaceOrder(), displayCartWithMediaAvailability(), MediaHandler/setMediaInfo(), HomeScreenHandler/setupData(), setupFunctionality(), setImage(), update(), redirectLoginScreen(), InvoiceScreenHandler/setupData(), confirmInvoice(), PaymentScreenHandler/confirmToPayOrder(), ShippingScreenHandler/setupData(), submitDeliveryInfo(), App/start() .stamp coupling:  HomeScreenHandler/setupData(), redirectLoginScreen(), LoginScreenHandler/setupData(), IntroScreenHandler/setupData(), PopupScreen/ PopupScreen(), success(), error(), loading(), setupData().


### Bài 2: Cohesion
- Subteam 2:
    Ong Thế Tùng:
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





