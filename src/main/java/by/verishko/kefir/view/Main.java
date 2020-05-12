package by.verishko.kefir.view;

import by.verishko.kefir.dao.connect.ConnectionPool;

public class Main {
    public static void main(String[] args) {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println(ConnectionPool.getInstance().toString());
    }
}
