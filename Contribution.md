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





