package by.verishko.kefir.dao;

public interface TransactionFactory {

    Transaction createTransaction();

    void close();
}
