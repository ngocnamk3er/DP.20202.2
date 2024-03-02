package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.MediaDAO;
/*
HomeController, PaymentController, PlaceOrderControlller, ViewCartController đều ở mức độ datacoupling vì các lớp này đều kế thừa
từ lớp BaseController, có 1 phụ thuộc dữ liệu vào lớp cha
 */
/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController {


    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }
}
